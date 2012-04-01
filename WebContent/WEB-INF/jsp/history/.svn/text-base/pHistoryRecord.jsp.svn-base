<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="com.fnst.officeapply.entity.*,java.util.*" %>
<%@ page import="com.fnst.officeapply.common.*;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String contextPath = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ contextPath + "/";
%>
<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎进入办公用品预定系统</title>
<style type="text/css">

.button{
	background-color: #a6eeb2;
}

</style>
</head>
<body
	style="background-image: url('images/login/bg_form.PNG'); font-family: SimSun">
	<table align="center">
		<tr>
			<jsp:include page="../common/header.jsp"></jsp:include>
		</tr>
	</table>
	<table align="left">
		<jsp:include page="../common/left.jsp"></jsp:include>
	</table>
		<table align="center">
			<tr>
				<th><font color="bule" style="font-weight: bolder; font-size: xx-large;">办公用品历史记录</font></th>
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
		<table align="center">
			<tr>
				<td>
					<form action="history/historyRecordByGoods.action" method="post">
						<input type="hidden" name="historyID" value="<%=historyID%>" />
						<input type="hidden" name="beginTime" value="<%=beginTime %>"/>
						<input type="hidden" name="endTime" value="<%=endTime %>"/>
						<input type="hidden" name="pageNum" value="<%=pageNum %>"/>
						<input type="submit" value="按物品查看"class="button" />
					</form>
				</td>
				<td>
					<form action=history/download.action method="post">
						<input type="hidden" name="downloadPageName" value="pdownload.jsp"/>
						<input type="hidden" name="historyID" value="<%=historyID%>" />
						<input type="submit" value="导出列表"class="button" />
					</form>
				</td>
				<td><a href="history/historyNextList.action?pageNum=<%=pageNum %>&beginTime=<%=beginTime %>&endTime=<%=endTime %>">&nbsp;&nbsp;返回</a></td>
			</tr>
		</table>
		<table align="center" border="1" width="75%" cellpadding="0"
			cellspacing="0" style="border: 1px; border-color: black;">
			<tr bgcolor="#FFFFF" align="center">
				<th>编号</th>
				<th>人名</th>
				<th>项目组</th>
				<th>物品及对应数量</th>
			</tr>
			
			<%
				int k = 0;
				List<String[]> historyRecords = (ArrayList<String[]>)request.getAttribute("historyRecords");
				if(historyRecords != null){
				for (String[] temp : historyRecords) {
			%>
			<tr align="center">
				<td><%=++k %></td>
				<td align="left"><%=temp[0]%></td>
				<td align="left"><%=temp[1]%></td>
				<td align="left"><%=temp[2]%></td>
			</tr>
			<%
				}}
			%>
			<tr align="left">
				<td colspan="4">备注：</td>
			</tr>
			<tr align="left">
				<td colspan="4"><%=note%></td>
			</tr>
		</table>
</body>
</html>