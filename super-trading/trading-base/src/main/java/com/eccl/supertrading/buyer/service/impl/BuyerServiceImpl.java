package com.eccl.supertrading.buyer.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.eccl.supertrading.buyer.mapper.BuyerDao;
import com.eccl.supertrading.buyer.pojo.Buyer;
import com.eccl.supertrading.buyer.service.BuyerService;

import com.eccl.supertrading.util.OperationFileUtil;
import com.eccl.supertrading.util.PathConfiger;
import com.eccl.supertrading.util.UUIDGenerator;

@Service("buyerService")
public class BuyerServiceImpl implements BuyerService{
	@Resource
	private BuyerDao buyerDao;

	@Override
	public List<Buyer> listAll() {
		// TODO Auto-generated method stub
		return this.buyerDao.listAll();
	}

	@Override
	public int insertSelective(Buyer buyer) {
		
		return this.buyerDao.insertSelective(buyer);
	}

	@Override
	public Buyer selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return this.buyerDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Buyer buyer) {
		// TODO Auto-generated method stub
		return this.buyerDao.updateByPrimaryKeySelective(buyer);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return this.buyerDao.deleteByPrimaryKey(id);
	}


}
