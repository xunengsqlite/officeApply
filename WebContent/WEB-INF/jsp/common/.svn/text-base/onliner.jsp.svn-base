<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.fnst.officeapply.common.*" %>
<%@ page import="com.fnst.officeapply.entity.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在线用户</title>
</head>
<body>
<h1>在线用户列表</h1>
<table id="ec_table" class="content" border="1" width="75%" cellpadding="0"
	cellspacing="0" >
	<tr bgcolor="#FFFFF">
		<th align="left" onclick="sortTable('ec_table',1,'string')">编号</th>
		<th align="left" onclick="sortTable('ec_table',2,'string')">账号</th>
		<th align="left" onclick="sortTable('ec_table',3,'string')">姓名</th>
		<th align="left" onclick="sortTable('ec_table',4,'string')">邮箱</th>
		<th align="left" onclick="sortTable('ec_table',5,'string')">部门</th>
		<th align="left" onclick="sortTable('ec_table',6,'string')">课</th>
		<th align="left" onclick="sortTable('ec_table',7,'string')">项目组</th>
		<th align="left" onclick="sortTable('ec_table',8,'string')">权限</th>
	</tr>
	<%	int i = 0;
		Set<UserInfo> users = (HashSet<UserInfo>) request.getAttribute("users");
		if (users != null) {
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
	</tr>
	<%
		}
		}
	%>
	<tr><td colspan="10"></td></tr>
</table>
</body>
</html>