<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>    
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Demo List</title>
</head>
<body>
    <h1>Demo List Page</h1>
    <br/>
    ${InfoMessage}
    <br/>
    <input type="button" value="新建" onclick="window.location.href='<%=basePath%>demo/addDemo'" />
    	<table border="1">
    	<tr>
    		<td>id</td>
    		<td>名称</td>
    		<td>描述</td>
    		<td>单位</td>
    		<td>备注</td>
    		<td>操作</td>
    	</tr>
		<c:forEach var="demo"  items="${demoList}">
		  	<tr>
		  		<td>${demo.id}</td>
		  		<td>${demo.name}</td>
		  		<td>${demo.itemDescription}</td>
		  		<td>${demo.unit}</td>
		  		<td>${demo.remark}</td>
		  		<td>
		  		<a href="editDemo?id=${demo.id}">编辑</a>
		  		<a href="deleteDemo?id=${demo.id}">删除</a>
		  		
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