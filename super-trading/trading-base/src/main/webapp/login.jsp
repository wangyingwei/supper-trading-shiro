<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登陆页面</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript">
	    function save(){
	    	var loginName=document.getElementById("loginName").value;
	    	if(loginName==""){
	    		alert("请填写登录名");
	    		return;
	    	}
	    	var password=document.getElementById("password").value;
	    	if(password==""){
	    		alert("请填写密码");
	    		return;
	    	}
	    	
	    	var loginForm=document.getElementById("loginForm"); 
	    	loginForm.submit();
	    }
	</script>
	
</head>
<body>
	<h1>Welcome To SuperTrading FrameWork!</h1>
	<br />
	<h2>登陆页面</h2>
	<br />
	${InfoMessage}
	<form name="loginForm" id="loginForm" action="<%=basePath %>user/login" method="post">
       <table>
           <tr>
	           <td colspan="2" align="center">${errorInfo}</td>
	       </tr>
	       <tr>
	           <td align="right">姓名：</td>
	           <td align="left"><input type="text" name="loginName" id="loginName"></td>
	       </tr>
	       <tr>
	           <td align="right">密码：</td>
	           <td align="left"><input type="password" name="password" id="password"></td>
	       </tr>
	       <tr>
	           <td colspan="2" align="center"><input type="button" value="登陆" onclick="save()"></td>
	       </tr>
       </table>
    </form>
</body>
</html>