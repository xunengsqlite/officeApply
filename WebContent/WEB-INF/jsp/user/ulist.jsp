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
<script type="text/javascript" src="js/manageUser.js"></script>
<script type="text/javascript" src="js/jqueryauto.js"></script>
<script type="text/javascript">
var submit = function(){
	var name = $("#qword").val();
	$("#qwordAct").val(name);
	$("#queryForm").submit();
};
</script>

<body style="background-image: url('images/login/bg_form.PNG'); font-family: SimSun">
<table align="center">
	<tr>
		<jsp:include page="../common/header.jsp"></jsp:include>
	</tr>
</table>
<table align="left">
	<jsp:include page="../common/left.jsp"></jsp:include>
</table>

<table class="content">
<tr>
<th>用户姓名:</th>
<td><input type="text" name="quserRealName" id="qword" alt="user/autoComplete.action"/></td>
<td><input type="button" id="queryButton" value="查询" class="button"/></td>
<td ><font color="red">*默认查询所有用户</font></td>
</tr>
</table>
<div id="auto"></div>
<form id="queryForm">
	<input type="hidden" name="quserRealName" id="qwordAct"/>
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
			String quserRealName = ComUtil.changeNullVal((String)request.getAttribute("quserRealName"),"");
			Integer pageNum = (Integer)request.getAttribute("pageNum_u");
			pageNum = (pageNum == null ? 1 : pageNum);
		%>
		
<form id = "addform" action="" method="get">
<table width="75%">
<!-- 	<tr>
		<th><font color="bule" style="font-weight: bolder; font-size: xx-large;">用户管理</font></th>
	</tr> -->
	<tr align="center">
		<td align="right" colspan="5">
		<input type="hidden" name="quserRealName" value="<%=quserRealName %>"/>
		<input type="hidden" name="pageNum" value="<%=pageNum%>"/>
		<input type="button" value="邮件提醒" onclick="adminUser('addform','user/mailPage.action')" style="background-color: #82daf0; margin-right: 10px" />
		<input type="button" value="添加用户" class="button" onclick="adminUser('addform','user/userAdderPage.action')"></td>
	</tr>
</table>
</form>
<div class="userList">
<form id="ULForm" action="" method="post">
<table id="ec_table" class="content" border="1" width="75%" cellpadding="0"
	cellspacing="0" >
	<tr bgcolor="#FFFFF">
		<th align="left" >选择</th>
		<th align="left" onclick="sortTable('ec_table',1,'string')">编号</th>
		<th align="left" onclick="sortTable('ec_table',2,'string')">账号</th>
		<th align="left" onclick="sortTable('ec_table',3,'string')">姓名</th>
		<th align="left" onclick="sortTable('ec_table',4,'string')">邮箱</th>
		<th align="left" onclick="sortTable('ec_table',5,'string')">部门</th>
		<th align="left" onclick="sortTable('ec_table',6,'string')">课</th>
		<th align="left" onclick="sortTable('ec_table',7,'string')">项目组</th>
		<th align="left" onclick="sortTable('ec_table',8,'string')">权限</th>
		<th align="left">操作</th>
	</tr>
	<%
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		Integer i = (Integer)request.getAttribute("serialNum");
		
		Integer lastPageNum = (Integer)request.getAttribute("lastPageNum");
		lastPageNum = (lastPageNum == null ? 1 : lastPageNum);
		String admin = userInfo.getUserName();
		List<UserInfo> users = (List<UserInfo>) request.getAttribute("users");
		if (users != null && i != null) {
			for (UserInfo temp : users) {
				String username = temp.getUserName();
	%>
	<tr><td><input type="checkbox" name="userIDArray" value="<%=temp.getUserID() %>"/></td>
		<td><%=++i%></td>
		<td id=<%=i%>><%=username%></td>
		<td><%=temp.getUserRealName()%></td>
		<td><%=temp.getMail()%></td>
		<td><%=temp.getDepartmentName() %></td>
		<td><%=temp.getOfficeName() %></td>
		<td><%=temp.getGroupName() %></td>
		<td><%=temp.getPermissionName()%></td>
		<td>
			<input type="button" onclick="handleUser('<%=username%>',<%=temp.getUserID() %>,'operUserForm','user/userUpdatePage.action','<%=admin%>', 1)" name="update" value="修改" class="button"/> 
			<input type="button" onclick="handleUser('<%=username%>',<%=temp.getUserID() %>,'operUserForm','user/delUser.action','<%=admin%>', 0)" name="delete" value="删除" class="button">
		</td>
	</tr>
	<%
		}
		}
	%>
	<tr><td colspan="10" align="right">
		<input type="hidden" name="quserRealName" value="<%=quserRealName %>"/>
		<input type="hidden" name="pageNum" value="<%=pageNum%>"/>
		<label><span class="button">全选</span>
		<input type="checkbox" value="全选" onClick="selAll('userIDArray',this.checked)"></label>
		<input type="button" class="button" value="删除选中" onclick="handle('ULForm', 'user/delAll.action', 0)"/></td>
	</tr>
	<tr><td colspan="10" align="right">
		<a href="user/userNextList.action?pageNum=1&quserRealName=<%=quserRealName %>">首页</a>
		<a href="user/userNextList.action?pageNum=<%=pageNum - 1%>&quserRealName=<%=quserRealName %>">上一页</a>
		<a href="user/userNextList.action?pageNum=<%=pageNum + 1%>&quserRealName=<%=quserRealName %>">下一页</a>
		<a href="user/userNextList.action?pageNum=<%=lastPageNum %>&quserRealName=<%=quserRealName %>">末页</a></td>
	</tr>
	<tr><td colspan="10"></td></tr>
</table>
</form>

<form id="operUserForm" action="" method="post">
	<input type="hidden" name="username"/>
	<input type="hidden" name="userID"/>
	<input type="hidden" name="quserRealName" value="<%=quserRealName %>"/>
	<input type="hidden" name="pageNum" value="<%=pageNum%>"/>
</form>
</div>

</body>
</html>
