<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<title>框架集成主页面</title>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>
<frameset rows="59,*" cols="*" frameborder="yes" border="1" framespacing="0">

	<frame name="topFrame" src="frame/top.jsp" id="topFrame" scrolling="No" frameborder="1" noresize="noresize" />

	<frameset cols="213,*" frameborder="yes" border="1" framespacing="0">

		<frame name="leftFrame" src="frame/left.jsp" id="leftFrame" scrolling="No" frameborder="1" noresize="noresize" frameborder="1" />

		<frame name="mainFrame" src="frame/main.jsp" id="mainFrame" scrolling="No" frameborder="1" />
	</frameset>
</frameset>
<body>

</body>
</html>