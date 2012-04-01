<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.fnst.officeapply.entity.*" %>
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加用户</title>
<script type="text/javascript" src="js/checkUser.js"></script>
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
<head/>
<body style="background-image: url('images/login/bg_form.PNG');font-family:SimSun">
<table align="center">
	<tr>
		<jsp:include page="../common/header.jsp"></jsp:include>
	</tr>
</table>
<table align="left">
	<jsp:include page="../common/left.jsp"></jsp:include>
</table>
<table>
	<tr>
		<th><font color="#0F500"
			style="font-weight: bolder; font-size: xx-large;">添加用户</font></th>
	</tr>
</table>
<form action="user/addUser.action" method="post" onsubmit="return doCheckAll()">
		<%
			String flag = request.getParameter("isOK");
			if ("true".equals(flag)) {
		%>
		<span style="color: red">成功添加!!</span>
		<%
			} else if ("false".equals(flag)) {
		%>
		<span style="color: red">添加失败!!（可能用户名已经存在）</span>
		<%
			}
		%>
		<%
			String quserRealName = ComUtil.changeNullVal((String)request.getAttribute("quserRealName"),"");
			Integer pageNum = (Integer)request.getAttribute("pageNum_u");
			pageNum = (pageNum == null ? 1 : pageNum);
		%>
<table class="content">
	<tr>
		<th align="left">账号:</th>
		<td align="left">
			<input type="text" name="username" id="username">
			<input type="hidden" name="quserRealName" value="<%=quserRealName %>"/>
			<input type="hidden" name="pageNum" value="<%=pageNum %>"/>
		
		</td>
		<td><font style="color: red; font-size: small;">*由字母开头，字母、数字、下划线组成长度小于20个字符</font></td>
	</tr>
	<tr>
	</tr>
	<tr>
		<th align="left">姓名:</th>
		<td align="left"><input type="text" name="name" value="" id="name">
		</td>
		<td><font style="color: red; font-size: small;">*由汉字、字母、数字组成长度小于20个字符</font></td>
	</tr>
	<tr>
	</tr>
	<tr>
		<th align="left">密码:</th>
		<td align="left""><input type="password" name="psd" value="" id="psd">
		</td>
		<td><font style="color: red; font-size: small;">*由字母、数字、下划线组成长度小于20个字符</font></td>
	</tr>
	<tr>
	</tr>
	<tr>
		<th align="left">权限：</th>
		<td align="left"><select style="width: 4cm" name="permissionID">
			<%
				List<Authority> auths = (ArrayList) request.getAttribute("auths");
				if (auths != null) {
					for (Authority auth : auths) {
			%>
			<option value="<%=auth.getPermissionID()%>"><%=auth.getPermissionName()%></option>
			<%
				}
				}
			%>
		</select></td>
		<td><font style="color: red; font-size: small;">*由0或者1组成长度小于2个字符</font></td>
	</tr>
	<tr>
	</tr>
	<tr>
		<th align="left">邮箱：</th>
		<td align="left"><input type="text" name="mail"
			value="xx@cn.fujitsu.com" id="mail"></td>
		<td><font style="color: red; font-size: small;">*由字母、数字、下划线、@以及．组成长度小于50个字符</font></td>
	</tr>
	<tr>
		<td align="right" colspan="3"><a href="user/userNextList.action?pageNum=<%=pageNum %>&quserRealName=<%=quserRealName %>">返回列表</a><input type="submit" value="确认添加"
			style="background-color: #82daf0;margin-left: 30px">
	</tr>
</table>
</form>
</body>
</html>