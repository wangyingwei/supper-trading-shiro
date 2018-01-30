<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>编辑采购方页面</title>
<style type="text/css">
		img{
			width:50px;heigth:80px;
		}
</style>
</head>
<body>
    <h1>Edit Buyer Page</h1>
    <br/>
    ${InfoMessage }
    <br/>
    <form action="<%=basePath %>buyer/updateBuyer" method="post" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${buyer.id }">
        <table>
            <tr>
                <td>采购方名称：</td>
                <td><input type="text" name="buyername" value="${buyer.buyername }"></td>
            </tr>
            <tr>
                <td>头像：</td>
                 <td><input type="file" name="headpath" value="${buyer.headpath }"></td>
				<td><img src="${buyer.headpath }"  alt=""></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="提交数据"></td>
            </tr>
        </table>
    </form>
</body>
</html>