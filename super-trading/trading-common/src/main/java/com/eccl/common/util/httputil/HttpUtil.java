package com.eccl.common.util.httputil;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;

/**
 * http 请求工具类
 * @author kanly
 *
 */
public class HttpUtil {
	
	/**
	 * GET请求
	 * @param url  URLEncoder.encode
	 * @return
	 * @throws IOException 
	 */
	public static byte[] requestGet(String url) throws IOException{
		
		URL getUrl = new URL(url); 
		HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
		connection.connect(); 
		InputStream in = connection.getInputStream();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		IOUtils.copy(in, out);
		in.close();
		connection.disconnect();
		
		byte[] datas = out.toByteArray();
		out.flush();
		out.close();
		return datas;
	}
	
	/**
	 * POST 请求
	 * @param url
	 * @param params URLEncoder.encode
	 * @return
	 * @throws IOException
	 */
	public static byte[] requestPost(String url,byte[] params) throws IOException{
		 URL postUrl = new URL(url); 
		 HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection(); 
		 connection.setDoOutput(true);   
		 connection.setDoInput(true); 
		 connection.setRequestMethod("POST"); 
		 //Post 请求不能使用缓存 
		 connection.setUseCaches(false);
		 connection.setInstanceFollowRedirects(true); 
		 connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded"); 
		 
		 //connection.connect(); connection.getOutputStream()会隐含的进行调用 connect()，所以这里可以省略
	     DataOutputStream out = new DataOutputStream(connection.getOutputStream());
	     out.write(params);
         out.flush(); 
         out.close();
         
        InputStream in = connection.getInputStream();
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		IOUtils.copy(in, byteOut);
		in.close();
		connection.disconnect();
	
		byte[] datas = byteOut.toByteArray();
		byteOut.flush();
		byteOut.close();
		return datas;
	}
}