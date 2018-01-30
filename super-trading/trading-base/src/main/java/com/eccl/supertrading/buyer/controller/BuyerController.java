package com.eccl.supertrading.buyer.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eccl.supertrading.buyer.pojo.Buyer;
import com.eccl.supertrading.buyer.service.BuyerService;
import com.eccl.supertrading.util.OperationFileUtil;
import com.eccl.supertrading.util.PathConfiger;
import com.eccl.supertrading.util.UUIDGenerator;


@Controller
@RequestMapping(value="buyer")
public class BuyerController {
		@Resource
		private BuyerService buyerService;
//		采购方列表——显示所有采购方
		@RequestMapping(value="buyerListAll")
		public String BuyerListAll(HttpServletRequest request,Model model){
			List<Buyer> buyer=this.buyerService.listAll();
			model.addAttribute("buyerList", buyer);
			return "/buyer/buyerListAll";
		}
//		采购方列表——跳向添加采购方
		@RequestMapping(value="addBuyer")
		public String addBuyer(HttpServletRequest request){
			System.out.println("——addBuyer——");
			return "/buyer/addBuyer";
		}
////		采购方列表——添加采购方
//		@RequestMapping(value="insertBuyer")
//		public String insertBuyer( HttpServletRequest request,RedirectAttributes ra){
//			String path=OperationFileUtil.uploadOneFile(request);
//			String name=request.getParameter("buyername");
//			Buyer buyer=new Buyer();
//			buyer.setBuyername(name);
//			buyer.setHeadpath(path);
//			int insertNum=this.buyerService.insertSelective(buyer);
//			if (insertNum==1) {
//					ra.addFlashAttribute("InfoMessage","新建Buyer成功");
//					return "redirect:/buyer/buyerListAll";
//			}else{
//				request.setAttribute("InfoMessage", "新建Buyer失败");
//				return "/buyer/addBuyer";
//			}			
//		}
//		采购方列表——添加采购方
		@RequestMapping(value="insertBuyer")
		public String insertBuyer( HttpServletRequest request,@RequestParam(value="headpath") MultipartFile[] paths,RedirectAttributes ra){
			
			String name=request.getParameter("buyername");
			int insertNum=0;
			for(int i=0;i<paths.length;i++){
				String path=OperationFileUtil.uploadManyFile(paths,request);
				Buyer buyer=new Buyer();
				String uname=name+"("+(i+1)+")";
				buyer.setBuyerName(uname);
				buyer.setHeadPath(path);
				 insertNum=this.buyerService.insertSelective(buyer);
				 uname=name;
			}
			if (insertNum==1) {
					ra.addFlashAttribute("InfoMessage","新建Buyer成功");
					return "redirect:/buyer/buyerListAll";
			}else{
				request.setAttribute("InfoMessage", "新建Buyer失败");
				return "/buyer/addBuyer";
			}			
		}
//   采购方列表——删除采购方
		@RequestMapping(value="deleteBuyer")
		public String deleteBuyer(HttpServletRequest request,RedirectAttributes ra){
			int id=Integer.parseInt(request.getParameter("id"));
			
			int deleteNum=1;
			 deleteNum=this.buyerService.deleteByPrimaryKey(id);
			if(deleteNum==1){
				 ra.addFlashAttribute("InfoMessage", "删除Buyer成功");				 
			}else{
				ra.addFlashAttribute("InfoMessage", "删除Buyer失败");				
			}
			System.out.println("id>>>>"+id);
			 return "redirect:/buyer/buyerListAll";
		}
//采购方列表——跳向修改采购方
		@RequestMapping(value="editBuyer")
		public String getBuyerById(HttpServletRequest request,Model model){
				int id=Integer.parseInt(request.getParameter("id"));
				Buyer buyer=this.buyerService.selectByPrimaryKey(id);
				model.addAttribute("buyer", buyer);
				return "/buyer/editBuyer";
		}
//采购方列表——更新采购方信息
		@RequestMapping(value="updateBuyer")
		public String getBuyerById(HttpServletRequest request,RedirectAttributes ra) {
			int id=Integer.parseInt(request.getParameter("id"));
			Buyer buyer=this.buyerService.selectByPrimaryKey(id);
			String path=OperationFileUtil.uploadOneFile(request);
			String name=request.getParameter("buyername");
			buyer.setBuyerName(name);
			buyer.setHeadPath(path);
			int updateNum = this.buyerService.updateByPrimaryKeySelective(buyer);
			if (updateNum == 1) {
//				重定向传递参数方式，可以传中文，用？传参不能传中文，
				ra.addFlashAttribute("InfoMessage", "编辑Buyer成功");
				return "redirect:/buyer/buyerListAll";
			} else {
				request.setAttribute("InfoMessage", "编辑Buyer失败");
				return "/buyer/editBuyer";
			}
		}
}
