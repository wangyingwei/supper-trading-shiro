package com.eccl.common.util.stringutil;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.eccl.common.util.objectUtil.ObjectUtil;

public class StringUtil {

	/**
	 * 字符串是否是数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 字符串是否是double
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDouble(String str) {
		Pattern pattern = Pattern.compile("^[0-9]+[.]?[0-9]*[0-9]$");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 对原始字符串（不包括中文标点符号）进行编码转换，如果失败，返回原始的字符串
	 * 
	 * @param s
	 *            原始字符串
	 * @param srcEncoding
	 *            源编码方式
	 * @param destEncoding
	 *            目标编码方式
	 * @return 转换编码后的字符串，失败返回原始字符串
	 */
	public static String transcodingFromTo(String s, String srcEncoding, String destEncoding) {
		try {
			return new String(s.getBytes(srcEncoding), destEncoding);
		} catch (UnsupportedEncodingException e) {
			return s;
		}
	}

	/**
	 * 根据某种编码方式将字节数组转换成字符串
	 * 
	 * @param b
	 *            字节数组
	 * @param encoding
	 *            编码方式
	 * @return 如果encoding不支持，返回一个缺省编码的字符串
	 */
	public static String byteStrToStr(byte[] b, String encoding) {
		try {
			return new String(b, encoding);
		} catch (UnsupportedEncodingException e) {
			return new String(b);
		}
	}

	/**
	 * 根据某种编码方式将字节数组转换成字符串
	 * 
	 * @param b
	 *            字节数组
	 * @param offset
	 *            要转换的起始位置
	 * @param len
	 *            要转换的长度
	 * @param encoding
	 *            编码方式
	 * @return 如果encoding不支持，返回一个缺省编码的字符串
	 */
	public static String byteStrToStr(byte[] b, int offset, int len, String encoding) {
		try {
			return new String(b, offset, len, encoding);
		} catch (UnsupportedEncodingException e) {
			return new String(b, offset, len);
		}
	}

