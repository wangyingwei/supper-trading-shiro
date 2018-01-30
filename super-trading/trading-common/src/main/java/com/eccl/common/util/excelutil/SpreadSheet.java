package com.eccl.common.util.excelutil;

import java.io.*;
import java.util.Hashtable;

public class SpreadSheet {
	private String separator = "\t";
	private String lineSeparator = "\n";
	private String spreadSheetStr = "";
	private String spsFilePath = "";

	private int maxX = 0;
	private int maxY = 0;

	private Hashtable cells = new Hashtable();

	private boolean blParsed = false;

	public static int REPLACE_KEY_WORDS = 1;
	public static int NONEREPLACE_KEY_WORDS = 0;

	public SpreadSheet() {

	}

	public SpreadSheet(String _spreadSheetStr) {
		this.spreadSheetStr = _spreadSheetStr;
	}

	public SpreadSheet(String _spreadSheetStr, String _spsFilePath) {
		this.spreadSheetStr = _spreadSheetStr;
		this.spsFilePath = _spsFilePath;
	}

	public SpreadSheet(String _spsFilePath, int itmp) throws Exception {
		this.spreadSheetStr = getFileContent(_spsFilePath);
		this.spsFilePath = _spsFilePath;
	}

	public SpreadSheet(File spsFile) throws Exception {
		this.spsFilePath = spsFile.getPath();
		this.spreadSheetStr = getFileContent(spsFile);
	}

	private String getFileContent(String filePath) throws Exception {
		File file = new File(filePath);
		if (!file.exists()) {
			throw new SpreadSheetException("系统找不到指定文件!");
		}
		return getFileContent(file);
	}

	private String getFileContent(File spsFile) throws Exception {
		FileInputStream fis = new FileInputStream(spsFile);
		byte[] buf = new byte[512];
		int size = fis.read(buf);
		String sTemp = "";
		while (size != -1) {
			sTemp += new String(buf, 0, size);
			size = fis.read(buf);
		}
		fis.close();
		return sTemp;
	}

	public void setSpreadSheetStr(String _spreadSheetStr) throws Exception {
		if (blParsed) {
			throw new SpreadSheetException("已经初始化,不允许此操作!");
		}
		this.spreadSheetStr = _spreadSheetStr;
	}

	public void setSpsFilePath(String _spsFilePath) throws Exception {
		this.spsFilePath = _spsFilePath;
		if (!blParsed & fileExist(_spsFilePath)) {
			this.spreadSheetStr = getFileContent(_spsFilePath);
		}
	}

	private boolean fileExist(String filePath) {
		File file = new File(filePath);
		return file.exists();
	}

	public void setSeparator(String _separator) {
		this.separator = _separator;
	}

	public void setLineSeparator(String _lineSeparator) {
		this.lineSeparator = _lineSeparator;
	}

	public void parse() throws Exception {
		if (this.spreadSheetStr.trim().equals("")) {
			throw new SpreadSheetException("没有初始化或者文件内容为空!");
		}
		if (!blParsed) {
			blParsed = true;
			try {
				if (this.lineSeparator.equals("\n")) {
					this.spreadSheetStr.replaceAll("\r\n", "\n");
				}

				String[] sLines = this.spreadSheetStr.split(this.lineSeparator);
				// maxX = sLines.length;
				for (int i = 0; i < sLines.length; i++) {
					String[] sCols = sLines[i].split(this.separator);

					// if(sCols.length>maxY)
					// maxY = sCols.length;

					for (int j = 0; j < sCols.length; j++) {
						this.setCells(sCols[j], i, j);
					}
				}
			} catch (Exception e) {
				blParsed = false;
				throw e;
			}
			// System.out.println(this.spreadSheetStr);
			blParsed = true;
		}
	}

	public void add() {
		blParsed = true;
	}

	public void save() throws Exception {
		this.rebuild();
		if (this.spsFilePath.trim().equals("")) {
			throw new SpreadSheetException("没有设置保存的文件名称!");
		}
		if (!blParsed) {
			throw new SpreadSheetException("还没有成功初始化!");
		}
		FileWriter file = new FileWriter(this.spsFilePath, false);
		file.write(this.spreadSheetStr);
		file.flush();
		file.close();
	}

