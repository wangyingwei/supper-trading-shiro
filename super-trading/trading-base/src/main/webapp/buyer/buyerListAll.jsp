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
<title>Buyer List</title>
<style type="text/css">
		img{
				width:50px;height:50px
		}
</style>
<script type="text/javascript">
		window.onload=function(){
				
		}
</script>
</head>
<body>
    <h1>Buyer List Page</h1>
    <br/>
    ${InfoMessage}
    <br/>
    <input type="button" value="新建" onclick="window.location.href='<%=basePath%>buyer/addBuyer'" />
    	<table border="1">
    	<tr>
    		<td>id</td>
    		<td>采购方名称</td>
    		<td>头像</td>
    		<td>操作</td>
    	</tr>
    	<c:forEach var="buyer"  items="${buyerList}" >
		  	<tr>
		  		<td>${buyer.id}</td>
		  		<td>${buyer.buyername}</td>		
		  		
		  		<td><img src="${buyer.headpath }"></td>
		  		<td>
			  		<a href="editBuyer?id=${buyer.id}">编辑</a>
			  		<a href="deleteBuyer?id=${buyer.id}">删除</a>
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