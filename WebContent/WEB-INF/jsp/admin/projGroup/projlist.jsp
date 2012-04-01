<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.fnst.officeapply.common.*" %>
<%@ page import="com.fnst.officeapply.entity.*" %>
<%@ page import="java.util.List,java.util.ArrayList" %>
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
<title>项目组管理</title>
<style type="text/css">
.content{
		background-color: gray;
		text-align: left;
		border: 1px;
		border-color: black;
	}
	.content td{
		background-color: rgb(232,253,253);
	}
	.content th{
		background-color: rgb(128,164,247);
	}
a {
	text-decoration: none;
}

.page {
	margin-left: 250px;
	margin-top: 20px;
}
.button{
	background-color: #82daf0;
}
</style>
</head>
<!--<script type="text/javascript" src="js/common.js"></script>-->

<body style="background-image: url('images/login/bg_form.PNG'); font-family: SimSun">
<table align="center">
	<tr>
		<jsp:include page="../../common/header.jsp"></jsp:include>
	</tr>
</table>
<table align="left">
	<jsp:include page="../../common/left.jsp"></jsp:include>
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
<form action="admin/orgz/adderPage.action" method="post">
<table width="40%">
	<tr>
		<th><font color="bule" style="font-weight: bolder; font-size: xx-large;">项目组管理</font></th>
	</tr>
	<tr align="center">
		<td align="right" colspan="5">
		<input type="hidden" name="orgzType" value="2"/>
		<input type="submit" name="add" value="添加项目组" class="button"></td>
	</tr>
</table>
</form>
<div>
<table class="content" align="left" border="1" width="40%" cellpadding="0"
	cellspacing="0" >
	<tr bgcolor="#FFFFF">
		<th align="left">编号</th>
		<th align="left">项目组</th>
		<th align="left">操作</th>
	</tr>
	<%
		Integer i = (Integer)request.getAttribute("serialNum");
		List<Organization> orgzs = (List<Organization>) request.getAttribute("orgzs");
		if (orgzs != null && i != null) {
			for (Organization orgz : orgzs) {
				String orgzName = orgz.getName();
				int orgzID = orgz.getId();
	%>
	<tr>
		<td><%=++i%></td>
		<td><%=orgzName %></td>
		<td><form id="form<%=i %>" action="" method="post">
			<input type="hidden" name="orgzType" value="2"/>
			<input type="hidden" name="orgzName" value="<%=orgzName %>" />
			<input type="hidden" name="orgzID" value="<%=orgzID %>"/>
			<input onclick="handle('form<%=i %>','admin/orgz/updatePage.action', 1)" type="button" name="update" value="修改" class="button"/> 
			<input type="button" onclick="handle('form<%=i %>','admin/orgz/del.action', 0)" name="delete" value="删除" class="button">
			</form>
		</td>
	</tr>
	<%
		}
		}
	%>
	<tr><td colspan="3" align="right">
	<form id="pageform" action="admin/orgz/orgzNextList.action" method="post">
	<input type="hidden" name="id" value=""/>
	<input type="hidden" name="orgzType" value="2"/>
	<input class="button" type="button" name="submit1" value="首页" onclick="changePage('pageform','1')"/>
	<input class="button" type="button" name="submit2" value="下一页" onclick="changePage('pageform','2')"/>
	<input class="button" type="button" name="submit3" value="上一页" onclick="changePage('pageform','3')"/>
	<input class="button" type="button" name="submit4" value="末页" onclick="changePage('pageform','4')" />
	</form>
	</td></tr>
</table>
</div>

</body>
</html>
