<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.*,com.fnst.officeapply.entity.*"%>
<%@ page import="com.fnst.officeapply.common.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String contextPath = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ contextPath + "/";
%>
<head>
<base href="<%=basePath %>"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>办公用品历史记录查看</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/calendar.js"></script>
<!-- <script type="text/javascript" src="js/common.js"></script> -->
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

.button{
	background-color: #82daf0;
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
			String beginTime = ComUtil.changeNullVal((String)request.getAttribute("beginTime"),""); 
			String endTime = ComUtil.changeNullVal((String)request.getAttribute("endTime"),""); 
		%>
		
<form action="history/historyNextList.action" method="get">
	<table border="0" class="content">
		<tr><th>从</th>
			<td><input type="text" name="beginTime" value="" readonly="readonly" onclick="SelectDate(this)" /></td>
			<th>&nbsp;到</th>
			<td><input type="text" name="endTime" value=""readonly="readonly" onclick="SelectDate(this)" /></td>
			<td><input type="hidden" name="pageNum" value="1"/></td>
			<td><input type="submit" value="查询" class="button"/></td>
			</tr>
	</table>		
</form>
	<table>
		<tr>
			<th><font color="bule" style="font-weight: bolder; font-size: xx-large;">历史记录索引</font></th>
		</tr>
	</table>
	<table width="75%" border="1" cellpadding="0"
		cellspacing="0" style="border: 1px; border-color: black;">
		<tr bgcolor="#FFFFF" align="center">
			<th>编号</th>
			<th>历史记录</th>
			<th>项目组</th>
			<th>备注</th>
			<th>修改备注</th>
		</tr>
		<%List<HistoryRecordList> historyList = (ArrayList<HistoryRecordList>)request.getAttribute("historyList"); 
		Integer i = (Integer)request.getAttribute("serialNum");
		Integer pageNum = (Integer)request.getAttribute("pageNum_h");
		pageNum = (pageNum == null ? 1 : pageNum);
		Integer lastPageNum = (Integer)request.getAttribute("lastPageNum");
		lastPageNum = (lastPageNum == null ? 1 : lastPageNum);
		
		if(historyList != null && i !=null){
			for(HistoryRecordList temp : historyList){
		%>
		<tr align="left">
			<td><%=++i%></td>
			<td><a title="点击详情" href="history/historyRecordByUser.action?historyID=<%=temp.getHistoryRecordListID()%>&pageNum=<%=pageNum %>&beginTime=<%=beginTime %>&endTime=<%=endTime %>">
			<%=temp.getHistoryRecordDate()%></a></td>
			<td><%=temp.getGroupName() %></td>
			<td width="40%"><%=temp.getNote()%></td>
			<td><form id="form<%=i %>" action="history/noteUpdatePage.action" method="post">
				<input type="hidden" name="historyID" value="<%=temp.getHistoryRecordListID()%>"/>
				<input type="hidden" name="beginTime" value="<%=beginTime %>"/>
				<input type="hidden" name="endTime" value="<%=endTime %>"/>
				<input type="hidden" name="pageNum" value="<%=pageNum %>"/>
				<input type="submit" name="update" value="修改" class="button"/> 
			</form></td>
		</tr>
		<%}} %>
		<tr><td colspan="5" align="right">
		<a href="history/historyNextList.action?pageNum=1&beginTime=<%=beginTime %>&endTime=<%=endTime %>">首页</a>
		<a href="history/historyNextList.action?pageNum=<%=pageNum - 1%>&beginTime=<%=beginTime %>&endTime=<%=endTime %>">上一页</a>
		<a href="history/historyNextList.action?pageNum=<%=pageNum + 1%>&beginTime=<%=beginTime %>&endTime=<%=endTime %>">下一页</a>
		<a href="history/historyNextList.action?pageNum=<%=lastPageNum %>&beginTime=<%=beginTime %>&endTime=<%=endTime %>">末页</a></td></tr>
	</table>


<!-- <form id="pageform" action="history/historyNextList.action" method="post">
<table align="right" border="0">
	<tr><td>
	<input type="hidden" name="id" value=""/>
	<input class="button" type="button" name="submit1" value="首页" onclick="changePage('pageform','1')"/>
	<input class="button" type="button" name="submit2" value="下一页" onclick="changePage('pageform','2')"/>
	<input class="button" type="button" name="submit3" value="上一页" onclick="changePage('pageform','3')"/>
	<input class="button" type="button" name="submit4" value="末页" onclick="changePage('pageform','4')" />
	</td></tr>
</table>
</form> -->
	
</body>
</html>