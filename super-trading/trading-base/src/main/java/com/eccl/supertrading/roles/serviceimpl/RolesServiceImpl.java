package com.eccl.supertrading.roles.serviceimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eccl.supertrading.roles.mapper.RolesMapper;
import com.eccl.supertrading.roles.po.Roles;
import com.eccl.supertrading.roles.po.RolesExample;
import com.eccl.supertrading.roles.service.RolesService;

@Service("rolesService")
public class RolesServiceImpl implements RolesService {
	@Resource
	private RolesMapper rolesMapper;

	@Override
	public long countByExample(RolesExample example) {
		return rolesMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(RolesExample example) {
		return rolesMapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return rolesMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Roles record) {
		return rolesMapper.insert(record);
	}

	@Override
	public int insertSelective(Roles record) {
		return rolesMapper.insertSelective(record);
	}

	@Override
	public List<Roles> selectByExample(RolesExample example) {
		return rolesMapper.selectByExample(example);
	}

	@Override
	public Roles selectByPrimaryKey(Integer id) {
		return rolesMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(Roles record, RolesExample example) {
		return rolesMapper.updateByExample(record, example);
	}

	@Override
	public int updateByExample(Roles record, RolesExample example) {
		return rolesMapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(Roles record) {
		return rolesMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKey(Roles record) {
		return rolesMapper.updateByPrimaryKey(record);
	}
	
}
