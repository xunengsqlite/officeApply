<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<title>password modification</title>
<style type="text/css">
	.title{
		background-color: red;
	}
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
<script type="text/javascript" src='js/checkPassword.js' ></script>
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
	<span style="color: red">密码修改成功!!</span>
	<%
		} else if ("false".equals(flag)) {
	%>
	<span style="color: red">密码修改失败!!</span>
	<%
		}
	%>
<table>
	<tr align="center">
		<th><font color="bule" style="font-weight: bolder; font-size: xx-large;">密码修改</font></th>
	</tr>
</table>
<form name ="myform" action="user/updatePassword.action" method="post" onsubmit="return doCheckAll()">
<table class="content" align="left">
	<tr>
		<th align="left">原密码:</th>
		<td align="left"><input type="password" name="psd" value=""
			id="psd"></td>
		<td><font style="color: red; font-size: small;">*由字母、数字、下划线组成长度小于20个字符</font></td>
	</tr>
	<tr>
		<th align="left">新密码:</th>
		<td align="left"><input type="password" name="newpsd" value="" id="newpsd">
		</td>
		<td><font style="color: red; font-size: small;">*由字母、数字、下划线组成长度小于20个字符</font></td>
	</tr>
	<tr>
		<th align="left">新密码确认:</th>
		<td align="left"><input type="password" name="re_newpsd" value="" id="re_newpsd">
		</td>
		<td><font style="color: red; font-size: small;">*由字母、数字、下划线组成长度小于20个字符</font></td>
	</tr>
	<tr>
		<td align="right" colspan="3"><input type="submit" name="update" value="确认修改"
			style="background-color: #82daf0"></td>
	</tr>
</table>
</form>

</body>
</html>