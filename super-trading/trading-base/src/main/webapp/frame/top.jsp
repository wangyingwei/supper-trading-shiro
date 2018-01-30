<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<body>
	<h3>Top Frame Page 
	<shiro:user>  
           欢迎[<shiro:principal/>]登录，<a href="shiro/logout" target="_parent">退出</a>  
    </shiro:user>  	
</body>
</html>