	/**
	 * 根据指定的字符集，将字符串编码成 byte数组
	 * 
	 * @param data
	 *            String 字符串
	 * @param charsetName
	 *            String 字符集名
	 * @return byte[]
	 */
	public static byte[] strToByte(String data, String charsetName) {
		CharBuffer charBuffer = CharBuffer.wrap(data);
		Charset charset = Charset.forName(charsetName);
		CharsetEncoder encoder = charset.newEncoder();
		ByteBuffer byteBuffer = null;
		try {
			// 编码
			byteBuffer = encoder.encode(charBuffer);
			byte[] datab = byteBuffer.array();
			return datab;
		} catch (CharacterCodingException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * 将Object转成String
	 * 
	 * @param value
	 *            需要转换的值
	 * @return 转换后的值，值为NULL时返回空字符串
	 * @author 郭辉
	 */
	public static String objectToString(Object value) {
		if (value == null)
			return "";
		else
			return value.toString().trim();
	}

	/*
	 * 判断字符串是否全部为数字
	 * 
	 */
	public static boolean isAllNumber(String number) {
		boolean flag = true;
		String s = number;
		int len = s.length();
		// String numbers="0123456789";
		for (int i = 0; i < len; i++) {
			if (!(s.charAt(i) >= '0' && s.charAt(i) <= '9')) {
				flag = false;
				break;
				// end if if(!(s.charAt(i)>='0'&&s.charAt(i)<='9'))
			}
			// end for for(int i=0;i<len;i++)
		}
		return flag;
	}

	/**
	 * 将字符串的数组组合为","分隔的字符串
	 */
	public static String concatStrings(String[] ids) {
		if (ids == null || ids.length < 1) {
			return "";
		}
		String theResult = ",";
		for (int i = 0; i < ids.length; i++) {
			theResult += ids[i] + ",";
		}
		return theResult;
	}

	/**
	 * 将字符串数组向ArrayList里边写,相同的字符串不进行重写
	 * 
	 */
	public static ArrayList addStringArrayToArrayList(ArrayList al, String[] s1) {
		for (int i = 0; i < s1.length; i++) {
			String s = s1[i];
			boolean flag = true;
			for (int j = 0; j < al.size(); j++) {
				String tmp = (String) al.get(j);
				if (tmp.equals(s)) {
					flag = false;
				}
			}
			if (flag) {
				al.add(s);
			}
		}
		return al;

	}

	/**
	 * 将String转成int
	 * 
	 * @param value
	 *            需要转换的值
	 * @param defaultValue
	 *            转换失败后返回的值
	 * @return 转换后的值
	 * @author 郭辉
	 */
	public static int stringToInt(String value, int defaultValue) {
		try {
			return Integer.parseInt(value.trim());
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * 将String转成int
	 * 
	 * @param value
	 *            需要转换的值
	 * @return 转换后的值
	 * @author 郭辉
	 */
	public static int stringToInt(String value) throws Exception {
		return Integer.parseInt(value.trim());
	}

	/**
	 * 将String转成double
	 * 
	 * @param value
	 *            需要转换的值
	 * @param defaultValue
	 *            转换失败后返回的值
	 * @return 转换后的值
	 * @author 郭辉
	 */
	public static double stringToDouble(String value, double defaultValue) {
		try {
			return Double.parseDouble(value.toString().trim());
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * 将String转成double
	 * 
	 * @param value
	 *            需要转换的值
	 * @return 转换后的值
	 * @author 郭辉
	 */
	public static double stringToDouble(String value) throws Exception {
		return Double.parseDouble(value.toString().trim());
	}

	/**
	 * 截取给定长度字符串
	 * 
	 * @param value
	 *            字符串
	 * @param sublen
	 *            截断长度
	 * @return
	 */
	public static String getSubStringWithLength(Object value, int sublen) {
		if (value == null)
			return "";
		else {
			int len = value.toString().length();
			if (len > sublen) {
				return value.toString().substring(0, sublen) + "...";
			} else {
				return value.toString().trim();
			}
		}

	}

	/**
	 * 将String[]转成int[]
	 * 
	 * @param strArr
	 *            需要转换的值
	 * @return 转换后的值
	 * @author 郭辉
	 */
	public static int[] getIntArr(String[] strArr) throws Exception {
		if (strArr == null) {
			return null;
		}
		int[] intArr = new int[strArr.length];
		for (int i = 0; i < strArr.length; i++) {
			intArr[i] = stringToInt(strArr[i]);
		}

		return intArr;
	}

	/**
	 * 将字符串进行编码
	 * 
	 * @param str
	 * @return
	 * @author 郭辉
	 */
	public static String getEncodeStr(String str) {
		try {
			return java.net.URLEncoder.encode(str, "GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 将字符串转换为日期格式
	 * 
	 * @param str
	 * @param format
	 *            格式化格式：“yyyy-MM-dd”
	 * @return
	 * @throws Exception
	 */
	public static Date stringToDate(String str, String format) throws Exception {
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
			date = sdf.parse(str);
		} catch (Exception e) {
			throw e;
		}
		return date;
	}

	/**
	 * 将字符串转换为日期格式
	 * 
	 * @param str
	 * @param format
	 *            格式化格式：“yyyy-MM-dd”
	 * @param defauleValue
	 * @return
	 */
	public static Date stringToDate(String str, String format, Date defauleValue) {
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
			date = sdf.parse(str);
		} catch (Exception e) {
			date = defauleValue;
		}
		return date;
	}

	/**
	 * @author weifeng 字符串数组链接成字符串
	 * @param str
	 *            字符串数组
	 * @param split
	 *            链接符
	 * @return 链接后的字符串
	 */
	public static String ConStringArray(String[] str, String split) {
		String ret = "";

		if (str != null) {
			for (int i = 0; i < str.length; i++) {
				ret += str[i] + split;
			}
			ret = ret.substring(0, ret.length() - 1);
		}

		return ret;

	}

	/**
	 * string 转 date
	 * 
	 * @param dateString
	 * @param dateFormat
	 * @return
	 */
	public static java.util.Date parse(String dateString, String dateFormat) {
		if (ObjectUtil.isNullOrEmptyStr(dateString)) {
			return null;
		}
		DateFormat df = new SimpleDateFormat(dateFormat);
		try {
			return df.parse(dateString);
		} catch (Exception e) {
			String errorInfo = "cannot use dateformat:" + dateFormat + " parse datestring:" + dateString;
			throw new IllegalArgumentException(errorInfo, e);
		}
	}

	/**
	 * 字符串替换函数，把source中的fromStr替换为toStr
	 * 
	 * @roseuid 4227B1730376
	 */
	public static String replaceAll(String source, String fromStr, String toStr) {
		String strDest = "";
		int intFromLen = fromStr.length();
		int intPos;

		while ((intPos = source.indexOf(fromStr)) != -1) {
			strDest = strDest + source.substring(0, intPos);
			strDest = strDest + toStr;
			source = source.substring(intPos + intFromLen);
		}
		strDest = strDest + source;

		return strDest;

	}
	
	
	/**
	 * 用一个字符串替代另外一个字符串中的子字符串
	 * 
	 * 
	 */
	public static String replaceSubString(String s, String s1, String s2)
    {
        String s3 = "";
        int i = s1.length();
        int j;
        while((j = s.indexOf(s1)) != -1) 
        {
            s3 = s3 + s.substring(0, j);
            s3 = s3 + s2;
            s = s.substring(j + i);
        }
        s3 = s3 + s;
        return s3;
    }

	/**
	 * 判断字符串是否是数字
	 * 
	 * 
	 */
	public static boolean isNumber(String s)
    {
        String s1 = "0.123456789";
        for(int i = 0; i < s.length(); i++){
            if(s1.indexOf(s.charAt(i)) < 0){
                return false;
            }
        }
        return s.lastIndexOf(".") == s.indexOf(".");
    }
	
	public static void main(String[] args) {
		String numberStr = "123456";
		System.out.println(StringUtil.isNumeric(numberStr));

		String numberStr1 = "3.564";
		System.out.println(StringUtil.isDouble(numberStr1));

		String utf8Str = "你好中国";
		String gbkStr = StringUtil.transcodingFromTo(utf8Str, "utf-8", "gbk");
		System.out.println(gbkStr);
		System.out.println(StringUtil.transcodingFromTo(gbkStr, "gbk", "utf-8"));

		byte[] byteArray = new byte[] { 87, 79, 87, 46, 46, 46 };
		String arrGbkStr = StringUtil.byteStrToStr(byteArray, "gbk");
		System.out.println(arrGbkStr);
		System.out.println(StringUtil.byteStrToStr(byteArray, 0, 2, "gbk"));

		byte[] byteArrayFromStr = strToByte(arrGbkStr, "gbk");
		for (int i = 0; i < byteArrayFromStr.length; i++) {
			byte num = byteArrayFromStr[i];
			System.out.println(num);
		}
	}
}
