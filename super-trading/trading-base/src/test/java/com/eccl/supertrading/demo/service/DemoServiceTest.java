package com.eccl.supertrading.demo.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.eccl.supertrading.demo.po.Demo;
import com.eccl.supertrading.frame.pojo.User;

@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class DemoServiceTest {
	private static Logger logger = Logger.getLogger(DemoServiceTest.class);

	@Resource
	private DemoService demoService = null;

	//@Test
	public void getUserByIdTest() {
		Demo demo = demoService.selectByPrimaryKey(1);
		System.out.println("----demo.getName----"+demo.getName());
	}
	
	//@Test
	public void listAllTest() {
		List<Demo> demoList = demoService.listAll();
		System.out.println("---- demoList.size ----"+demoList.size());
	}
	
	//@Test
	public void updateByPrimaryKey() {
		Demo demo=new Demo();
		demo.setId(1);
		demo.setName("电脑2");
		int updateNum = demoService.updateByPrimaryKeySelective(demo);
		System.out.println("---- updateNum ----"+updateNum);
	}
	
	//@Test
	public void insertSelective() {
		Demo demo=new Demo();
		demo.setName("打印纸");
		demo.setItemDescription("A4");
		demo.setUnit("包");
		demo.setRemark("财务部购买");
		int insertNum = demoService.insertSelective(demo);
		System.out.println("---- insertNum ----"+insertNum);
	}
	
	@Test
	public void deleteByPrimaryKey() {
		int deleteNum = demoService.deleteByPrimaryKey(5);
		System.out.println("---- deleteNum ----"+deleteNum);
	}
}
