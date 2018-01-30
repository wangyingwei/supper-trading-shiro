package com.eccl.supertrading.frame.service;

import java.util.List;

import com.eccl.supertrading.demo.po.Demo;
import com.eccl.supertrading.frame.pojo.User;

public interface UserService {
	
	public User getUserById(int userId);
	
	public User selectByUserName(String userName);
	
//	查询所有用户
	public List<User> listAll();
//	插入用户
	public int insertSelective(User user);
//	根据用户id查询用户
	public User selectByPrimaryKey(Integer id);
//	对用户信息进行修改
	public int updateByPrimaryKeySelective(User user);
//	根据用户ID删除用户
	public int deleteUser(Integer id);
}
