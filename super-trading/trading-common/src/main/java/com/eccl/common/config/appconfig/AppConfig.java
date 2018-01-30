package com.eccl.common.config.appconfig;

import java.util.HashMap;
import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author 尹鸿涛
 * @version $Id: AppConfig.java,v 1.1.1.1 2009/06/04 02:34:01 liuchao Exp $
 * @since 1.0
 */
public class AppConfig {

	DruidDataSource dataSource = new DruidDataSource();

	/**
	 * 数据库连接字符串
	 */

	private String ecclDbConnStr = "";

	/**
	 * 数据库用户名
	 */
	private String ecclDbUser = "";

	/**
	 * 数据库密码
	 */
	private String ecclDbPwd = "";

	/**
	 * 数据库类型，大写
	 */
	private String ecclDbType = "ORACLE";

	/**
	 * 数据库需要的类
	 */
	private String ecclDbJDBCClass = "";

	/**
	 * 数据库连接池
	 */
	private String ecclJNDIName = "";

	/**
	 * web应用所在目录
	 */
	private String ecclAppPath = "";

	/**
	 * 翻页时每页显示条数
	 */
	private int everyPageShow = -1;

	/**
	 * 发送邮件时的邮件地址
	 */
	private String ecclEmail = "";
	/**
	 * @since 1.0 需要发送邮件时SMTP服务器地址
	 */
	private String ecclSMTP = "";

	/**
	 * @since 1.0
	 */
	private String ecclSMTPUser = "";

	/**
	 * @since 1.0
	 */
	private String ecclSMTPPwd = "";

	/**
	 * 晨砻采购网Exchange平台连接地址基础部分。
	 */
	private String ecclURLBase = "";

	/*
	 * 系统名称--电子商务平台
	 */
	private String SystemName = "";

	/*
	 * 日志目录
	 */
	private static String logPath = "";

	/**
	 * 日志级别
	 */
	private static String logLevel = "";

	/**
	 * @since 1.0
	 */
	private static AppConfig acInstance = new AppConfig();

	/**
	 * 存储文本
	 */
	HashMap hmText = new HashMap();
	
	/**
	 * 存储对象
	 */
	HashMap hmObject = new HashMap();


	private AppConfig() {
		
	}
	
	/**
	 * 设置文本
	 */
	public void setAttribute(String name, String value) {
		hmText.put(name, value);
	}

	/**
	 * 获得文本
	 */
	public String getAttribute(String name) {
		if (hmText.containsKey(name)) {
			return (String) hmText.get(name);
		}
		return name;
	}

	/**
	 * 设置对象
	 */
	public void setObject(String name, Object value) {
		hmObject.put(name, value);
	}

	/**
	 * 获得对象
	 */
	public Object getObject(String name) {
		if (hmObject.containsKey(name)) {
			return hmObject.get(name);
		}
		return null;
	}

	/**
	 * @roseuid 41255AA100AC
	 */
	public void setEcclDbConnStr(String dbConnStr) {
		ecclDbConnStr = dbConnStr;
	}

	/**
	 * @roseuid 41255B1A0074
	 */
	public void setEcclDbUser(String dbUser) {
		ecclDbUser = dbUser;
	}

	/**
	 * @roseuid 41255B230226
	 */
	public void setEcclDbPwd(String dbPwd) {
		ecclDbPwd = dbPwd;
	}

	/**
	 * @roseuid 41255B2C0188
	 */
	public void setEcclDbType(String dbType) throws Exception {
		boolean isValidType = false;
		String enabledDbType = "ORACLE;MSSQL;MYSQL;ACCESS;DB2";
		String[] tmp = enabledDbType.split(";");
		for (int i = 0; i < tmp.length; i++) {
			if (dbType.equals(tmp[i]))
				isValidType = true;
		}
		if (!isValidType)
			throw new Exception("系统配置错误：不支持的数据库类型！");
		ecclDbType = dbType.toUpperCase();
	}

	/**
	 * @roseuid 41255B330391
	 */
	public void setEcclDbJDBCClass(String jdbcClass) {
		ecclDbJDBCClass = jdbcClass;
	}

	/**
	 * @roseuid 41255B4501CA
	 */
	public void setEcclJNDIName(String jndiName) {
		ecclJNDIName = jndiName;
	}

	/**
	 * @roseuid 41255B50004A
	 */
	public void setEcclAppPath(String appPath) {
		ecclAppPath = appPath;
	}

	/**
	 * @roseuid 41255B550105
	 */
	public void setEveryPageShow(int _everyPageShow) {
		everyPageShow = _everyPageShow;
	}

	/**
	 * @roseuid 41255B650299
	 */
	public String getEcclDbConnStr() {
		return ecclDbConnStr;
	}

	/**
	 * @roseuid 41255B70003C
	 */
	public String getEcclDbUser() {
		return ecclDbUser;
	}

	/**
	 * @roseuid 41255B740363
	 */
	public String getEcclDbPwd() {
		return ecclDbPwd;
	}

	/**
	 * @roseuid 41255B7A0253
	 */
	public String getEcclDbType() {
		return ecclDbType;
	}

