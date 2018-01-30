package com.eccl.common.util.doubleUtil;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class DoubleUtil {

	/**
	 * 格式化输出double，小数位全是0则不返回
	 * 
	 * @param maximumFractionDigits
	 *            精度位数
	 * @param value
	 *            需要格式化的值
	 * @param defauleValue
	 *            异常返回值
	 * @return 格式化后的值
	 * @author 郭辉
	 */
	public static String getStr(int maximumFractionDigits, double value, String defauleValue) {
		try {
			String temp = "";
			for (int i = 0; i < maximumFractionDigits; i++) {
				temp += "#";
			}
			DecimalFormat decimalformat = new DecimalFormat("#####0." + temp);
			decimalformat.setMaximumFractionDigits(maximumFractionDigits);
			decimalformat.setRoundingMode(RoundingMode.HALF_UP);
			double d = Double.valueOf(value).doubleValue();
			return decimalformat.format(d);
		} catch (Exception e) {
			e.toString();
			return defauleValue;
		}
	}

	/**
	 * 格式化输出double，小数位全是0也照常返回
	 * 
	 * @param maximumFractionDigits
	 *            精度位数
	 * @param value
	 *            需要格式化的值
	 * @param defauleValue
	 *            异常返回值
	 * @return 格式化后的值
	 * @author 郭辉
	 */
	public static String getStr2(int maximumFractionDigits, double value, String defauleValue) {
		try {
			String temp = "";
			for (int i = 0; i < maximumFractionDigits; i++) {
				temp += "0";
			}
			DecimalFormat decimalformat = new DecimalFormat("#####0." + temp);
			decimalformat.setMaximumFractionDigits(maximumFractionDigits);
			double d = Double.valueOf(value).doubleValue();
			return decimalformat.format(d);
		} catch (Exception e) {
			e.toString();
			return defauleValue;
		}
	}

}
