<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.fnst.officeapply.common.*" %>
<%@ page import="com.fnst.officeapply.entity.*" %>
<%@ page import="java.util.*" %>
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
<title>编辑用户</title>
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
<%
	String quserRealName = ComUtil.changeNullVal((String)request.getAttribute("quserRealName"),"");
	Integer pageNum = (Integer)request.getAttribute("pageNum_u");
	pageNum = (pageNum == null ? 1 : pageNum);
	UserInfo userInfo = (UserInfo) request.getAttribute("userInfo");
	if (userInfo != null) {
		int permission = userInfo.getPermission();
%>

<script type="text/javascript" src="js/checkUser.js">	
</script>
<script type="text/javascript" src="js/manageUser.js"></script>
<body style="background-image: url('images/login/bg_form.PNG');">
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
		<th><font color="#0F500" style="font-weight: bolder;font-size: xx-large;">修改用户</font></th>
	</tr>
</table>

<form name="form" action="user/updateUser.action" method="post" onsubmit="return CheckAllUpdate()">
<table class="content">
	<tr>
		<th align="left">账号:</th>
		<td align="left">
			<input type="text" name="username" value="<%=userInfo.getUserName()%>" readonly="readonly">
			<input type="hidden" name="quserRealName" value="<%=quserRealName %>"/>
			<input type="hidden" name="pageNum" value="<%=pageNum %>"/>
		</td>
		<td><font style="color: red; font-size: small;">*不可编辑</font></td>
	</tr>
	<tr>
	</tr>
	<tr>
		<th align="left">姓名</th>
		<td align="left"><input type="text" name="name" value="<%=userInfo.getUserRealName()%>" id="name">
		</td>
		<td><font style="color: red; font-size: small;">*由汉字、字母、数字组成长度小于20个字符</font></td>
	</tr>
	<tr>
	</tr>
	<tr>
		<th align="left">密码:</th>
		<td align="left""><input type="password" name="psd" id="psd"
			value="<%=userInfo.getPassword()%>"></td>
		<td><font style="color: red; font-size: small;">*由字母、数字、下划线组成长度小于20个字符</font></td>
	</tr>
	<tr>
	</tr>
	<tr>
		<th align="left">权限：</th>
		<td align="left"><select style="width: 4cm" name="permissionID" id="2" >
			<%
				List<Authority> auths = (ArrayList) request.getAttribute("auths");
					if (auths != null) {
						for (Authority auth : auths) {
							if (permission == auth.getPermission()) {
			%>
			<option value="<%=auth.getPermissionID()%>" selected="selected"><%=auth.getPermissionName()%></option>
			<%
				} else {
			%>
			<option value="<%=auth.getPermissionID()%>"><%=auth.getPermissionName()%></option>
			<%
				}
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
		<td align="left"><input type="text" name="mail" id="mail"
			value="<%=userInfo.getMail()%>"></input></td>
		<td><font style="color: red; font-size: small;">*由字母、数字、下划线、@以及．组成长度小于50个字符</font></td>
	</tr>
	<tr>
	<td colspan="3" align="right"><a href="user/userNextList.action?pageNum=<%=pageNum %>&quserRealName=<%=quserRealName %>">返回列表</a><input type="submit" name="update" value="确认修改" style="background-color: #82daf0;margin-left: 30px"></td>
</tr>
</table>
<%
	}
%>
</form>
</body>
</html>