	/**
	 * @roseuid 41255B82006A
	 */
	public String getEcclDbJDBCClass() {
		return ecclDbJDBCClass;
	}

	/**
	 * @roseuid 41255B900273
	 */
	public String getEcclJNDIName() {
		return ecclJNDIName;
	}

	/**
	 * @roseuid 41255B99022F
	 */
	public String getEcclAppPath() {
		return ecclAppPath;
	}

	public void setEcclExchangeDbServicesBase(String url) {

	}

	public String getEcclExchangeDbServicesBsse() {
		return ecclAppPath;
	}

	public String getEcclExchangeURLBase() {
		return ecclAppPath;
	}

	public boolean getEcclLicenseSnIsValid() {
		return false;
	}

	/**
	 * @roseuid 41255B9E03D1
	 */
	public int getEveryPageShow() {
		return everyPageShow;
	}

	/**
	 * @roseuid 412583CE033E
	 */
	public static AppConfig getInstance() {
		return acInstance;
	}

	/**
	 * @roseuid 413857250091
	 */
	public void setEcclSMTP(String smtpServer) {
		ecclSMTP = smtpServer.trim();
	}

	/**
	 * @roseuid 4138572D022D
	 */
	public String getEcclSMTP() {
		return ecclSMTP;
	}

	/**
	 * @roseuid 4138573501C1
	 */
	public void setEcclSMTPUser(String smtpUser) {
		ecclSMTPUser = smtpUser.trim();
	}

	/**
	 * @roseuid 4138573A038B
	 */
	public String getEcclSMTPUser() {
		return ecclSMTPUser;
	}

	/**
	 * @roseuid 413857400221
	 */
	public void setEcclSTMPPwd(String smtpPwd) {
		ecclSMTPPwd = smtpPwd.trim();
	}

	/**
	 * @roseuid 4138574800EC
	 */
	public String getEcclSMTPPwd() {
		return ecclSMTPPwd;
	}

	public void setEcclURLBase(String ecclURLBase) {
		this.ecclURLBase = ecclURLBase;

	}

	public String getEcclURLBase() {
		return ecclURLBase;
	}

	public void setEcclEmail(String ecclEmail) {

		this.ecclEmail = ecclEmail;
	}

	public String getEcclEmail() {
		return ecclEmail;
	}

	/**
	 * 测试方法入口
	 * 
	 */
	public static void main(String[] args) {

		/*
		 * System.out.println(
		 * " $Id: AppConfig.java,v 1.1.1.1 2009/06/04 02:34:01 liuchao Exp $ ");
		 * 
		 * 
		 * AppConfig ac = AppConfig.getInstance(); ac.setEcclLicenseSn("");
		 */
		/*
		 * if(true) { int i=0; } int i=1;
		 */
		// System.out.print(1%25);
		for (int i = 0, j = 1; i < 25; i++)
			System.out.println(i + j);
	}

	public void setEcclSMTPPwd(String ecclSMTPPwd) {
		this.ecclSMTPPwd = ecclSMTPPwd;
	}

	public static AppConfig getAcInstance() {
		return (AppConfig.acInstance);
	}

	public static void setAcInstance(AppConfig acInstance) {
		AppConfig.acInstance = acInstance;
	}   
	
	public DruidDataSource getDataSource() {
		return dataSource;
	}

	public String getSystemName() {
		return SystemName;
	}

	public void setSystemName(String systemName) {
		SystemName = systemName;
	}

	public static String getLogPath() {
		return logPath;
	}

	public void setLogPath(String logPath) {
		this.logPath = logPath;
	}

	public static String getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}

	/*
	 * 初始化数据库连接池参数
	 */
	public void initDataSourceConfig() {
		// 设置招投标的连接池参数
		dataSource.setDriverClassName(getEcclDbJDBCClass());
		dataSource.setUrl(getEcclDbConnStr());
		dataSource.setUsername(getEcclDbUser());
		dataSource.setPassword(getEcclDbPwd());
		dataSource.setMaxActive(30);
		dataSource.setInitialSize(5);
		dataSource.setMaxIdle(5);
		dataSource.setMinIdle(2);
		dataSource.setMaxWait(-1);
		dataSource.setRemoveAbandoned(true);
		dataSource.setRemoveAbandonedTimeout(600);

		/***
		 * #<!-- 初始化连接 --> dataSource.initialSize=10
		 * 
		 * #<!-- 最大空闲连接 --> dataSource.maxIdle=20
		 * 
		 * #<!-- 最小空闲连接 --> dataSource.minIdle=5
		 * 
		 * #最大连接数量 dataSource.maxActive=50 #是否在自动回收超时连接的时候打印连接的超时错误
		 * dataSource.logAbandoned=true
		 * 
		 * #是否自动回收超时连接 dataSource.removeAbandoned=true
		 * 
		 * #超时时间(以秒数为单位) dataSource.removeAbandonedTimeout=180
		 * 
		 * #<!-- 超时等待时间以毫秒为单位 6000毫秒/1000等于60秒 --> dataSource.maxWait=1000
		 ***/
	}
}
