package com.eccl.common.util.iputil;

import javax.servlet.http.HttpServletRequest;

import com.eccl.common.tools.mobiletools.MobileTools;

public class IpUtil {
    
	/**
	 * 从ip的字符串形式得到字节数组形式
	 * 
	 * @param ip
	 *            字符串形式的ip
	 * @return 字节数组形式的ip
	 */
	public static byte[] getIpByteArrayFromString(String ip) {
		byte[] ret = new byte[4];
		java.util.StringTokenizer st = new java.util.StringTokenizer(ip, ".");
		try {
			ret[0] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);
			ret[1] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);
			ret[2] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);
			ret[3] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return ret;
	}

	/**
	 * @param ip
	 *            ip的字节数组形式
	 * @return 字符串形式的ip
	 */
	public static String getIpStringFromBytes(byte[] ip) {
		StringBuffer sb = new StringBuffer();
		sb.append(ip[0] & 0xFF);
		sb.append('.');
		sb.append(ip[1] & 0xFF);
		sb.append('.');
		sb.append(ip[2] & 0xFF);
		sb.append('.');
		sb.append(ip[3] & 0xFF);
		return sb.toString();
	}
	
	/**
	 * 获取客户端IP地址
	 * @param request
	 * @return IP
	 */
	public static ClientVO getClientVO(HttpServletRequest request){
		ClientVO clientVO = new ClientVO();
		String teminalID = getIP(request);
		clientVO.setIp(teminalID);
		
		// 获取MAC地址
		clientVO.setMac("");
		
		// 获取IP所在地址
		MobileTools addressUtils = new MobileTools();
		clientVO.setIpAddress(addressUtils.getAddress(teminalID));
		
		return clientVO;
	}
	
	public static String getIP(HttpServletRequest request){
		String teminalID = request.getHeader("x-forwarded-for");
		
		if (teminalID == null || teminalID.length() == 0 || "unknown".equalsIgnoreCase(teminalID)) 
		{
			teminalID = request.getHeader("Proxy-Client-IP");
		}
		
		if (teminalID == null || teminalID.length() == 0 || "unknown".equalsIgnoreCase(teminalID)) 
		{
			teminalID = request.getHeader("WL-Proxy-Client-IP");
		}

		if (teminalID == null || teminalID.length() == 0 || "unknown".equalsIgnoreCase(teminalID)) 
		{
			teminalID = request.getHeader("HTTP_CLIENT_IP");
		}
		
		if (teminalID == null || teminalID.length() == 0 || "unknown".equalsIgnoreCase(teminalID)) 
		{
			teminalID = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
			
		if (teminalID == null || teminalID.length() == 0 || "unknown".equalsIgnoreCase(teminalID)) 
		{
			teminalID = request.getRemoteAddr();
		}
		
		// 截取首位有效IP地址
		if (teminalID != null && teminalID.length() > 15 && teminalID.indexOf(",") > 0)
		{
			teminalID = teminalID.substring(0, teminalID.indexOf(","));
		}
		return teminalID;
	}
	
	public static void main(String args[]) {
		byte[] a = getIpByteArrayFromString("127.0.0.1");
		for (int i = 0; i < a.length; i++){
			System.out.println(a[i]);
		}
		System.out.println(getIpStringFromBytes(a));
	}
	
}
