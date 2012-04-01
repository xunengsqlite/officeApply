<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String contextPath = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ contextPath + "/";
%>
<head>
<base href="<%=basePath %>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<table align="center" border="1" width="75%" cellpadding="0"
		cellspacing="0" style="border: 1px; border-color: black;">
		<tr><td colspan="2"><div>回到首页：<a href="jsp/goods/goodslist.jsp">请点这</a></div>
		<tr><td colspan="2"><div>上传链接：<a href="jsp/user/uploadUser.jsp">请点这</a></div>
		</td></tr>

		<tr bgcolor="#FFFFF">
			<th align="left">文件名</th>
			<th align="left">下载</th>
		</tr>

		<%
			Map<String, String> filemap = (HashMap) request.getAttribute("filemap");
			if(filemap==null){
				 return;
			}
			Iterator<Map.Entry<String, String>> it = filemap.entrySet().iterator();
			//String context =request.getContextPath();
			while (it.hasNext()) {
				Map.Entry<String, String> entry = it.next();
		%>
		<tr>
			<td><%=entry.getValue()%></td>
			<td><a href=FileDownloadServlet?path=<%=entry.getKey()%>>download</a></td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>