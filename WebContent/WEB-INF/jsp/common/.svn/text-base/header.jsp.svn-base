<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.fnst.officeapply.common.*" %>
<%@ page import="com.fnst.officeapply.entity.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%--
	String contextPath = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ contextPath + "/";
--%>

<%UserInfo userInfo = (UserInfo)session.getAttribute("userInfo"); %>
<head>
<%-- <base href="<%=basePath%>"/>--%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/cookie.js"></script>
<script type="text/javascript" src="js/common.js"></script>

</head>
<body style="font-family:SimSun;" onload="startTime('timeshow')">
<%--<input type="hidden" name ="context" value="<%=basePath %>"/>
--%><table align="center" border="0" style="background-image: url('images/login/h1.PNG');background-position: top; background-repeat: no-repeat; width: 100%;border-bottom: solid 1px #666;background-attachment: fixed;">
	<tr align="center">
		<td style="font-family: SimSun; font-size: 33px; font-weight: bold;">办公用品预定系统</td>
	</tr>
	<tr align="left">
		<td >当前时间:<span id="timeshow"></span>
		<a title="点击退出登录，并取消自动登录"style="text-decoration: none;margin-left:50px;" href="logout.action">退出系统</a>
		<a title="点击查看在线用户"style="text-decoration: none;margin-left:50px;" href="showOnliner.action" target="blank">当前在线人数：<%=OnlineCounter.getInstance(application).getCounter()%></a>
		</td>
	</tr>
	<tr align="right" style="font-size: small;font-weight:bold">
		<td>当前登录用户: <%=userInfo.getUserRealName() %></td>
	</tr>
</table>
</body>
</html>