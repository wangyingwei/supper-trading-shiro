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
<title>Add Roles List</title>
</head>
<body>
    <h1>Add Roles Page</h1>
    <br/>
    ${InfoMessage }
    <br/>
    <form action="<%=basePath %>roles/insertRoles" method="post">
        <table>
            <tr>
                <td>角色名称：</td>
                <td><input type="text" name="roleName" value=""></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="提交数据"></td>
            </tr>
        </table>
    </form>
</body>
</html>