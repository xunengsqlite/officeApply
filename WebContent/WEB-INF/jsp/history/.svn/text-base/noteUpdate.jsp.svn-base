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
<title>修改备注</title>
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
		margin-left: 30px";
	}
</style>
</head>
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
		<th><font color="#0F500" style="font-weight: bolder;font-size: xx-large;">修改备注</font></th>
	</tr>
</table>
<%		
		String note = ComUtil.changeNullVal((String)request.getAttribute("note"),""); 
		Integer historyID = (Integer)request.getAttribute("historyID");
		Integer pageNum = (Integer)request.getAttribute("pageNum_h");
		pageNum = (pageNum == null ? 1 : pageNum);
		String beginTime = ComUtil.changeNullVal((String)request.getAttribute("beginTime"),""); 
		String endTime = ComUtil.changeNullVal((String)request.getAttribute("endTime"),"");
%>
<form name="form" action="history/updateNote.action" method="post">
<table class="content">
	<tr>
		<th align="left">备注:</th>
		<td align="left">
			<input type="hidden" name="historyID" value="<%=historyID %>"/>
			<input type="hidden" name="beginTime" value="<%=beginTime %>"/>
			<input type="hidden" name="endTime" value="<%=endTime %>"/>
			<input type="hidden" name="pageNum" value="<%=pageNum %>"/>
			<textarea name="note" cols="60"><%=note %></textarea></td>
		<td><font style="color: red; font-size: small;">*小于255个字符</font></td>
	</tr>
	<tr>
	<td colspan="3" align="right"><a href="history/historyNextList.action?pageNum=<%=pageNum %>&beginTime=<%=beginTime %>&endTime=<%=endTime %>">返回列表</a>
	<input type="submit" value="确认修改" class="button"></td>
</tr>
</table>
</form>
</body>
</html>