package com.eccl.supertrading.demo.service;

import java.util.List;

import com.eccl.supertrading.demo.po.Demo;

public interface DemoService {
	
	public Demo selectByPrimaryKey(int id);
	
	public List<Demo> listAll();
	
	public int updateByPrimaryKeySelective(Demo demo);
	
	public int insertSelective(Demo demo);
	
	public int deleteByPrimaryKey(Integer id);
	
}
