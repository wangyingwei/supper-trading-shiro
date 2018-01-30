package com.eccl.supertrading.frame.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eccl.supertrading.frame.pojo.User;
import com.eccl.supertrading.frame.service.UserService;
import com.eccl.supertrading.shiro.realms.ShiroMd5Realm;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserService userService;
	@Resource
	private ShiroMd5Realm md5;
	/*
	 * 1. 获取当前的 Subject. 调用 SecurityUtils.getSubject(); 
	 * 2. 测试当前的用户是否已经被认证.即是否已经登录. 调用 Subject 的 isAuthenticated() 
	 * 3. 若没有被认证, 则把用户名和密码封装为 UsernamePasswordToken 对象 
	 *    1). 创建一个表单页面 
	 *    2). 把请求提交到 SpringMVC 的 Handler 
	 *    3). 获取用户名和密码. 
	 * 4. 执行登录: 调用 Subject 的 login(AuthenticationToken) 方法. 
	 * 5. 自定义Realm 的方法, 从数据库中获取对应的记录, 返回给 Shiro. 
	 *    1). 实际上需要继承org.apache.shiro.realm.AuthenticatingRealm 类 
	 *    2). 实现doGetAuthenticationInfo(AuthenticationToken) 方法. 
	 * 6. 由 shiro 完成对密码的比对.
	 */

	@RequestMapping("/login")
	public String login(HttpServletRequest request, Model model){
		String userName = request.getParameter("loginName");
		String password = request.getParameter("password");

        Subject subject= SecurityUtils.getSubject();
        
        // 把用户名和密码封装为 UsernamePasswordToken 对象
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        
        if(subject.isRemembered()){
            System.out.println("---- subject isRemembered ----");	
        }else{
        	System.out.println("---- subject is not Remembered ----");
        	// rememberme
            token.setRememberMe(true);
        }
		
        if (!subject.isAuthenticated()) {
            try {
            	System.out.println("1." + token.hashCode());
            	//Session session=subject.getSession();
            	// 执行登录. 
            	subject.login(token);
            } catch (UnknownAccountException uae) {// 若没有指定的账户, 则 shiro 将会抛出 UnknownAccountException 异常. 
            	System.out.println("没有指定的账户: " + uae.getMessage());
            	request.setAttribute("InfoMessage", "没有指定的账户");
            	return "/login";
            } catch (IncorrectCredentialsException ice) { // 若账户存在, 但密码不匹配, 则 shiro 会抛出 IncorrectCredentialsException 异常。 
            	System.out.println("用户名密码不匹配: " + ice.getMessage());
            	request.setAttribute("InfoMessage", "用户名密码不匹配");
                return "/login";
            } catch (LockedAccountException lae) {// 用户被锁定的异常 LockedAccountException
            	System.out.println("用户被锁定: " + lae.getMessage());
            	request.setAttribute("InfoMessage", "用户被锁定");
            	return "/login";
            } catch (AuthenticationException e) { // 所有认证时异常的父类. 
            	System.out.println("登录失败: " + e.getMessage());
            	request.setAttribute("InfoMessage", "登录失败");
            	return "/login";
            }
        }
		
		return "/frame/frame";
	    

//		//根据用户名查询登陆用户信息
//		User user = this.userService.selectByUserName(userName);
//		if(user!=null){
//			//匹配密码，如果密码匹配成功，则登陆到主压面
//			if(password.equals(user.getPassword())){
//				return "/frame/frame";
//			}else{//如果密码登陆失败，则返回登陆页面，并提示用户名密码错误
//				return "/login";
//			}
//		}else{//如果没有查询到用户，返回登陆页面，并提示用户名密码错误
//			return "/login";
//		}
	}

	@RequestMapping("/showUser")
	public String toIndex(HttpServletRequest request, Model model) {
		int userId = Integer.parseInt(request.getParameter("id"));
		User user = this.userService.getUserById(userId);
		model.addAttribute("user", user);
		return "/user/showUser";
	}
	
//	用户列表——显示所有用户
	@RequestMapping("/userListAll")
	public String userListAll(HttpServletRequest request,Model model){
		List<User> user=this.userService.listAll();
		model.addAttribute(user);
		return "/user/userListAll";
	}
//	用户列表——跳转到创建新用户
	@RequestMapping("/addUser")
	public String addUser(HttpServletRequest request){
		System.out.println("---- addUser ----");
		return "/user/addUser";
	}
//	用户列表——插入新用户
	@RequestMapping("/insertUser")
	public String insertUser(HttpServletRequest request,User user){
		String pwd=request.getParameter("password");
//		将pwd转为md5格式，再进行存储？？
		int insertNum=this.userService.insertSelective(user);
		System.out.println("--insertUser--");
		if (insertNum == 1) {
			request.setAttribute("InfoMessage", "新建user成功");
			return "redirect:/user/userListAll";
		} else {
			request.setAttribute("InfoMessage", "新建user失败");
			return "/user/addUser";
		}
	}
//	用户列表——跳转到修改用户
	@RequestMapping("/editUser")
	public String getUserById(HttpServletRequest request,Model model){
		int id=Integer.parseInt(request.getParameter("id"));
		User user=this.userService.selectByPrimaryKey(id);
		request.setAttribute("user", user);
		return "/user/editUser";
	}
//用户列表——更新用户信息
	@RequestMapping("/updateUser")
	public String updateUser(User user,HttpServletRequest request){
		System.out.println("---- UserController updateUser ----");
//		通过验证用户名是否有相同的，旧密码是否正确，然后才可以更新密码
		int updateNum = this.userService.updateByPrimaryKeySelective(user);
		if (updateNum == 1) {
			request.setAttribute("InfoMessage", "编辑user成功");
			return "redirect:/user/userListAll";
		} else {
			request.setAttribute("InfoMessage", "编辑user失败");
			return "/user/editUser";
		}
	}
//	用户列表——删除用户信息
	@RequestMapping("/deleteUser")
	public String deleteUser(HttpServletRequest request){
		int id = Integer.parseInt(request.getParameter("id"));
		int deleteNum=this.userService.deleteUser(id);
		System.out.println("deleteNum"+deleteNum);
//		if (deleteNum == 1) {
//			request.setAttribute("InfoMessage", "删除user成功");
//		} else {
//			request.setAttribute("InfoMessage", "删除user失败");
//		}
		return "redirect:/user/userListAll?flag="+deleteNum;
	}
}
