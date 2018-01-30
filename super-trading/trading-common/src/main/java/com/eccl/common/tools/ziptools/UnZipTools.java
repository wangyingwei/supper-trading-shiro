package com.eccl.common.tools.ziptools;

import java.io.*;

/**
 * @author 李金涛
 * @version $Id: UnZipAuto.java,v 1.1.1.1 2009/06/04 02:34:05 liuchao Exp $
 * @since 1.0
 */
public class UnZipTools {
	String errMsg = "";

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	/*
	 * 传入需要解压缩的密码，解压缩的文件路径
	 * 
	 */
	public String unzipFileForDepass(String unzipFilePath, String password, String unzipToDir) throws Exception {

		File fileDirMake = new File(unzipToDir);
		fileDirMake.mkdirs();

		errMsg = "";
		Runtime rt = Runtime.getRuntime();
		String batFileScript = "";
		if (password.equals("")) {
			batFileScript += "unzip -P " + "123" + " ";
		} else {
			batFileScript += "unzip -P " + password + " ";
		}

		batFileScript += " -o -u  ";
		batFileScript += "-d  " + unzipToDir + " ";
		batFileScript += unzipFilePath;
		System.out.println(batFileScript);
		Process proc = rt.exec(batFileScript); // 要加start才會出現dos畫面，且才會真正執行runconsole.bat的指令
		System.out.println("#" + batFileScript + "#");

		StreamGobbler errorMsg = new StreamGobbler(proc.getErrorStream(), "ERROR");
		System.out.println("errorMsg.start1");
		errorMsg.start();
		System.out.println("errorMsg.start");

		boolean isFinishForReader = false;
		System.out.println("errorMsg.isFinish-Start");
		isFinishForReader = errorMsg.isFinish();
		System.out.println("errorMsg.isFinish-End");
		int sleepTimes = 0;
		while (!isFinishForReader) {
			System.out.println("sleeping");
			Thread.currentThread().sleep(1000);
			sleepTimes++;
			System.out.println("start1");
			isFinishForReader = errorMsg.isFinish();
			System.out.println("start2");
		}
		System.out.println("sleepTimes:" + sleepTimes);
		errMsg += errorMsg.getStreamMsg();

		System.out.println("errMsg:" + errMsg);
		if (!errMsg.equals("")) {
			if (errMsg.indexOf("incorrect password") != -1) {
				errMsg = "密码错误";

			} else if (errMsg.indexOf("password:") != -1) {
				errMsg = "密码需要填写";
			} else if (errMsg.indexOf("Either this file is not  a zipfile") != -1) {
				errMsg = "被解压缩的文件不是zip文档";
			} else {
				errMsg = "未知错误：" + errMsg;
			}
		}

		return errMsg;
	}

	/*
	 * 获得文件扩展名
	 * 
	 */
	public String getFileNameExcludeExt(String fileName) {
		String fileNameExcludeExt = "";

		int pos = fileName.lastIndexOf(".");
		fileNameExcludeExt = fileName.substring(0, pos);
		return fileNameExcludeExt;

	}

	public static void main(String[] args) throws Exception {

		System.out.println(new String("1234567浣犲ソ1234567".getBytes("gbk"), "utf8"));

		if (true) {
			UnZipTools unZipTools = new UnZipTools();
			String unzipFilePath = "e:/testzip/abc.zip";

			String password = "welcome1";
			String unzipToDir = "E:/testzip/zipto";
			String errMsg = unZipTools.unzipFileForDepass(unzipFilePath, password, unzipToDir);
			System.out.println("errMsg:" + errMsg);
			System.out.println("ok");
			return;
		}
	}

}
