package com.eccl.common.util.dateutil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

	/*
	 * 比较两个日期的前后关系
	 * 
	 * @param date1比较前后关系的日期 date2比较前后关系的日期
	 * 
	 * @return 返回前后关系代码 1 date1 在date2前 -1 date1 在date2前 0 date1 和date2为同一天
	 * 
	 */
	public static int compareDate(String date1, String date2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		try {
			Date dt1 = df.parse(date1);
			Date dt2 = df.parse(date2);
			if (dt1.getTime() > dt2.getTime()) {
				System.out.println("dt1在dt2前");
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				System.out.println("dt1在dt2后");
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	/*
	 * 比较两个日期间隔多少天
	 * 
	 * @param front第一个日期参数 after第er个日期参数
	 * 
	 * @return 返回间隔的日期天数
	 * 
	 */
	public static long getDays(String front, String after) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date one;
		Date two;
		long days = 0;
		try {
			one = df.parse(front);
			two = df.parse(after);
			long time1 = one.getTime();
			long time2 = two.getTime();
			long diff;
			if (time1 < time2) {
				diff = time2 - time1;
			} else {
				diff = time1 - time2;
			}
			days = diff / (1000 * 60 * 60 * 24);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return days;
	}

	/*
	 * 获取系统时间
	 * 
	 * @return 获取系统时间
	 * 
	 */
	public static String getSysdateString() throws Exception {
		String sdate;
		Date ddate = new Date();
		sdate = (new SimpleDateFormat("yyyy-MM-dd HH:mm")).format(ddate);
		return sdate;
	}

	/**
	 * 返回如yyyy-mm-dd格式日期,不足位年加20，月和日加0,如2013-01-01
	 *
	 * @parma dateStr 需要处理的日期
	 * @parma formatStr 需要处理的日期的分隔符，不可为""
	 * @parma returnFormatStr 需要返回的日期分隔符
	 *
	 * @return returnFormatStr分割的全格式日期，如2013-01-01
	 */
	public static String getFullDateStr(String dateStr, String formatStr, String returnFormatStr) {

		String[] s = null;
		s = dateStr.split(formatStr);
		if (s[0].length() == 2) {
			s[0] = "20" + s[0];
		}

		if (s[1].length() == 1) {
			s[1] = "0" + s[1];
		}

		if (s[2].length() == 1) {
			s[2] = "0" + s[2];
		}

		return s[0] + returnFormatStr + s[1] + returnFormatStr + s[2];
	}

	/**
	 * 返回如23:03:03格式时间,不足位加0
	 * 
	 * @parma timeStr 需要处理的时间
	 * @parma formatStr 需要处理的时间的分隔符，不可为""
	 * @parma returnFormatStr 需要返回的时间分隔符，如":"
	 *
	 * @return returnFormatStr分割的全格式时间，如23:03:03
	 */
	public static String getFullTimeStr(String timeStr, String formatStr, String returnFormatStr) {
		String[] s = null;
		String return_s = "";

		s = timeStr.split(formatStr);

		for (int i = 0; i < s.length; i++) {
			if (s[i].length() == 1) {
				s[i] = "0" + s[i];
			}
		}

		for (int i = 0; i < s.length; i++) {
			return_s += s[i] + (i == s.length - 1 ? "" : returnFormatStr);
		}

		return return_s;
	}

	/**
	 * 传入字符串传出数字，传入为YYYY-MM-DD格式的日期，返回YYYYMMDD格式的数字
	 * 
	 * @roseuid 41254983032C
	 */
	public static long StrDateToInt(String strDate) {
		strDate = strDate.replaceAll("-", "");
		strDate = strDate.replaceAll(" ", "");
		strDate = strDate.replaceAll(":", "");
		return Long.parseLong(strDate);

	}

	/**
	 * 判断某个日期是否在传入的时间段内，传入时间格式为YYYY-MM-DD
	 *
	 */
	public static boolean isBetweenDate(String sDate, String eDate) {
		long intsDate = StrDateToInt(sDate);
		long inteDate = StrDateToInt(eDate);

		java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = new java.util.Date();
		String nDate = df.format(date);

		long intnDate = StrDateToInt(nDate);
		if (intnDate >= intsDate && intnDate <= inteDate)
			return true;
		else
			return false;
	}

	/**
	 * 传入2个日期，判断是否第一个日期大于第二个日期 传入日期格式 YYYY-MM-DD hh24:mi
	 */
	public static boolean isDate1Big(String sDate, String eDate) {
		long intsDate = StrDateToInt(sDate);
		long inteDate = StrDateToInt(eDate);
		if (intsDate > inteDate)
			return true;
		else
			return false;
	}

	/**
	 * 判断某一天是否是将来的某一天
	 * 
	 */
	public static boolean isDateFuture(String sDate) {
		java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
		java.util.Date date = new java.util.Date();
		String nDate = df.format(date);

		return isDate1Big(sDate, nDate);
	}

	/**
	 * 判断某一天是否是以前的某一天
	 * 
	 */
	public static boolean isDateHistory(String sDate) {
		java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
		java.util.Date date = new java.util.Date();
		String nDate = df.format(date);

		return !isDate1Big(sDate, nDate);

	}

	/**
	 * 输入一个字符串，返回对应日期yyy-MM-dd HH:mm:ss
	 * 
	 */
	public static String formatDateString(String dateString) {

		String dateStringFormat = "";
		java.util.Date date = new java.util.Date();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format3 = new SimpleDateFormat("yyyy-MM-dd HH");
		SimpleDateFormat format4 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			date = format1.parse(dateString);
			dateStringFormat = format1.format(date);
		} catch (Exception e1) {
			try {
				date = format2.parse(dateString);
				dateStringFormat = format1.format(date);
			} catch (Exception e2) {

				try {
					date = format3.parse(dateString);
					dateStringFormat = format1.format(date);
				} catch (Exception e3) {
					try {
						date = format4.parse(dateString);
						dateStringFormat = format1.format(date);
					} catch (Exception e4) {
					}
				}
			}
		}
		return dateStringFormat;
	}

	/**
	 * 获得当前月的第一天 :格式:yyyy-MM-dd
	 * 
	 */
	public static String getFirstDayStringOfCurrentMonth() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		java.util.Date dateNow = new java.util.Date();
		calendar.setTime(dateNow);
		int firstDateInt = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, firstDateInt);

		String dateString = sdf.format(calendar.getTime());
		return dateString;

	}

	/**
	 * 获得当前月的最后一天 :格式:yyyy-MM-dd
	 * 
	 */
	public static String getLastDayStringOfCurrentMonth() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		java.util.Date dateNow = new java.util.Date();
		calendar.setTime(dateNow);
		int firstDateInt = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, firstDateInt);

		String dateString = sdf.format(calendar.getTime());
		return dateString;

	}

	/**
	 * 获得上个月的第一天 :格式:yyyy-MM-dd
	 * 
	 */
	public static String getFirstDayStringOfPreviousMonth() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);

		String dateString = sdf.format(calendar.getTime());
		return dateString;

	}

	/**
	 * 获得上个月的最后一天 :格式:yyyy-MM-dd
	 * 
	 */
	public static String getLastDayStringOfPreviousMonth() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DATE, -1);

		String dateString = sdf.format(calendar.getTime());
		return dateString;

	}

	/**
	 * 获得当前时间加上一个月的时间
	 * 
	 */
	public static String getTimeAddOneMonth(java.util.Date date)

	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int month = calendar.get(Calendar.MONTH);
		month++;
		calendar.set(Calendar.MONTH, month);

		date = calendar.getTime();
		SimpleDateFormat sdateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = sdateFormat.format(date);
		return dateString;

	}

	/**
	 * 得到剩余时间
	 * 
	 */
	public static String getRemainTime(String closeDate) throws Exception {
		String remainTimeStr = null;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		java.util.Date oldDate = null;
		try {
			oldDate = sdf.parse(closeDate);

			long remainTime = oldDate.getTime() - System.currentTimeMillis();
			if (remainTime > 0) {
				long remainMinute = (remainTime / 1000) / 60;
				long remainHour = remainMinute / 60;
				remainMinute = remainMinute % 60;
				long remainDay = remainHour / 24;
				remainHour = remainHour % 24;
				if (remainDay > 0) {
					remainTimeStr = remainDay + "天" + remainHour + "小时" + remainMinute + "分钟";
				} else if (remainHour > 0) {
					remainTimeStr = remainHour + "小时" + remainMinute + "分钟";
				} else {
					remainTimeStr = remainMinute + "分钟";
				}
			} else {
				remainTimeStr = "0分钟";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			remainTimeStr = e.getMessage() + "------得到的时间格式不正确！";
			e.printStackTrace();
		}
		return remainTimeStr;
	}

	/**
	 * 校验是否是日期类型
	 * 
	 * @param str
	 * @param format
	 *            格式化格式：“yyyy-MM-dd”
	 * @return
	 */
	public static boolean checkDate(String str, String format) {
		boolean returnValue = false;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
			sdf.parse(str);
			returnValue = true;
		} catch (Exception e) {

		}
		return returnValue;
	}

	/**
	 * 获取加上num天后的日期（yyyy-MM-dd）
	 */
	public static String dayAddition(int num, String errValue) {
		try {
			SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date = timeformat.parse("1900-1-1");
			Calendar a = Calendar.getInstance();
			a.setTime(date);
			a.add(Calendar.DATE, (num - 2));
			return timeformat.format(a.getTime());
		} catch (Exception e) {
			e.toString();
			return errValue;
		}
	}

	/**
	 * date 转 string
	 * 
	 * @param date
	 * @param dateFormat
	 * @return
	 */
	public static String format(java.util.Date date, String dateFormat) {
		if (date == null)
			return null;
		return new SimpleDateFormat(dateFormat).format(date);
	}

	/**
	 * 计算两个日期之间相隔的天数
	 * 
	 */
	public static int dateDiffOfDays(Date oldDate, Date newDate) {
		int days = 0;
		Calendar calo = Calendar.getInstance();
		Calendar caln = Calendar.getInstance();
		calo.setTime(oldDate);
		caln.setTime(newDate);
		int oday = calo.get(Calendar.DAY_OF_YEAR);
		int nyear = caln.get(Calendar.YEAR);
		int oyear = calo.get(Calendar.YEAR);
		while (nyear > oyear) {
			calo.set(Calendar.MONTH, 11);
			calo.set(Calendar.DATE, 31);
			days = days + calo.get(Calendar.DAY_OF_YEAR);
			oyear = oyear + 1;
			calo.set(Calendar.YEAR, oyear);
		}
		int nday = caln.get(Calendar.DAY_OF_YEAR);
		days = days + nday - oday;
		return days;
	}

	/**
	 * 计算两个日期之间相隔的天数
	 * 
	 */
	public static int dateDiffOfDays(String oldDate, String newDate) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date oDate = sdf.parse(oldDate);
		Date nDate = sdf.parse(newDate);
		return dateDiffOfDays(oDate, nDate);
	}

	/**
	 * 计算传入的日期与现在相隔的天数
	 * 
	 */
	public static int dateDiffOfDaysNow(Date date) {
		Date curDate = new Date();
		return dateDiffOfDays(date, curDate);
	}

	/**
	 * 计算传入的日期与现在相隔的天数
	 * 
	 */
	public static int dateDiffOfDaysNow(String date) throws Exception {
		Date curDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return dateDiffOfDays(sdf.parse(date), curDate);
	}

	/**
	 * 运算某个日期加减运算后的日期
	 * 
	 */
	public static Date addDateToDate(Date date, String type, int addNum) {
		return addDateToDate(date, getIType(type), addNum);
	}

	/**
	 * 运算某个日期加减运算后的日期
	 * 
	 */
	public static Date addDateToDate(Date date, int type, int addNum) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(type, addNum);
		return calendar.getTime();
	}

	/**
	 * 运算某个日期加减运算后的日期
	 * 
	 */
	public static String addDate(Date date, int type, int addNum, String df) {
		SimpleDateFormat sdf = new SimpleDateFormat(df);
		return sdf.format(addDateToDate(date, type, addNum));
	}

	/**
	 * 运算某个日期加减运算后的日期
	 * 
	 */
	public static String addDate(Date date, int type, int addNum) {
		String df = "yyyy-MM-dd";
		if (type == Calendar.HOUR)
			df = "yyyy-MM-dd HH";
		if (type == Calendar.MINUTE)
			df = "yyyy-MM-dd HH:mm";
		return addDate(date, type, addNum, df);
	}

	/**
	 * 运算某个日期加减运算后的日期
	 * 
	 */
	public static String addDate(Date date, String type, int addNum, String df) {
		return addDate(date, getIType(type), addNum, df);
	}

	/**
	 * 获取日期的类型
	 * 
	 */
	public static int getIType(String type) {
		int iType = Calendar.DATE;
		if (type.equals("M") || type.equals("月")) {
			iType = Calendar.MONTH;
		} else if (type.equals("Y") || type.equals("年")) {
			iType = Calendar.YEAR;
		} else if (type.equals("D") || type.equals("天")) {
			iType = Calendar.DATE;
		} else if (type.equals("H") || type.equals("小时")) {
			iType = Calendar.HOUR;
		} else if (type.equals("m") || type.equals("分钟")) {
			iType = Calendar.MINUTE;
		} else if (type.equals("W") || type.equals("星期") || type.equals("周")) {
			iType = Calendar.WEEK_OF_MONTH;
		}
		return iType;
	}

	/**
	 * 运算某个日期加减运算后的日期
	 * 
	 */
	public static String addDate(Date date, String type, int addNum) {
		return addDate(date, getIType(type), addNum);
	}

	/**
	 * 运算某个日期加减运算后的日期
	 * 
	 */
	public static String addDate(String type, int addNum) {
		Date curDate = new Date();
		return addDate(curDate, type, addNum);
	}

	/**
	 * 运算某个日期加减运算后的日期
	 * 
	 */
	public static String addDate(String type, int addNum, String df) {
		Date curDate = new Date();
		return addDate(curDate, type, addNum, df);
	}

	/**
	 * 翻译数据库的时间格式到java语言的时间格式
	 * 
	 */
	public static String getJavaDateFormat(String fm) {
		if (fm.length() == "yyyy-mm-dd hh24:mi:ss".length()) {
			return "yyyy-MM-dd HH:mm:ss";
		}
		if (fm.length() == "yyyy-mm-dd hh24:mi".length()) {
			return "yyyy-MM-dd HH:mm";
		}
		if (fm.length() == "yyyy-mm-dd hh24".length()) {
			return "yyyy-MM-dd HH";
		}
		if (fm.length() == "yyyy-mm-dd".length()) {
			return "yyyy-MM-dd";
		}
		if (fm.length() == "yyyy-mm".length()) {
			return "yyyy-MM";
		}
		if (fm.length() == "yyyy".length()) {
			return "yyyy";
		}
		return "yyyy-MM-dd";
	}

	/**
	 * 获得日期，参数为当前计算机时间和GMT时间(格林威治时间)1970年1月1号0时0分0秒所差的毫秒数
	 * 
	 */
	public static Date getDate(long ldate) {
		Date date = new Date(ldate);
		return date;
	}

	/**
	 * 获得日期，参数为字符串
	 * 
	 */
	public static Date getDate(String dateStr) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = sdf.parse(dateStr);
		return date;
	}

	/**
	 * 获得日期，参数为当前计算机时间和GMT时间(格林威治时间)1970年1月1号0时0分0秒所差的毫秒数和日期格式
	 * 
	 */
	public static String getDateStr(long ldate, String df) {
		SimpleDateFormat sdf = new SimpleDateFormat(df);
		return sdf.format(getDate(ldate));
	}

	/**
	 * 获得日期，参数为当前计算机时间和GMT时间(格林威治时间)1970年1月1号0时0分0秒所差的毫秒数
	 * 
	 */
	public static String getDateStr(long ldate) {
		return getDateStr(ldate, "yyyy-MM-dd HH:mm:ss");
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			System.out.println(compareDate("2015-09-29 14:55", "2015-09-28 14:53"));
			System.out.println(getDays("2015-09-29 14:55", "2015-09-26 14:53"));
			System.out.println(getSysdateString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}