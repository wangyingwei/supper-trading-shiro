package com.eccl.common.tools.ziptools;

import org.apache.tools.zip.ZipOutputStream;

import com.eccl.common.util.stringutil.StringUtil;

import org.apache.tools.zip.ZipEntry;
import java.io.*;

public class ZipTools {
	// 已经存在的压缩流
	ZipOutputStream zipStreamExist;
	ByteArrayOutputStream byteOutStream;
	String errMsg = "";

	public ZipTools() {
		errMsg = "";
		byteOutStream = new ByteArrayOutputStream();
		zipStreamExist = new ZipOutputStream(byteOutStream);
		zipStreamExist.setEncoding("gbk");
		zipStreamExist.setMethod(ZipOutputStream.DEFLATED);
	}

	/**
	 * 传入:已有的压缩流，和需要增加的压缩流，需要向Entry中写入的字节 返回:增加之后的压缩流
	 **/
	public void accumulateZipStream(String entryName, byte[] appendData) {
		if (entryName == null) {
			return;
		}
		// 判断，当建立的目录不是目录，而数据是空，则返回
		if (!entryName.trim().endsWith("/")) {
			if (appendData == null) {
				return;
			}
			if (appendData.length == 0) {
				return;
			}
		} else {
			if (appendData == null) {
				appendData = new byte[0];
			}
		}
		ZipEntry entryAdd = new ZipEntry(entryName);
		try {
			zipStreamExist.putNextEntry(entryAdd);
			zipStreamExist.write(appendData);
			zipStreamExist.closeEntry();
		} catch (Exception e) {
			errMsg = appendDotAndStr(errMsg, e.getMessage());
		}
	}

	public byte[] getBytes() {
		try {
			zipStreamExist.close();
		} catch (Exception eStreamClose) {
			errMsg = appendDotAndStr(errMsg, eStreamClose.getMessage());
		}

		return byteOutStream.toByteArray();
	}

	/**
	 * 处理字符串，如果不为空，则用逗号分割
	 **/
	private String appendDotAndStr(String source, String appendString, String seperator) {
		if (source.equals("")) {
			source += appendString;
		} else {
			source += seperator + appendString;
		}
		return source;
	}

	/*
	 * 将增加的字符串用隔断分开
	 **/
	private String appendDotAndStr(String source, String appendString) {
		return appendDotAndStr(source, appendString, ",");
	}

	public String handleSpecialFileName(String inputString) {
		inputString = inputString.trim();

		inputString = StringUtil.replaceAll(inputString, "/", "∕");
		inputString = StringUtil.replaceAll(inputString, "\\", "﹨");
		inputString = StringUtil.replaceAll(inputString, ":", "：");
		inputString = StringUtil.replaceAll(inputString, "*", "﹡");
		inputString = StringUtil.replaceAll(inputString, "?", "？");
		inputString = StringUtil.replaceAll(inputString, "<", "＜");
		inputString = StringUtil.replaceAll(inputString, ">", "＞");
		inputString = StringUtil.replaceAll(inputString, "|", "∣");
		inputString = StringUtil.replaceAll(inputString, "\"", "″");
		return inputString;
	}

	public static void main(String[] args) throws Exception {
		ZipTools zipTools = new ZipTools();
		zipTools.accumulateZipStream(java.net.URLEncoder.encode("你好1/1.txt", "GBK"), "你好1".getBytes());
		zipTools.accumulateZipStream("2/2.txt", "你好2".getBytes());

		FileOutputStream fw = new FileOutputStream("d:/a.zip");
		fw.write(zipTools.getBytes());
		fw.close();

	}

}