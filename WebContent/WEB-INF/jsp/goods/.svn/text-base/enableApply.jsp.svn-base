<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.fnst.officeapply.entity.*"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String contextPath = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ contextPath + "/";
%>
<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>最终申请提交</title>
</head>
<!-- <script type="text/javascript" src="js/common.js"></script> -->

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
		<th><font color="bule" style="font-weight: bolder; font-size: xx-large;">办公用品最终确认</font></th>
	</tr>
</table>

<form action="currentRecord/allowApply.action" method="post">
<table align="center" border="1" width="75%" cellpadding="0"
	cellspacing="0" style="border: 1px; border-color: black;">
	<tr align="center">
		<th>编号</th>
		<th>办公用品</th>
		<th>申请总数</th>
		<th>申请人及对应数量</th>
		<th>是否允许申请</th>
	</tr>
	<%
		int i = 0;
		List<String[]> currRecord = (ArrayList<String[]>) request.getAttribute("currRecord");
		if (currRecord != null) {
			for (String[] infos : currRecord) {
	%>
		<tr align="center">
		<td><%=++i%></td>
		<td align="left"><%=infos[0] %></td>
		<td align="left"><%=infos[1] %></td>
		<td align="left"><%=infos[2] %></td>
		<td align="left"><input type="checkbox" name="goodsNameList" value="<%=infos[0] %>"/></td>
		</tr>
	<%
		}
		}
	%>
	<tr align="left">
		<td colspan="5">添加备注：</td>
	</tr>
	<tr align="left">
		<td colspan="5"><textarea rows="3" cols="130" name="note"></textarea>
		</td>
	</tr>
</table>

<table align="center" width="30%">
	<tr align="center">
		<td><label><span style="background-color: #82daf0">全选</span><input type="checkbox" name="allselect" value="全选"
			onClick="selAll('goodsNameList',this.checked)"></label></td>
		<td><input type="submit" name="submit" value="提交"
			style="background-color: #82daf0" /></td>
	</tr>
</table>
</form>
</body>
</html>