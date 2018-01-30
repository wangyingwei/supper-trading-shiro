package com.eccl.supertrading.shiro.permissionannotation;

import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;

@Service("permissionAnnotationService")
public class PermissionAnnotationService {

	@RequiresRoles({"admin"})
	public void permissionAnnotation() {
		System.out.println("---- permissionAnnotation, time: " + new Date() + " ----");
		
		Session session = SecurityUtils.getSubject().getSession();
		Object val = session.getAttribute("sessionKey");
		
		System.out.println("---- Service SessionVal: " + val + " ----");
	}

}
