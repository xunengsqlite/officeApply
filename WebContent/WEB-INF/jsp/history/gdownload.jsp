<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*,com.fnst.officeapply.entity.*,com.fnst.officeapply.common.*"%>
<%
	response.setContentType("application/vnd.ms-excel");
	response.setHeader("Content-disposition",
			"inline; filename="+new String("办公用品申请单.xls".getBytes(),"iso-8859-1"));
	//以上这行设定传送到前端浏览器时的档名为办公用品申请单.xls
	//就是靠这一行，让前端浏览器以为接收到一个excel档
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="tableshow">
		<table border="1" width="75%" cellpadding="0" cellspacing="0"
			style="margin-left: 180px; border: 1px; border-color: black;">
			<tr align="center">
				<th>编号</th>
				<th>办公用品</th>
				<th>申请总数</th>
				<th>申请人及对应数量</th>
			</tr>
			
			<%
				int k = 0;
				String note = ComUtil.changeNullVal((String)request.getAttribute("note"),"");
				List<String[]> historyRecords = (ArrayList<String[]>)request.getAttribute("historyRecords");
				if(historyRecords != null){
				for (String[] temp : historyRecords) {
			%>
			<tr align="center">
				<td><%=++k %></td>
				<td><%=temp[0] %></td>
				<td><%=temp[1] %></td>
				<td align="left"><%=temp[2] %></td>
			</tr>
			<%
				}}
			%>
			<tr align="left">
				<td colspan="4"><%=note%></td>
			</tr>
		</table>
	</div>
</body>
</html>