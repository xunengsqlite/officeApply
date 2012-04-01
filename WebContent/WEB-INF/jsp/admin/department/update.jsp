<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.fnst.officeapply.entity.*" %>
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
<title>部门修改</title>
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
</head>
<body style="background-image: url('images/login/bg_form.PNG'); font-family: SimSun">
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
		<th><font color="bule" style="font-weight: bolder; font-size: xx-large;">部门修改</font></th>
	</tr>
</table>
<%Organization orgz = (Organization)request.getAttribute("orgz");
int orgzID = (orgz == null ? -1 : orgz.getId());
String orgzName = (orgz == null ? "" : orgz.getName());
%>
<form action="admin/orgz/update.action" method="post" >
<table class="content">
	<tr>
		<th align="left">部门名:</th>
		<td align="left">
		<input type="hidden" name="orgzType" value="0"/>
		<input type="hidden" name=orgzID value="<%=orgzID %>"/>
		<input type="hidden" name="oldOrgzName" value="<%=orgzName %>"/>
		<input type="text" name="orgzName" value="<%=orgzName %>">
		</td>
		<td><font style="color: red; font-size: small;">*任意非空字符(不含空格),长度(1-255)</font></td>
	</tr>
	<tr>
		<td align="right" colspan="3"><a href="admin/orgz/orgzNextList.action?orgzType=0">返回列表</a><input type="submit" name="update" value="确认修改"
			class="button">
	</tr>
</table>
</form>

</body>
</html>