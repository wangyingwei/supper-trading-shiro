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
<title>编辑用户页面</title>
</head>
<body>
    <h1>Edit User Page</h1>
    <br/>
    ${InfoMessage }
    <br/>
    <form action="<%=basePath %>user/updateUser" method="post">
        <input type="hidden" name="id" value="${user.id }">
        <table>
            <tr>
                <td>用户名：</td>
                <td><input type="text" name="userName" value="${user.userName }"></td>
            </tr>
            <tr>
                <td>密码：</td>
                <td><input type="text" name="password" value="${user.password }"></td>
            </tr>
            <tr>
                <td>年龄：</td>
                <td><input type="number" name="age" value="${user.age }"></td>
            </tr>
            <tr>
                <td>性别：</td>
                <td>
                	<c:if test="${user.gender==0}">
                			<input type="radio" name="gender" value="0"  checked="checked">男
                			<input type="radio" name="gender" value="1"  >女
                	</c:if>
                	<c:if test="${user.gender==1}">
                			<input type="radio" name="gender" value="0"  >男
                			<input type="radio" name="gender" value="1"  checked="checked">女
                	</c:if>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="提交数据"></td>
            </tr>
        </table>
    </form>
</body>
</html>