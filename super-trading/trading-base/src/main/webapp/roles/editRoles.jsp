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
<title>编辑角色页面</title>
<style type="text/css">  
        /*input css*/  
        .iInput{  
            position: absolute;  
            width: 99px;  
            height: 16px;  
            left: 1px;  
            top: 2px;  
            border-bottom: 10px;  
            border-right: 0px;  
            border-left: 0px;  
            border-top: 0px;  
        }  
        </style>  
</head>
<body>
    <h1>Edit Roles Page</h1>
    <br/>
    ${InfoMessage }
    <br/>
    <form action="<%=basePath %>roles/updateRoles" method="post">
        <input type="hidden" name="id" value="${roles.id }">
        <table>
            <tr style="height:50px;">
                <td>角色名称：</td>
                <td><input type="text" name="roleName" value="${roles.roleName }"></td>
            </tr>
            </tr>
            <tr >
                <td colspan="2" align="center"><input type="submit" value="提交数据"></td>
            </tr>
        </table>
    </form>
</body>
</html>