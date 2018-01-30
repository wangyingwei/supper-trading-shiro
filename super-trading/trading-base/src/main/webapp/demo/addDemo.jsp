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
<title>Demo List</title>
</head>
<body>
    <h1>Add Demo Page</h1>
    <br/>
    ${InfoMessage }
    <br/>
    <form action="<%=basePath %>demo/insertDemo" method="post">
        <table>
            <tr>
                <td>名称：</td>
                <td><input type="text" name="name" value=""></td>
            </tr>
            <tr>
                <td>描述：</td>
                <td><input type="text" name="itemDescription" value=""></td>
            </tr>
            <tr>
                <td>单位：</td>
                <td><input type="text" name="unit" value=""></td>
            </tr>
            <tr>
                <td>备注：</td>
                <td><input type="text" name="remark" value=""></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="提交数据"></td>
            </tr>
        </table>
    </form>
</body>
</html>