	public String getSpreadSheetStr() throws Exception {
		if (!blParsed) {
			throw new SpreadSheetException("还没有成功初始化!");
		}
		return this.spreadSheetStr;
	}

	public byte[] getSpreadSheetBinary() throws Exception {
		if (!blParsed) {
			throw new SpreadSheetException("还没有成功初始化!");
		}
		return this.spreadSheetStr.getBytes();
	}

	public void rebuild() throws Exception {
		StringBuffer sb = new StringBuffer("");
		if (!blParsed) {
			throw new SpreadSheetException("还没有成功初始化!");
		}
		this.spreadSheetStr = "";
		for (int i = 0; i <= maxX; i++) {
			for (int j = 0; j <= maxY; j++) {
				String sTemp = "";
				if (cells.get("cell" + i + "-" + j) != null) {
					sTemp = cells.get("cell" + i + "-" + j).toString();
				}
				sb.append(sTemp);
				if (j != maxY) {
					sb.append(this.separator);
				}
			}
			if (i != maxX) {
				sb.append(this.lineSeparator);
			}

		}
		this.spreadSheetStr = sb.toString();
	}

	public String getCells(int x, int y) throws Exception {
		if (x > maxX) {
			throw new SpreadSheetException("行坐标越界!");
		}
		if (y > maxY) {
			throw new SpreadSheetException("列坐标越界!");
		}
		if (!blParsed) {
			throw new SpreadSheetException("还没有成功初始化!");
		}
		String sTemp = "";
		if (cells.get("cell" + x + "-" + y) != null) {
			sTemp = "" + cells.get("cell" + x + "-" + y);
		}

		if (sTemp.indexOf(",") > 0 && sTemp.startsWith("\"") && sTemp.endsWith("\"")) {
			sTemp = sTemp.substring(1, sTemp.length() - 1);
		}

		if (sTemp.indexOf("\"\"") > 0 && sTemp.startsWith("\"") && sTemp.endsWith("\"")) {
			sTemp = sTemp.replaceAll("\"", "\"\"").substring(1, sTemp.length() - 1);
		}

		return sTemp.trim();

	}

	public void setCells(String value, int x, int y, int t) throws Exception {
		if (t == this.REPLACE_KEY_WORDS) {
			setCells(value.replaceAll(this.separator, "").replaceAll(this.lineSeparator, ""), x, y);
		} else {
			setCells(value, x, y);
		}
	}

	public void setCells(String value, int x, int y) throws Exception {
		if (value.indexOf(this.separator) > 0 || value.indexOf(this.lineSeparator) > 0) {
			throw new SpreadSheetException("设置的内容含有关键字");
		}
		if (!blParsed) {
			throw new SpreadSheetException("还没有成功初始化!");
		}
		if (value.startsWith("\"")) {
			value = " " + value;
		}
		cells.put("cell" + x + "-" + y, value);
		if (x > maxX) {
			maxX = x;
		}
		if (y > maxY) {
			maxY = y;
		}

	}

	public int getMaxX() {
		return maxX;
	}

	public int getMaxY() {
		return maxY;
	}

	public void release() {
		cells.clear();
		this.spreadSheetStr = "";
		this.spsFilePath = "";
		maxX = 0;
		maxY = 0;
		blParsed = false;

	}

	public void close() {
		this.release();
	}

	public static void main(String args[]) throws Exception {
		System.out.println(" $Id: SpreadSheet.java,v 1.1.1.1 2009/06/04 02:34:05 liuchao Exp $ ");
		SpreadSheet sps = new SpreadSheet();
		sps.setSpsFilePath("c:\\abc.txt");
		sps.add();
		sps.setCells("yht1", 0, 0);
		sps.setCells("yht3", 1, 1);
		sps.save();
		sps.release();
		sps.setSpsFilePath("c:\\abc.txt");
		sps.parse();
		System.out.println(sps.getCells(1, 1));
	}
}