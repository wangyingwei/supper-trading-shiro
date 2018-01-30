<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>    
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Roles List</title>
<script type="text/javascript">
		window.onload=function(){
				
		}
</script>
</head>
<body>
    <h1>Rolesr List Page</h1>
    <div>
        <h2>查询条件</h2>
        <form action="<%=basePath %>roles/queryRoles" method="post">
            Id <input type="text" name="queryBean.id" value="${queryBean.id}">
                               角色名称 <input type="text" name="queryBean.roleName" value="${queryBean.roleName}">
            <input type="submit" name="search" value="查询">
        </form>
    </div>
    
    <br/>
    ${InfoMessage}
    <br/>
    <input type="button" value="新建" onclick="window.location.href='<%=basePath%>roles/addRoles'" />

    	<table border="1">
    	<tr>
    		<td>id</td>
    		<td>角色名称</td>
    		<td>操作</td>
    	</tr>
		<c:forEach var="role"  items="${rolesList}" >
		  	<tr>
		  		<td>${role.id}</td>
		  		<td>${role.roleName}</td>
		  		<td>
 		  		<a href="showRoles?id=${role.id}">详情</a>
		  		<a href="editRoles?id=${role.id}">编辑</a>
		  		<a href="deleteRoles?id=${role.id}">删除</a>
		  		</td>
		  	</tr>
	   	</c:forEach> 
	</table>
	<shiro:hasRole name="adminTag">
		<br/>
		<hr> 
		<table border="1">
			<tr>
			    <td>admin访问的标签</td>
			</tr>
			<tr>
			    <td>admin访问的标签</td>
			</tr>
		</table>
	</shiro:hasRole>
	<shiro:hasRole name="buyerTag">
		<br/>
		<hr> 
		<table border="1">
			<tr>
			    <td>buyer访问的标签</td>
			</tr>
			<tr>
			    <td>buyer访问的标签</td>
			</tr>
		</table>
	</shiro:hasRole>
	<shiro:hasRole name="sellerTag">
		<br/>
		<hr> 
		<table border="1">
			<tr>
			    <td>seller访问的标签</td>
			</tr>
			<tr>
			    <td>seller访问的标签</td>
			</tr>
		</table>
	</shiro:hasRole>
</body>
</html>