package com.eccl.supertrading.buyer.mapper;

import java.util.List;

import com.eccl.supertrading.buyer.pojo.Buyer;


public interface BuyerDao {
	/** 方法名 与 BuyerMapper.xml 文件中的 sql语句的 id 相同 */

//	查询所有角色
	List<Buyer> listAll();
//	插入角色
	int insertSelective(Buyer buyer);
// 根据角色ID查询角色
	Buyer selectByPrimaryKey(Integer id);	
//	对角色信息进行修改
	int updateByPrimaryKeySelective(Buyer buyer);
//	根据角色ID删除角色
	int deleteByPrimaryKey(Integer id);
}
