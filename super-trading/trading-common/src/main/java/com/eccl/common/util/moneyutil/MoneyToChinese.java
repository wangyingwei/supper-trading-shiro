package com.eccl.common.util.moneyutil;

public class MoneyToChinese {

	String HanDigiStr[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
	String HanDiviStr[] = { "", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾",
			"佰", "仟", "万", "拾", "佰", "仟" };

	public MoneyToChinese() {
		
	}

	public static void main(String args[]) {
		MoneyToChinese money1 = new MoneyToChinese();
		System.out.println(-100000000000000D + money1.NumToRMBStr(-100000000000000D));
		System.out.println(100000000000000D + money1.NumToRMBStr(100000000000000D));
	}

	/**
	 * 人民币本地化处理
	 * 
	 * 
	 */
	String PositiveIntegerToHanStr(String s) {
		String s1 = "";
		boolean flag = false;
		boolean flag1 = false;
		int i = s.length();
		if (i > 15){
			return "数值过大!";
		}
		for (int k = i - 1; k >= 0; k--) {
			if (s.charAt(i - k - 1) == ' '){
				continue;
			}
			int j = s.charAt(i - k - 1) - 48;
			if (j < 0 || j > 9){
				return "输入含非数字字符!";
			}
			if (j != 0) {
				if (flag){
					s1 = s1 + HanDigiStr[0];
				}
				if (j != 1 || k % 4 != 1 || k != i - 1){
					s1 = s1 + HanDigiStr[j];
				}
				s1 = s1 + HanDiviStr[k];
				flag1 = true;
			} else if (k % 8 == 0 || k % 8 == 4 && flag1) {
				s1 = s1 + HanDiviStr[k];
				flag1 = false;
			}
			if (k % 8 == 0 || k % 8 == 4){
				flag1 = false;
			}
			flag = j == 0 && k % 4 != 0;
		}

		if (s1.length() == 0){
			return HanDigiStr[0];
		} else {
			return s1;
		}
	}

	/**
	 * 获取人民币的汉字表示形式
	 * 
	 */
	public String NumToRMBStr(double d) {
		String s = "";
		String s1 = "";
		if (d < 0.0D) {
			d = -d;
			s = "负";
		}
		if (d > 100000000000000D || d < -100000000000000D){
			return "数值位数过大!";
		}
		long l2 = Math.round(d * 100D);
		long l1 = l2 / 100L;
		long l = l2 % 100L;
		int i = (int) l / 10;
		int j = (int) l % 10;
		if (i == 0 && j == 0) {
			s1 = "整";
		} else {
			s1 = HanDigiStr[i];
			if (i != 0){
				s1 = s1 + "角";
			}
			if (l1 == 0L && i == 0){
				s1 = "";
			}
			if (j != 0){
				s1 = s1 + HanDigiStr[j] + "分";
			}
		}
		return "" + s + PositiveIntegerToHanStr(String.valueOf(l1)) + "元" + s1;
	}
}
