package com.eccl.common.tools.pdftools;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.URL;

import javax.servlet.ServletOutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.zefer.pd4ml.PD4Constants;
import org.zefer.pd4ml.PD4ML;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import com.eccl.common.tools.pdftools.Html2Pdf;

public class PDFTools {

	public String getContent(String strUrl) {
		try {
			URL url = new URL(strUrl);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			String s = "";
			StringBuffer sb = new StringBuffer("");
			while ((s = br.readLine()) != null) {
				sb.append(s);
			}
			br.close();
			return check(sb.toString());
		} catch (Exception e) {
			return "error open url:" + strUrl;
		}
	}

	private String check(String content) {
		String temp = "";
		if (content.indexOf("<!--hiddenStart-->") != -1) {
			temp = content.substring(content.indexOf("<!--hiddenStart-->"), content.indexOf("<!--hiddenEnd-->") + 16);
			content = content.replace(temp, "");
			return check(content);
		} else
			return content;
	}

	/**
	 * 
	 * @param str
	 * @param out
	 */
	public void convertPDF(String str, OutputStream out) {
		try {
			PD4ML html = new PD4ML();
			html.setPageSize(html.changePageOrientation(PD4Constants.A4));
			html.setPageInsets(new java.awt.Insets(0, 0, 0, 0));
			html.setHtmlWidth(1024);
			html.useTTF("java:fonts", true);
			html.enableDebugInfo();
			html.adjustHtmlWidth();
			html.render(new StringReader(str), out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean createPDF(String url_, ServletOutputStream sout) {
		boolean result = false;
		String defaultEncoding = "GBK";

		try {

			String encoding = getEncoding();
			if (StringUtils.isNotEmpty(encoding)) {
				defaultEncoding = encoding;
			}
			URL url = new URL(url_);
			InputStream in = url.openStream();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			IOUtils.copy(in, out);
			PD4ML html = new PD4ML();
			html.setPageSize(html.changePageOrientation(PD4Constants.A4));
			html.setPageInsets(new java.awt.Insets(0, 0, 0, 0));
			html.setHtmlWidth(1024);
			html.useTTF("java:fonts", true);
			html.enableDebugInfo();
			html.adjustHtmlWidth();
			html.render(new StringReader(out.toString(defaultEncoding)), sout);
			in.close();
			in = null;
			out.close();
			out = null;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {

		}
		return result;
	}

	public boolean createPDF(String content, ServletOutputStream sout, String encoding) throws Exception {
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		ITextRenderer renderer = new ITextRenderer();
		ITextFontResolver fontResolver = renderer.getFontResolver();
		InputStream intream = null;
		Document doc = null;
		Document doc2 = null;
		boolean returnValue = false;
		try {
			intream = new ByteArrayInputStream(content.getBytes(encoding));
			doc = (Document) builder.parse(intream);
			transformer.setOutputProperty("encoding", encoding);
			transformer.transform(new DOMSource(doc), new StreamResult(bos));
			intream = new ByteArrayInputStream(bos.toString().getBytes());
			doc2 = (Document) builder.parse(intream);
			fontResolver.addFont(Html2Pdf.class.getResource("simsun.ttc").toString().substring(5), BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			renderer.setDocument(doc2, null);
			renderer.layout();
			renderer.createPDF(sout, true);
			returnValue = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sout != null) {
				try {
					sout.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return returnValue;
	}

	private String getEncoding() throws IOException {
		LoadProperties load = new LoadProperties("config");
		return load.getValue("PDF_INPUT_ENCODING");
	}
	
/**
    
    @roseuid 41254983032C
	 */
	public static byte[] getPDFError(String errorStr) throws Exception
	{
		errorStr="错误消息："+errorStr;
		BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		com.lowagie.text.Font FontChinese = new com.lowagie.text.Font(bfChinese, 9, com.lowagie.text.Font.NORMAL);
		com.lowagie.text.Font titleFont=new com.lowagie.text.Font(bfChinese,15,com.lowagie.text.Font.NORMAL);
		//定义输出流
		ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
		//FileOutputStream outputStream=new FileOutputStream("Err.pdf");
		//定义pdf文档
		com.lowagie.text.Document document=new com.lowagie.text.Document();
		document.setPageSize(com.lowagie.text.PageSize.A4.rotate());
		document.setMargins(8,8,16,16);
		//初始化pdfWriter
		PdfWriter pdfWriter=PdfWriter.getInstance(document,outputStream);
		//打开文档
		document.open();
		com.lowagie.text.Table table=new com.lowagie.text.Table (95);
		//设置table自动适应报表
		table.setCellsFitPage(true);
		//设置table宽度
		table.setWidth(98);
		//设置每个单元格的在table中的周围的空间
		table.setSpaceInsideCell(2);

		//table.setBorderWidth(1/2);
		table.setBorder(com.lowagie.text.Rectangle.NO_BORDER);
		com.lowagie.text.Cell cell00=new com.lowagie.text.Cell(new com.lowagie.text.Paragraph("发生错误",titleFont));
		//cell00.setBorder(com.lowagie.text.Rectangle.TOP|com.lowagie.text.Rectangle.LEFT|com.lowagie.text.Rectangle.RIGHT);
		cell00.setBorder(com.lowagie.text.Rectangle.NO_BORDER);
		cell00.setHorizontalAlignment(com.lowagie.text.Rectangle.ALIGN_CENTER);
		cell00.setColspan(95);
		//cell00.setHeader(true);
		table.addCell(cell00);

		com.lowagie.text.Cell cell01=new com.lowagie.text.Cell(new com.lowagie.text.Paragraph("对不起，程序暂时无法提供服务",titleFont));
		//cell01.setBorder(com.lowagie.text.Rectangle.TOP|com.lowagie.text.Rectangle.LEFT|com.lowagie.text.Rectangle.RIGHT);
		cell01.setBorder(com.lowagie.text.Rectangle.NO_BORDER);
		cell01.setHorizontalAlignment(com.lowagie.text.Rectangle.ALIGN_CENTER);
		cell01.setColspan(95);
		table.addCell(cell01);

		com.lowagie.text.Cell cell02=new com.lowagie.text.Cell(new com.lowagie.text.Paragraph(errorStr,titleFont));
		//cell02.setBorder(com.lowagie.text.Rectangle.TOP|com.lowagie.text.Rectangle.LEFT|com.lowagie.text.Rectangle.RIGHT);
		cell02.setBorder(com.lowagie.text.Rectangle.NO_BORDER);
		cell02.setHorizontalAlignment(com.lowagie.text.Rectangle.ALIGN_CENTER);
		cell02.setColspan(95);
		table.addCell(cell02);

		table.complete();
		document.add(table);
		document.close();

		return outputStream.toByteArray();
		//return new byte[2];
	}

}