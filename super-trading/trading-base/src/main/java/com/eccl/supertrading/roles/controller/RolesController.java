package com.eccl.supertrading.roles.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eccl.supertrading.roles.po.Roles;
import com.eccl.supertrading.roles.po.RolesExample;
import com.eccl.supertrading.roles.service.RolesService;

@Controller
@RequestMapping("/roles")
public class RolesController {
	
	@Resource
	private RolesService rolesService;

	@RequestMapping(value = "rolesListAll")
	public String rolesListAll(HttpServletRequest request, Model model) {
		
		RolesExample rolesExample=new RolesExample();
		rolesExample.setOrderByClause("id desc");
		
		List<Roles> roles = this.rolesService.selectByExample(rolesExample);
		
		request.setAttribute("rolesList", roles);
		
		return "/roles/rolesListAll";
	}

	@RequestMapping(value = "queryRoles")
	public String queryRoles(HttpServletRequest request) {
		
		String id = request.getParameter("queryBean.id");
		String roleName = request.getParameter("queryBean.roleName");
		
		RolesExample rolesExample=new RolesExample();
		RolesExample.Criteria criteria=rolesExample.createCriteria();
		if((!"".equals(id))&&(id!=null)){
			criteria.andIdEqualTo(Integer.valueOf(id));
		}
		if((!"".equals(roleName))&&(roleName!=null)){
			criteria.andRoleNameLike("%"+roleName+"%");
		}
		rolesExample.setOrderByClause("id desc");
		
		List<Roles> roles = this.rolesService.selectByExample(rolesExample);
		
		request.setAttribute("rolesList", roles);
		request.setAttribute("queryBean.id", id);
		request.setAttribute("queryBean.roleName", roleName);
		
		return "/roles/rolesListAll";
	}
	
	@RequestMapping(value = "addRoles")
	public String addRoles(HttpServletRequest request) {
		return "/roles/addRoles";
	}

	@RequestMapping(value = "insertRoles")
	public String insertRoles(HttpServletRequest request, Roles roles, RedirectAttributes redirectAttributes) {
		
		int insertNum = this.rolesService.insertSelective(roles);
		
		if (insertNum == 1) {
			redirectAttributes.addFlashAttribute("InfoMessage", "新建roles成功");
			return "redirect:/roles/rolesListAll";
		} else {
			request.setAttribute("InfoMessage", "新建roles失败");
			return "/roles/addRoles";
		}
	}

	@RequestMapping(value = "deleteRoles")
	public String deleteRoles(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		int deleteNum = this.rolesService.deleteByPrimaryKey(id);
		
		if (deleteNum == 1) {
			redirectAttributes.addFlashAttribute("InfoMessage", "删除Roles成功");
		} else {
			redirectAttributes.addFlashAttribute("InfoMessage", "删除Roles失败");
		}
		return "redirect:/roles/rolesListAll";
	}
    
	@RequestMapping(value = "showRoles")
	public String showRoles(HttpServletRequest request, Model model) {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		Roles roles = this.rolesService.selectByPrimaryKey(id);
		request.setAttribute("roles", roles);
		
		return "roles/showRoles";
	}
	
	@RequestMapping(value = "editRoles")
	public String editRoles(HttpServletRequest request, Model model) {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		Roles roles = this.rolesService.selectByPrimaryKey(id);
		request.setAttribute("roles", roles);
		
		return "roles/editRoles";
	}

	@RequestMapping(value = "updateRoles")
	public String updateRoles(HttpServletRequest request, Roles roles, RedirectAttributes redirectAttributes) {

		int updateNum = this.rolesService.updateByPrimaryKeySelective(roles);
		
		if (updateNum == 1) {
			redirectAttributes.addFlashAttribute("InfoMessage", "编辑roles成功");
			return "redirect:/roles/rolesListAll";
		} else {
			request.setAttribute("InfoMessage", "编辑roles失败");
			return "/roles/editRoles";
		}
	}
}
