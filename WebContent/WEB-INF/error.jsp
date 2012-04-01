<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.fnst.officeapply.common.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String contextPath = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ contextPath + "/";
%>
<head>
<base href="<%=basePath %>"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>错误页面</title>
</head>
<%String adminMailAddr = ComUtil.changeNullVal((String)request.getAttribute("adminMailAddr"),"");
String errMsg = ComUtil.changeNullVal((String)request.getAttribute("errMsg"),"");
%>
<body style="background-image: url('images/login/bg_form.PNG'); font-family: SimSun">
	
	<h1>系统出错了，请淡定等待...</h1>
	<%-- <h2>错误信息：</h2><%=errMsg %> --%>
	<h2>如您有任何疑问，请您联系系统管理员:<a href="mailto:<%=adminMailAddr %>"><%=adminMailAddr %></a></h2>
	<h3>回到首页：<a href="homePage.action">首页</a></h3>
	<h3>试着重新登录：<a href="logout.action">退出登录</a></h3>
</body>
</html>