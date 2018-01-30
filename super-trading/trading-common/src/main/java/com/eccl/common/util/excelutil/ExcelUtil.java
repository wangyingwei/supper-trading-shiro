package com.eccl.common.util.excelutil;

import java.io.File;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import com.eccl.common.tools.charactertools.CharacterTools;

import java.util.HashMap;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.Sheet;
import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.write.WritableWorkbook;
import jxl.write.WritableSheet;
import jxl.write.Label;

public class ExcelUtil {
	private int rowNum = 0;
	private int colNum = 0;
	private int startRowIndex = 0;
	private int startColIndex = 0;

	private String defaultEncoding = "GBK";
	private WorkbookSettings workBookSettings = null;
	private Workbook workBook = null;
	private Sheet sheet = null;
	private Cell cell = null;

	private String[][] results = null;

	public ExcelUtil() {

	}

	public ExcelUtil(int rowNum, int colNum, int startRowIndex, int startColIndex) {
		this.rowNum = rowNum;
		this.colNum = colNum;
		this.startRowIndex = startRowIndex;
		this.startColIndex = startColIndex;
	}

	/**
	 * 通过Workbook对象获取Excel数据的二维字符串数组
	 * 
	 * @param workBook
	 *            表示Excel中一个sheet的数据对象
	 * @return 返回一个Excel数据的二维字符串数组
	 */
	public String[][] parse(Workbook workBook) {
		int endRowNum = 0;
		int endColNum = 0;
		try {
			sheet = workBook.getSheet(0);
			endRowNum = (sheet.getRows() > rowNum) ? rowNum : sheet.getRows();
			System.out.println("endRowNum==" + endRowNum);
			endColNum = (sheet.getColumns() > colNum) ? colNum : sheet.getColumns();
			System.out.println("endColNum==" + endColNum);
			results = new String[endRowNum - startRowIndex][endColNum - startColIndex];
			for (int i = startRowIndex; i < endRowNum; i++) {
				for (int j = startColIndex; j < endColNum; j++) {
					cell = (sheet.getRow(i))[j] == null ? null : (sheet.getRow(i))[j];
					if (cell != null)
						results[i - startRowIndex][j - startColIndex] = cell.getContents();
					else
						results[i - startRowIndex][j - startColIndex] = "";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	/**
	 * 通过Excel文件获取Excel数据，并用默认编码分析Excel数据获取Excel文件的二维字符串数据
	 * 
	 * @param fileName
	 *            所需解析Excel文件的文件名
	 * @param encoding
	 *            用于指定解析Excel文件的编码
	 * @return 返回一个Excel文件的二维字符串数组
	 */
	public String[][] parseExcelData(File file) {
		return parseExcelData(file, defaultEncoding);
	}

	/**
	 * 通过Excel文件获取Excel数据，并用指定的编码分析Excel数据获取Excel文件的二维字符串数据
	 * 
	 * @param fileName
	 *            所需解析Excel文件的文件名
	 * @param encoding
	 *            用于指定解析Excel文件的编码
	 * @return 返回一个Excel文件的二维字符串数组
	 */
	public String[][] parseExcelData(File file, String encoding) {
		workBookSettings = new WorkbookSettings();
		workBookSettings.setEncoding(encoding);
		try {
			workBook = Workbook.getWorkbook(file, workBookSettings);
			results = this.parse(workBook);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	/**
	 * 通过Excel文件名获取Excel数据，并用指定的编码分析Excel数据获取Excel文件的二维字符串数据
	 * 
	 * @param fileName
	 *            所需解析Excel文件的文件名
	 * @param encoding
	 *            用于指定解析Excel文件的编码
	 * @return 返回一个Excel文件的二维字符串数组
	 */
	public String[][] parseExcelData(String fileName) {
		return parseExcelData(fileName, defaultEncoding);
	}

	/**
	 * 通过Excel文件名获取Excel数据，并用指定的编码分析Excel数据获取Excel文件的二维字符串数据
	 * 
	 * @param fileName
	 *            所需解析Excel文件的文件名
	 * @param encoding
	 *            用于指定解析Excel文件的编码
	 * @return 返回一个Excel文件的二维字符串数组
	 */
	public String[][] parseExcelData(String fileName, String encoding) {
		workBookSettings = new WorkbookSettings();
		workBookSettings.setEncoding(encoding);
		try {
			workBook = Workbook.getWorkbook(new File(fileName), workBookSettings);
			results = this.parse(workBook);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	/**
	 * 从Excel文件字节数组获取Excel数据，采用默认的编码defaultEncoding解析Excel文件的数据
	 * 
	 * @param bytes
	 *            是Excel文件的字节数组
	 * @return 返回一个Excel数据的二维字符串数组
	 */
	public String[][] parseExcelData(byte[] bytes) {
		return parseExcelData(bytes, defaultEncoding);
	}

	/**
	 * 从Excel文件字节数组获取Excel数据，并采用指定的编码解析Excel数据
	 * 
	 * @param bytes
	 *            是Excel文件的字节数组
	 * @param encoding
	 *            用于指定解析Excel文件时编码
	 * @return 返回一个Excel数据的二维字符串数组
	 */
	public String[][] parseExcelData(byte[] bytes, String encoding) {

		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		workBookSettings = new WorkbookSettings();
		workBookSettings.setEncoding(encoding);
		try {
			workBook = Workbook.getWorkbook(in, workBookSettings);
			results = this.parse(workBook);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	/**
	 * 从Excel文件的字节数据中，通过分析获取指定类的对象集合，采用默认编码defaultEncoding解析Excel数据
	 * 
	 * @param className
	 *            获取目标对象集合中的对象限定名
	 * @param propertyName
	 *            获取目标对象集合中的对象中需要赋值的属性
	 * @param bytes
	 *            Excel文件的字节数组
	 * @return
	 */
	public List parseExcelObj(String className, String[] propertyName, byte[] bytes) throws Exception {
		return parseExcelObj(className, propertyName, bytes, defaultEncoding);
	}

	/**
	 * 从Excel文件字节数组获取Excel数据，采用指定的编码分析数据并获取指定类的对象集合
	 * 
	 * @param className
	 *            获取目标对象集合中的对象限定名
	 * @param propertyName
	 *            获取目标对象集合中的对象中需要赋值的属性
	 * @param bytes
	 *            Excel文件的字节数组
	 * @param encoding
	 *            用于指定解析Excel文件数据的编码
	 * @return
	 */
	public List parseExcelObj(String className, String[] propertyName, byte[] bytes, String encoding) throws Exception {
		List results = new ArrayList();
		String[][] binResults = null;
		Object newObj = null;
		Field[] fields = null;
		String log = "";
		try {
			log += "bytes.size=" + bytes.length + "\nstart..\n Class.forName(className);\n";
			Class c = Class.forName(className);
			log += "parseExcelData(bytes,encoding);\n";
			binResults = this.parseExcelData(bytes, encoding);
			log += "for...;\n binResults.length=" + binResults.length;
			for (int i = 0; i < binResults.length; i++) {
				log += "\n\n";
				newObj = c.newInstance();
				fields = c.getDeclaredFields();
				for (int j = 0; j < binResults[i].length; j++) {
					log += "i=" + i + " j=" + j + "; " + newObj + "; " + fields.length + "\n";
					for (int k = 0; k < fields.length; k++) {
						log += "; " + (fields[k]).getName();
						if ((fields[k]).getName().equals(propertyName[j])) {
							log += "; propertyName[j]=" + propertyName[j] + ";vaule==" + binResults[i][j] + "\n";
							(fields[k]).setAccessible(true);
							(fields[k]).set(newObj, binResults[i][j]);
							break;
						}
					}
				}
				results.add(newObj);
			}
		} catch (Exception e) {
			throw new Exception("分析Excel数据获取对象时错误:" + e.getMessage() + "\n log=" + log);
		}

		return results;
	}

	/**
	 * 从Excel文件字节数组获取Excel数据，采用指定的编码分析数据并获取指定类的对象集合
	 * 
	 * @param className
	 *            获取目标对象集合中的对象限定名
	 * @param colAndPropNames
	 *            获取目标对象集合中的对象中需要赋值的属性与Excel文件中的列值的对应关系集合
	 * @param bytes
	 *            Excel文件的字节数组
	 * @param encoding
	 *            用于指定解析Excel文件数据的编码
	 * @return
	 */
	public List parseExcelObj(String className, HashMap colAndPropNames, byte[] bytes, String encoding)
			throws Exception {
		List results = new ArrayList();
		String[][] binResults = null;
		Object newObj = null;
		Field[] fields = null;
		String log = "";
		try {
			log += "bytes.size=" + bytes.length + "\nstart..\n Class.forName(className);\n";
			Class c = Class.forName(className);
			log += "parseExcelData(bytes,encoding);\n";
			binResults = this.parseExcelData(bytes, encoding);
			log += "for...;\n binResults.length=" + binResults.length;
			for (int i = 0; i < binResults.length; i++) {
				log += "\n\n";
				newObj = c.newInstance();
				fields = c.getDeclaredFields();
				for (int j = 0; j < binResults[i].length; j++) {

					if (colAndPropNames.get(String.valueOf(j)) == null) {
						break;
					}
					log += "i=" + i + " j=" + j + "; " + newObj + "; " + fields.length + "\n";
					for (int k = 0; k < fields.length; k++) {
						log += "; " + (fields[k]).getName();
						if ((fields[k]).getName().equals(colAndPropNames.get(String.valueOf(j)))) {
							log += "; propertyName[j]=" + colAndPropNames.get(String.valueOf(j)) + ";vaule=="
									+ binResults[i][j] + "\n";
							(fields[k]).setAccessible(true);
							(fields[k]).set(newObj, binResults[i][j]);
							break;
						}
					}
				}
				results.add(newObj);
			}
		} catch (Exception e) {
			throw new Exception("分析Excel数据获取对象时错误:" + e.getMessage() + "\n log=" + log);
		}

		return results;
	}

	/**
	 * @param workbook
	 *            一个<code>WritableWorkbook</code>对象，作为生成Excel文件中的工作薄
	 * @param className
	 *            一个<code>String</code>对象，用于指定传入数据对象所属的类限定名
	 * @param objects
	 *            一个<code>List</code>对象，用于产生Excel文件的数据对象
	 * @param headers
	 *            一个<code>String[]</code>对象，用于指定生成Excel文件的文件头
	 * @param colAndPropNames
	 *            一个<code>HashMap</code>对象，用于指定数据对象中的属性与生成Excel文件的列的对应关系
	 * @return void
	 * @throws Exception
	 */
	public void generateExcelFile(WritableWorkbook workbook, String className, List objects, String[] headers,
			HashMap colAndPropNames) throws Exception {
		StringBuffer result = new StringBuffer();
		Class c = null;
		Field[] fields = null;
		Field field = null;
		int cols = headers.length;
		String tempPropName = null;
		WritableSheet sheet = workbook.createSheet("sheet1", 0);

		c = Class.forName(className);
		fields = c.getDeclaredFields();

		for (int i = 0; i < cols; i++) {// 添加Excel表头
			sheet.addCell(new Label(i, 0, headers[i]));
		}

		for (int j = 0; j < objects.size(); j++) {// 逐行生成数据
			for (int k = 0; k < cols; k++) {// 对于每一行逐列生成数据
				tempPropName = (String) colAndPropNames.get("" + k + "");
				if (tempPropName != null) {
					for (int m = 0; m < fields.length; m++) {// 判断数据对象是否有列值和属性映射中对应的属性
						field = fields[m];
						field.setAccessible(true);
						field.get((objects.get(j)));
						if (field.getName().equals(tempPropName)) {
							sheet.addCell(new Label(k, j, (field.get(objects.get(j))).toString()));
						}
						field.setAccessible(false);
					}
				}
				// 下一列
			} // end for(int k...)
				// 换行
		} // end for(int i...)
	}

	/**
	 * 根据表头数据和行数据生成个Excel数据文件
	 * 
	 * @param workbook
	 *            一个<code>WritableWorkbook</code>对象，作为生成Excel文件中的工作薄
	 * @param excelHeaders
	 *            一个<code>String[]</code>对象为表头数据
	 * @param excelDatas
	 *            一个<code>String[]</code>对象为表中行数据
	 * @return void
	 */
	public void generateExcelFile(WritableWorkbook workbook, String[] excelHeaders, String[][] excelDatas)
			throws Exception {

		int columnNum = 0;
		int rowNum = 0;
		WritableSheet sheet = workbook.createSheet("sheet1", 0);// excel文件中的工作表

		if (excelHeaders != null) {
			columnNum = excelHeaders.length;
		}
		if (excelDatas != null) {
			rowNum = excelDatas.length;
		}
		for (int i = 0; i < columnNum; i++) {// 添加Excel表头
			sheet.addCell(new Label(i, 0, excelHeaders[i]));
		}

		for (int j = 0; j < rowNum; j++) {// 每一行
			for (int k = 0; k < columnNum; k++) {
				sheet.addCell(new Label(k, j + 1, excelDatas[j][k]));// 每一列
			}
		}
	}

	/**
	 * 根据传入的Excel的byte[]转换为SpreadSheet格式，并返回byte[]
	 *
	 */
	public static byte[] excel2spreadSheet(byte[] excelBytes) throws Exception {
		SpreadSheet spreadSheet = new SpreadSheet();
		spreadSheet.setSeparator("\t");
		spreadSheet.setLineSeparator("\r\n");
		// spreadSheet.setSpsFilePath("c:\\zhuan.xls");
		spreadSheet.add();
		ByteArrayInputStream input = new ByteArrayInputStream(excelBytes);

		WorkbookSettings workbookSettings = new WorkbookSettings();
		workbookSettings.setEncoding("ISO-8859-1");
		Workbook workbook = Workbook.getWorkbook(input, workbookSettings);

		Sheet sheet = workbook.getSheet(0);
		// sheet.get
		// eccl.clmp.util.Cell spreadCell=eccl.clmp.util
		for (int row = 0; row < sheet.getRows(); row++) {

			Cell[] cell = sheet.getRow(row);
			// System.out.println(cell.length);
			for (int column = 0; column < cell.length; column++) {
				String str = "";
				if (cell[column].getType() == CellType.DATE) {
					DateCell dateCell = (DateCell) cell[column];
					// System.out.println(dateCell.getDateFormat());
					java.util.Date utilDate = dateCell.getDate();
					// System.out.println(utilDate.toGMTString());

					// System.out.println(utilDate.toLocaleString());
					utilDate.setHours(utilDate.getHours() - 8);
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					str = simpleDateFormat.format(utilDate);
				} else {
					str = cell[column].getContents();

				}
				// System.out.println(str);
				spreadSheet.setCells(str, row, column, SpreadSheet.REPLACE_KEY_WORDS);
			}
		}
		spreadSheet.rebuild();
		return spreadSheet.getSpreadSheetStr().getBytes();
	}

	/**
	 * 根据传入的SpreadSheet的byte[]转换为Excel格式，并返回byte[]
	 *
	 */
	public static byte[] spreadSheet2excel(byte[] spsBytes) throws Exception {
		String spreadStr = new String(spsBytes);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		WritableWorkbook writeWorkbook = Workbook.createWorkbook(outputStream);
		WritableSheet sheetWrite = writeWorkbook.createSheet("第一页", 1);
		// System.out.println(spreadStr);
		String[] rows = spreadStr.split("\r\n");
		// System.out.println(rows.length);
		for (int row = 0; row < rows.length; row++) {
			String[] columns = rows[row].split("\t");
			for (int column = 0; column < columns.length; column++) {
				Label label = new Label(column, row, columns[column]);
				sheetWrite.addCell(label);

			}
		}
		writeWorkbook.write();
		writeWorkbook.close();
		return outputStream.toByteArray();
	}

	/**
	 * 读Excel单元格，返回值，主要用作日期处理
	 * 
	 * @param cell
	 *            Excel单元格
	 * @param dateFormat
	 *            日期格式
	 * @return String
	 * @throws Exception
	 */
	public static String readExcelCell(Cell cell, String dateFormat) throws Exception {
		String returnStr = "";

		if (cell.getType() == CellType.DATE || cell.getType() == CellType.DATE_FORMULA) {
			java.util.Date date = ((DateCell) cell).getDate();

			TimeZone tz = TimeZone.getTimeZone("GMT");

			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

			sdf.setTimeZone(tz);

			String dateStr = sdf.format(date);

			returnStr = dateStr;
		} else {
			returnStr = cell.getContents() == null ? "" : CharacterTools.ImportCharacterReplace(cell.getContents());
		}

		return returnStr;
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public int getColNum() {
		return colNum;
	}

	public void setColNum(int colNum) {
		this.colNum = colNum;
	}

	public int getStartRowIndex() {
		return startRowIndex;
	}

	public void setStartRowIndex(int startRowIndex) {
		this.startRowIndex = startRowIndex;
	}

	public int getStartColIndex() {
		return startColIndex;
	}

	public void setStartColIndex(int startColIndex) {
		this.startColIndex = startColIndex;
	}

	public String getDefaultEncoding() {
		return defaultEncoding;
	}

	public void setDefaultEncoding(String defaultEncoding) {
		this.defaultEncoding = defaultEncoding;
	}

}