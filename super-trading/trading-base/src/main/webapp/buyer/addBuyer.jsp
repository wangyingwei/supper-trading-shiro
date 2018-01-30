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
<title>Add Buyer List</title>
</head>
<body>
    <h1>Add Buyer Page</h1>
    <br/>
    ${InfoMessage }
    <br/>
    <form action="<%=basePath %>buyer/insertBuyer" method="post"  enctype="multipart/form-data">
        <table>
            <tr>
                <td>采购方名称：</td>
                <td><input type="text" name="buyername" value=""></td>
            </tr>
            <tr>
                <td>头像：</td>
             	<td><input type="file" name="headpath"  value="上传"></td>
             	<td><input type="file" name="headpath"  value="上传"></td>
             	<td><input type="file" name="headpath"  value="上传"></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                		<input type="submit" value="提交数据">
                </td>
            </tr>
        </table>
    </form>
</body>
</html>