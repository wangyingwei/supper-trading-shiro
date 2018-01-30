package com.eccl.supertrading.roles.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.eccl.supertrading.roles.po.Roles;
import com.eccl.supertrading.roles.po.RolesExample;

public interface RolesMapper {

	// 根据条件查询数量
	long countByExample(RolesExample example);

	// 根据条件删除多条
	int deleteByExample(RolesExample example);

	// 根据条件删除单条
	int deleteByPrimaryKey(Integer id);

	// 插入数据
	int insert(Roles record);

	// 插入数据
	int insertSelective(Roles record);

	// 根据条件查询数据
	List<Roles> selectByExample(RolesExample example);

	// 根据主键查询数据
	Roles selectByPrimaryKey(Integer id);

	// 按条件更新值不为null的字段
	int updateByExampleSelective(@Param("record") Roles record, @Param("example") RolesExample example);

	// 按条件更新
	int updateByExample(@Param("record") Roles record, @Param("example") RolesExample example);

	// 按条件更新
	int updateByPrimaryKeySelective(Roles record);

	// 按主键更新
	int updateByPrimaryKey(Roles record);

}