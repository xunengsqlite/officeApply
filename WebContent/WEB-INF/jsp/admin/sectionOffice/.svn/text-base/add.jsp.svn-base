<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.fnst.officeapply.entity.*" %>
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课添加</title>
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
	.button{
		background-color: #82daf0;
		margin-left: 30px;
	}
</style>
<head/>
<body style="background-image: url('images/login/bg_form.PNG');font-family:SimSun">
<table align="center">
	<tr>
		<jsp:include page="../../common/header.jsp"></jsp:include>
	</tr>
</table>
<table align="left">
	<jsp:include page="../../common/left.jsp"></jsp:include>
</table>
<table>
	<tr>
		<th><font color="#0F500"
			style="font-weight: bolder; font-size: xx-large;">课添加</font></th>
	</tr>
</table>
<form action="admin/orgz/add.action" method="post" onsubmit="return doCheckAll()">
		<%
			String flag = request.getParameter("isOK");
			if ("true".equals(flag)) {
		%>
		<span style="color: red">成功添加!!</span>
		<%
			} else if ("false".equals(flag)) {
		%>
		<span style="color: red">添加失败!!</span>
		<%
			}
		%>
		
<table align="left" class="content">
	<tr>
		<th align="left">课名:</th>
		<td align="left">
			<input type="hidden" name="orgzType" value="1"/>
			<input type="text" name="orgzName" value="">
		</td>
		<td><font style="color: red; font-size: small;">*任意非空字符(不含空格),长度(1-255)</font></td>
	</tr>
	<tr>
		<td align="right" colspan="3"><a href="admin/orgz/orgzNextList.action?orgzType=1">返回列表</a><input type="submit" class="button" name="add" value="确认添加">
	</tr>
</table>
</form>
</body>
</html>