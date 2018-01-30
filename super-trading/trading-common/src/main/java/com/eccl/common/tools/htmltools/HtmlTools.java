package com.eccl.common.tools.htmltools;

import com.eccl.common.util.stringutil.StringUtil;

public class HtmlTools {

	/**
	 * Html编码化
	 * 
	 * @roseuid 4234F6700317
	 */
	public static String HTMLEncoder(String source, boolean replaceBr) {
		String html = source.replaceAll("\"", "&quot;");
		html = html.replaceAll("<", "&lt;");
		html = html.replaceAll(">", "&gt;");
		html = html.replaceAll(" ", "&nbsp;");
		if (replaceBr) {
			html = html.replaceAll("\r\n", "<br>");
			html = html.replaceAll("\n", "<br>");
			html = html.replaceAll("\r", "<br>");
		}
		return html;
	}

	/**
	 * Html编码化
	 * 
	 * @roseuid 4234F6700317
	 */
	public static String HTMLEncoder(String source) {
		return HTMLEncoder(source, false);
	}

	/**
	 * Html编码化
	 * 
	 * @roseuid 4234F6700317
	 */
	public static String HTMLEncoder(Object source) {
		return HTMLEncoder(StringUtil.objectToString(source), false);
	}
}
