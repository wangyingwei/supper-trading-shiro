package com.eccl.common.tools.charactertools;

/**
 * @author gaohui
 * @version $Id: CharacterTools.java, v 1.1.1.1 2013/08/22 $
 * @since 1.0
 */
public class CharacterTools {

	public CharacterTools() {
	}

	public static String ExportCharacterReplace(String value) {

		String returnVlue = "";

		returnVlue = value.replaceAll("m&sup2;", "㎡");

		returnVlue = value.replaceAll("m&sup3;", "m³");

		return returnVlue;
	}

	public static String ImportCharacterReplace(String value) {

		String returnVlue = "";

		returnVlue = value.replaceAll("㎡", "m&sup2;");

		returnVlue = value.replaceAll("m³", "m&sup3;");

		return returnVlue;
	}

}