package com.eccl.supertrading.buyer.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.eccl.supertrading.buyer.pojo.Buyer;
public interface BuyerService {

//	查询所有角色
	public List<Buyer> listAll();
//	插入角色
	public int insertSelective( Buyer buyer);
// 根据角色ID查询角色
	public Buyer selectByPrimaryKey(Integer id);	
//	对角色信息进行修改
	public int updateByPrimaryKeySelective(Buyer buyer);
//	根据角色ID删除角色
	public int deleteByPrimaryKey(Integer id);
}
