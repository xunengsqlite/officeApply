<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="com.fnst.officeapply.common.mail.*" %>
<%@ page import="com.fnst.officeapply.common.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String contextPath = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ contextPath + "/";
%>
<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>邮件发送</title>
<style type="text/css">
	.content{
		background-color: gray;
		text-align: left;
	}
	.content td{
		background-color: rgb(232,253,253);
	}
	.content th{
		background-color: rgb(128,164,247);
	}
</style>
</head>

<body style="background-image: url('images/login/bg_form.PNG'); font-family: SimSun">
<table align="center">
	<tr>
		<jsp:include page="../common/header.jsp"></jsp:include>
	</tr>
</table>
<table align="left">
	<jsp:include page="../common/left.jsp"></jsp:include>
</table>
	<%
		String flag = request.getParameter("isOK");
		if ("true".equals(flag)) {
	%>
	<span style="color: red">成功!!</span>
	<%
		} else if ("false".equals(flag)) {
	%>
	<span style="color: red">失败!!</span>
	<%
		}
	%>
	<%
		String serverURL = ComUtil.changeNullVal((String) request.getAttribute(AdminConfig.SERVER_URL),"");
		String quserRealName = ComUtil.changeNullVal((String)request.getAttribute("quserRealName"),"");
		Integer pageNum = (Integer)request.getAttribute("pageNum_u");
		pageNum = (pageNum == null ? 1 : pageNum);
	%>
<form name="form" action="user/applyGoodsReminder.action" method="post">
<table class="content" align="left" border="1">
	<tr align="center"><td><font size="6">物品申请通知</font></td></tr>
	<tr>
		<th align="left">登录地址：</th>
	</tr>
	<tr>
		<td>
			<input type="text" size="40" name="serverURL" value="<%=serverURL %>"/>
			<input type="hidden" name="quserRealName" value="<%=quserRealName %>"/>
			<input type="hidden" name="pageNum" value="<%=pageNum %>"/>
		</td>
	</tr>
	<tr>
		<th align="left">邮件备注：</th>
	</tr>
	<tr>
		<td><textarea rows="2" cols="100" name="mailContent"></textarea></td>
	</tr>
	<tr align="right">
		<td><a href="user/userNextList.action?pageNum=<%=pageNum %>&quserRealName=<%=quserRealName %>">返回列表</a><input type="submit" value="确认发送"
			style="background-color: #82daf0;margin-left: 30px"/></td>
	</tr>
</table>
</form>
</body>
</html>
