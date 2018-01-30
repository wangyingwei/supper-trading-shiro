package com.eccl.supertrading.frame.mapper;

import java.util.List;

import com.eccl.supertrading.frame.pojo.User;

public interface UserDao {

	/** 方法名 与 UserMapper.xml 文件中的 sql语句的 id 相同 */

	int updateByPrimaryKey(User record);
    
	User selectByUserName(String userName);
//	查询所有用户
	List<User> listAll();
//	插入用户
	int insertSelective(User user);
// 根据用户ID查询用户
	User selectByPrimaryKey(Integer id);	
//	对用户信息进行修改
	int updateByPrimaryKeySelective(User user);
//	根据用户ID删除用户
	int deleteByPrimaryKey(Integer id);
}