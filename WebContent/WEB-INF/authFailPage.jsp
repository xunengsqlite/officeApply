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
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>权限不足</title>
</head>
<%String adminMailAddr = ComUtil.changeNullVal((String)request.getAttribute("adminMailAddr"),""); %>
<body style="background-image: url('images/login/bg_form.PNG'); font-family: SimSun">
	<table align="center">
		<jsp:include page="jsp/common/header.jsp"></jsp:include>
	</table>
	<div align="left">
		<table>
			<jsp:include page="jsp/common/left.jsp" flush="false"></jsp:include>
		</table>
	</div>
	<h1>对不起，您没有访问权限!</h1>
	<h1></h1>
	<h2>如您有任何疑问，请您联系系统管理员:<a href="mailto:<%=adminMailAddr %>"><%=adminMailAddr %></a></h2>
</body>
</html>
