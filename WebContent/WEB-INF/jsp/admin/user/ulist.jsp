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
<title>欢迎进入用户管理</title>
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
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jqueryauto.js"></script>
<script type="text/javascript">
var submit = function(){
	var name = $("#qword").val();
	var groupName = $("select[name='qgroupName'] option:selected").val();
	$("#qwordAct").val(name);
	$(":hidden[name='qgroupName']").val(groupName);
	$("#queryForm").submit();
};
</script>
<body style="background-image: url('images/login/bg_form.PNG'); font-family: SimSun">
<table align="center">
	<tr>
		<jsp:include page="../../common/header.jsp"></jsp:include>
	</tr>
</table>
<table align="left">
	<jsp:include page="../../common/left.jsp"></jsp:include>
</table>
	
<table class="content">
<tr>
<th>项目组：</th><td align="left">
			<select style="width: 4cm" name="qgroupName">
			<option value="">不选择</option>
			<%
				List<Organization> projs = (ArrayList) request.getAttribute("projs");
				if (projs != null) {
					for (Organization proj : projs) {
			%>
			<option value="<%=proj.getName()%>"><%=proj.getName()%></option>
			<%
				}
				}
			%>
		</select></td>
<th>用户姓名:</th><td><input type="text" name="quserRealName" id="qword" alt="admin/user/autoComplete.action"/></td>
<td><input type="submit" id="queryButton" value="查询" class="button"/></td>
<td ><font color="red">*默认查询所有用户</font></td>
</tr>

</table>
<div id="auto"></div>
<form id="queryForm">
	<input type="hidden" name="quserRealName" id="qwordAct"/>
	<input type="hidden" name="qgroupName" />
</form>
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
		String qgroupName = ComUtil.changeNullVal((String)request.getAttribute("qgroupName"),"");
		String quserRealName = ComUtil.changeNullVal((String)request.getAttribute("quserRealName"),"");
		Integer pageNum = (Integer)request.getAttribute("pageNum_u");
		pageNum = (pageNum == null ? 1 : pageNum);
	%>
	
<form id = "addform" action="admin/user/userAdderPage.action" method="get">
<table width="75%" border="0">
	<tr align="center">
		<td align="right">
		<input type="hidden" name="qgroupName" value="<%=qgroupName %>"/>
		<input type="hidden" name="quserRealName" value="<%=quserRealName %>"/>
		<input type="hidden" name="pageNum" value="<%=pageNum%>"/>
		<input type="submit" value="添加用户" class="button">
		</td>
	</tr>
</table>
</form>
<div class="userList">
<table class="content" align="left" border="1" width="75%" cellpadding="0"
	cellspacing="0" >
	<tr bgcolor="#FFFFF">
		<th align="left">编号</th>
		<th align="left">账号</th>
		<th align="left">姓名</th>
		<th align="left">邮箱</th>
		<th align="left">部门</th>
		<th align="left">课</th>
		<th align="left">项目组</th>
		<th align="left">权限</th>
		<th align="left">操作</th>
	</tr>
	<%
		Integer i = (Integer)request.getAttribute("serialNum");
		List<UserInfo> users = (List<UserInfo>) request.getAttribute("users");
		
		Integer lastPageNum = (Integer)request.getAttribute("lastPageNum");
		lastPageNum = (lastPageNum == null ? 1 : lastPageNum);
		if (users != null && i != null) {
			for (UserInfo temp : users) {
				String username = temp.getUserName();
	%>
	<tr>
		<td><%=++i%></td>
		<td id=<%=i%>><%=username%></td>
		<td><%=temp.getUserRealName()%></td>
		<td><%=temp.getMail()%></td>
		<td><%=temp.getDepartmentName() %></td>
		<td><%=temp.getOfficeName() %></td>
		<td><%=temp.getGroupName() %></td>
		<td><%=temp.getPermissionName()%></td>
		<td><form id="form<%=i %>" action="" method="post">
			<input type="hidden" name="username" value="<%=username %>"/>
			<input type="hidden" name="userID" value="<%=temp.getUserID() %>"/>
			<input type="hidden" name="qgroupName" value="<%=qgroupName %>"/>
			<input type="hidden" name="quserRealName" value="<%=quserRealName %>"/>
			<input type="hidden" name="pageNum" value="<%=pageNum%>"/>
			<input onclick="handle('form<%=i %>', 'admin/user/userUpdatePage.action', 1)" type="button" value="修改" class="button"/> 
			<input type="button" onclick="handle('form<%=i %>', 'admin/user/delUser.action', 0)" value="删除" class="button">
			</form>
		</td>
	</tr>
	<%
		}
		}
	%>
	<tr><td colspan="9" align="right">
		<a href="admin/user/userNextList.action?pageNum=1&qgroupName=<%=qgroupName %>&quserRealName=<%=quserRealName %>">首页</a>
		<a href="admin/user/userNextList.action?pageNum=<%=pageNum - 1%>&qgroupName=<%=qgroupName %>&quserRealName=<%=quserRealName %>">上一页</a>
		<a href="admin/user/userNextList.action?pageNum=<%=pageNum + 1%>&qgroupName=<%=qgroupName %>&quserRealName=<%=quserRealName %>">下一页</a>
		<a href="admin/user/userNextList.action?pageNum=<%=lastPageNum %>&qgroupName=<%=qgroupName %>&quserRealName=<%=quserRealName %>">末页</a></td></tr>
	<tr><td colspan="9"></td></tr>
</table>
</div>

</body>
</html>