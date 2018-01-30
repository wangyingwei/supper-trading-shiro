package com.eccl.supertrading.demo.mapper;

import java.util.List;

import com.eccl.supertrading.demo.po.Demo;

public interface DemoMapper {

	/** 方法名 与 UserMapper.xml 文件中的 sql语句的 id 相同 */

	Demo selectByPrimaryKey(Integer id);
	
	List<Demo> listAll();
	
	int updateByPrimaryKeySelective(Demo demo);
	
	int insertSelective(Demo demo);
	
	int deleteByPrimaryKey(Integer id);
	
}