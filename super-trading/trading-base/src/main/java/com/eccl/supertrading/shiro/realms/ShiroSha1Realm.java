package com.eccl.supertrading.shiro.realms;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.eccl.supertrading.frame.pojo.User;
import com.eccl.supertrading.frame.service.UserService;

public class ShiroSha1Realm extends AuthenticatingRealm {

	@Resource
	private UserService userService;

	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		System.out.println("---- [SecondRealm] ShiroSha1Realm ----");
		// 1. 把 AuthenticationToken 转换为 UsernamePasswordToken
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;

		// 2. 从 UsernamePasswordToken 中来获取 username
		String userName = upToken.getUsername();

		// 3. 调用数据库的方法, 从数据库中查询 userName 对应的用户记录
		User user = this.userService.selectByUserName(userName);
		if (user != null) {
			System.out.println("数据库中存在用户名为  " + userName + " 所对应的用户信息.");
		}

		// 4. 若用户不存在, 则可以抛出 UnknownAccountException 异常
		if (user == null) {
			throw new UnknownAccountException("用户不存在!");
		}

		// 5. 根据用户信息的情况, 决定是否需要抛出其他的 AuthenticationException 异常.
		if ("lockedUser".equals(userName)) {
			throw new LockedAccountException("用户被锁定");
		}

		// 6. 根据用户的情况, 来构建 AuthenticationInfo对象并返回. 通常使用的实现类为:
		// SimpleAuthenticationInfo
		// 以下信息是从数据库中获取的.
		// 1). principal: 认证的实体信息. 可以是 username, 也可以是数据表对应的用户的实体类对象.
		Object principal = user.getUserName();
		// 2). credentials: 密码.
		Object credentials = user.getPassword();
//		if("user1".equals(userName)){
//			credentials="2701586cc3589815473febf4d2f87a7a3f428587---";
//		}
		// 3). realmName: 当前 realm 对象的 name. 调用父类的 getName() 方法即可
		String realmName = getName();
		//4). 盐值. 
	    ByteSource credentialsSalt = ByteSource.Util.bytes(principal);

		//SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, realmName);
	    SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
		return info;
	}
	
	public static void main(String[] args) {
		String hashAlgorithmName = "SHA1";
		Object credentials = "1";
		//Object salt = ByteSource.Util.bytes("lockedUser");
		Object salt = ByteSource.Util.bytes("user1");
		int hashIterations = 1024;
		
		Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
		System.out.println(result);
	}

}
