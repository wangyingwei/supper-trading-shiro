package com.eccl.common.tools.validatetools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.eccl.common.util.stringutil.StringUtil;

public class ValidateTools {

	/**
	 * 判断是否为有效身份证
	 * 
	 */
	public static boolean isValidIDCard(String cardNumber) {
		boolean flag = false;
		String s = cardNumber.replaceAll("x", "0").replaceAll("X", "0");
		int len = s.length();
		// String numbers="0123456789";
		if (len == 15 || len == 18) {
			if (StringUtil.isAllNumber(s)) {
				flag = true;
				// end if(isAllNumber(s))
			}
			// end if(len==15)
		}
		return flag;
	}

	/**
	 * 验证手机号码是否符合规范
	 * 
	 * @param mobiles
	 *            手机号码
	 * @return true/false
	 */
	public static boolean isMobileNO(String mobiles) {
		// Pattern p =
		// Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Pattern p = Pattern.compile("^(1[0-9])\\d{9}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	/**
	 * 验证邮箱是否符合规范
	 * 
	 * @param email
	 *            邮箱
	 * @return true/false
	 */
	public static boolean isEmail(String email) {
		String str = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);
		return m.matches();
	}
}
