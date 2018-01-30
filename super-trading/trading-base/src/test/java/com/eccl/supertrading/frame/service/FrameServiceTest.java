package com.eccl.supertrading.frame.service;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.eccl.supertrading.frame.pojo.User;

@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class FrameServiceTest {
	private static Logger logger = Logger.getLogger(FrameServiceTest.class);

	@Resource
	private UserService userService = null;

	@Test
	public void getUserByIdTest() {
		User user = userService.getUserById(1);
		System.out.println("----user.getUserName----"+user.getUserName());
		//logger.info(user.getUserName());
	}
}
