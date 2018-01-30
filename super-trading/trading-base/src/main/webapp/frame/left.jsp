<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%  
    String path = request.getContextPath();  
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";  
%>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<base target="rightfrm">
</head>
<body class="left">
    <h3>Left Frame Page</h3>
	<table border="0" cellspacing="0" cellpadding="0" align="center">
		<tr>
			<td valign="top">
				<ul class="">
				    <li><a>Demo管理</a></li>
				</ul>
				<ul>
					<li><a href="<%=basePath %>demo/listAll" target="mainFrame">Demo列表</a></li>
				</ul>
				
				<ul class="">
					<li><a>用户管理</a></li>
				</ul>
				<ul>
					<li><a href="<%=basePath %>user/userListAll" target="mainFrame">用户列表</a></li>
				</ul>
				
				<ul class="">
					<li><a>角色管理</a></li>
				</ul>
				<ul>
					<li><a href="<%=basePath %>roles/rolesListAll" target="mainFrame">角色列表</a></li>
				</ul>
				
				<ul class="">
					<li><a>权限管理</a></li>
				</ul>
				<ul>
					<li><a href="<%=basePath %>permission/permissionList.jsp" target="mainFrame">权限列表</a></li>
				</ul>
				
				<ul class="">
					<li><a>采购方管理</a></li>
				</ul>
				<ul>
					<li><a href="<%=basePath %>buyer/buyerListAll"  target="mainFrame">采购方列表</a></li>
				</ul>
				
				<ul class="">
					<li><a>供应商管理</a></li>
				</ul>
				<ul>
					<li><a href="<%=basePath %>seller/sellerList.jsp" target="mainFrame">供应商列表</a></li>
				</ul>
				
				<ul class=""><li><a>权限注解功能测试</a></li>
				</ul>
				<ul>
					<li><a href="<%=basePath %>demo/permissionAnnotation" target="mainFrame">权限注解功能测试</a></li>
				</ul>
			</td>
		</tr>

		<tr>

		</tr>
		<tr>

		</tr>
	</table>

</body>
</html>

