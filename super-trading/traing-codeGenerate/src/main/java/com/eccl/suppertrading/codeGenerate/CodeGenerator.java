package com.eccl.suppertrading.codeGenerate;

import cn.org.rapid_framework.generator.GeneratorFacade;
import cn.org.rapid_framework.generator.GeneratorProperties;

public class CodeGenerator {

	public static void main(String[] args) throws Exception {
		
		//设置输出目录字符集
		System.setProperty("generator.outputEncoding","UTF-8");
		//设置源目录字符集
		System.setProperty("generator.sourceEncoding","UTF-8");
		
		// 模板地址
		String templatePath = "D:/java/workSuperTrading/super-trading/traing-codeGenerate/src/main/resources/template";
		GeneratorFacade g = new GeneratorFacade();
		g.getGenerator().addTemplateRootDir(templatePath);
		
		//打印数据库中的表名称
		g.printAllTableNames();				
		
		// 删除生成器的输出目录//
		//g.deleteOutRootDir();
		// 通过数据库表生成文件
		g.generateByTable("DEMO");

		// 自动搜索数据库中的所有表并生成文件,template为模板的根目录
		// g.generateByAllTable();
		// 按table名字删除文件
		// g.deleteByTable("table_name", "template");
		
		//打开文件夹
		Runtime.getRuntime().exec("cmd.exe /c start "+GeneratorProperties.getRequiredProperty("outRoot"));
	}
}
