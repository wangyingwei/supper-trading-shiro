package com.eccl.common.tools.ziptools;

import java.io.*;

import java.io.*;

class StreamGobbler extends Thread {

	Thread parentThread = null;

	boolean isFinish = false;
	InputStream is;
	String type;
	String streamMsg = "";

	StreamGobbler(InputStream is, String type) {
		this.is = is;
		this.type = type;
		this.parentThread = parentThread;
	}

	public void run() {
		System.out.println("StreamGobbler Start");
		try {
			InputStreamReader isr = new InputStreamReader(is, "gb2312");

			BufferedReader br = new BufferedReader(isr);
			String line = null;

			System.out.println("开始读错误数据");
			int times = 0;
			while ((line = br.readLine()) != null) {
				System.out.println("times:" + times);
				System.out.println();
				streamMsg += line;
				System.out.println(type + ">" + "###" + line + "###");
				if (line.equals("")) {
					break;
				}
				System.out.println("times++");
				times++;
				System.out.println("br.readLine()");
				System.out.println("br.readLine()End");
			} 
			System.out.println("结束读错误数据");

		} catch (IOException ioe) {

			ioe.printStackTrace();
			streamMsg += ioe.getMessage();
		}

		isFinish = true;
		System.out.println("isFinish:" + isFinish);
	}

	public String getStreamMsg() {
		return streamMsg;
	}

	public void setStreamMsg(String streamMsg) {
		this.streamMsg = streamMsg;
	}

	public Thread getParentThread() {
		return parentThread;
	}

	public void setParentThread(Thread parentThread) {
		this.parentThread = parentThread;
	}

	public boolean isFinish() {
		return isFinish;
	}

	public void setFinish(boolean isFinish) {
		this.isFinish = isFinish;
	}
}
