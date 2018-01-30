package com.eccl.common.tools.dbtools;

import com.eccl.common.config.appconfig.AppConfig;

public class DbTools {

	public static String getDbNowDate() throws Exception {
		AppConfig ac = AppConfig.getInstance();
		if (ac.getEcclDbType().equals("MSSQL"))
			return " getDate() ";
		else if (ac.getEcclDbType().equals("ORACLE"))
			return " sysdate ";
		else if (ac.getEcclDbType().equals("MYSQL"))
			return " now()  ";
		else
			throw new Exception("不支持的数据库!");
	}
}
