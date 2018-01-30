package com.eccl.supertrading.shiro.factory;

import java.util.LinkedHashMap;

public class FilterChainDefinitionMapBuilder {

	/**
	 * 初始化资源和权限和方法
	 * @return
	 */
	public LinkedHashMap<String, String> buildFilterChainDefinitionMap() {
		LinkedHashMap<String, String> map = new LinkedHashMap<>();

		//此处角色、权限数据应该从数据库中读取
		map.put("/login.jsp", "anon");
		map.put("/user/login", "anon");
		map.put("/frame/shiro/logout", "logout");	
		map.put("/user/userListAll", "roles[admin]");
		map.put("/roles/rolesListAll", "roles[admin]");
		map.put("/permission/permissionList.jsp", "roles[admin]");
		
		map.put("/buyer/buyerListAll", "authc,roles[buyer]");
		
		map.put("/seller/sellerList.jsp", "authc,roles[seller]");
		//user拦截器：用户已经身份验证或者记住我登陆都可以访问
		map.put("/demo/listAll", "user");
		
		map.put("/**", "authc");

		return map;
	}

}
