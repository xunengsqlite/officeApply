<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.fnst.officeapply.entity.*,java.util.ArrayList,java.util.List"%>
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
<title>欢迎进入办公用品预定系统</title>
</head>
<body style="background-image: url('images/login/bg_form.PNG'); font-family:SimSun ">
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
	<span style="color: red">成功!!</span>
	<%
		} else if ("false".equals(flag)) {
	%>
	<span style="color: red">失败!!</span>
	<%
		}
	%>

<table align="center">
	<tr>
		<th><font color="bule" size="5">预定办公用品列表</font></th>
	</tr>
</table>

<table align="center" border="0">
	<tr>
	   <td>
	   <form action="currentRecord/currRecordByGoods.action" method="get" >
	   <input type="submit" value="按物品查看" style="background-color:#a6eeb2"/>
	   </form>
	   </td>
	   <td><a href="homePage.action">返回申请页面</a></td>
	</tr>
</table>

<table align="center" border="1" width="75%" cellpadding="0" cellspacing="0" style="border: 1px;border-color: black;">
	<tr bgcolor="#FFFFF" align="center">
		<th>编号</th>
		<th>人名</th>
		<th>项目组</th>
		<th>物品及对应数量</th>
	</tr>

	<%
		int i = 0;
		List<String[]> currRecord = (ArrayList<String[]>) request.getAttribute("currRecord");
		if (currRecord != null) {
			for (String[] infos : currRecord) {
	%>
	<tr align="center">
		<td><%=++i%></td>
		<td align="left"><%=infos[0]%></td>
		<td align="left"><%=infos[1]%></td>
		<td align="left"><%=infos[2]%></td>	
	</tr>
	<%
		}
		}
	%>
	<tr><td colspan="4"></td></tr>
</table>
</body>
</html>