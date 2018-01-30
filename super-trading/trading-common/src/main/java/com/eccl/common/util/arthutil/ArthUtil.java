package com.eccl.common.util.arthutil;

import java.math.BigDecimal;

/**
 * 提供科学计算，已经四舍五入
 * 
 * @version @(#)Arith.java 1.0 03/05/06
 * @author 尹鸿涛 Copyright 2003 eccl.com.cn. All rights reserved. 河北晨砻科技股份有限公司
 */
public class ArthUtil {

	private static final int DEF_DIV_SCALE = 10;

	/**
	 * 不能创建类实例
	 */
	private ArthUtil() {

	}

	/**
	 * 加法运算
	 * 
	 * @param 两个数
	 * @return 运算结果
	 */
	public static double add(double d, double d1) {
		BigDecimal bigdecimal = new BigDecimal(Double.toString(d));
		BigDecimal bigdecimal1 = new BigDecimal(Double.toString(d1));
		return bigdecimal.add(bigdecimal1).doubleValue();
	}

	/**
	 * 减法运算
	 * 
	 * @param 两个数
	 * @return 运算结果
	 */
	public static double sub(double d, double d1) {
		BigDecimal bigdecimal = new BigDecimal(Double.toString(d));
		BigDecimal bigdecimal1 = new BigDecimal(Double.toString(d1));
		return bigdecimal.subtract(bigdecimal1).doubleValue();
	}

	/**
	 * 乘法运算
	 * 
	 * @param 两个数
	 * @return 运算结果
	 */
	public static double mul(double d, double d1) {
		BigDecimal bigdecimal = new BigDecimal(Double.toString(d));
		BigDecimal bigdecimal1 = new BigDecimal(Double.toString(d1));
		return bigdecimal.multiply(bigdecimal1).doubleValue();
	}

	/**
	 * 除法运算
	 * 
	 * @param 两个数
	 * @return 运算结果
	 */
	public static double div(double d, double d1) {
		return div(d, d1, 10);
	}

	/**
	 * 除法运算
	 * 
	 * @param 两个数，前两个为参与运算的数，最后是精度
	 * @return 运算结果
	 */
	public static double div(double d, double d1, int i) {
		if (i < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		} else {
			BigDecimal bigdecimal = new BigDecimal(Double.toString(d));
			BigDecimal bigdecimal1 = new BigDecimal(Double.toString(d1));
			return bigdecimal.divide(bigdecimal1, i, 4).doubleValue();
		}
	}

	/**
	 * 四舍五入
	 * 
	 * @param 前边是待处理的数，后边是精度
	 * @return 运算结果
	 */
	public static double round(double d, int i) {
		if (i < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		} else {
			BigDecimal bigdecimal = new BigDecimal(Double.toString(d));
			BigDecimal bigdecimal1 = new BigDecimal("1");
			return bigdecimal.divide(bigdecimal1, i, 4).doubleValue();
		}
	}
}