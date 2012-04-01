<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.fnst.officeapply.common.*" %>
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
<title>对象监控</title>

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
	.title{
		font-size: 30px;
	}
	body{
		background-image: url('images/login/bg_form.PNG'); font-family: SimSun;
	}
</style>
<script type="text/javascript">
	function sendRequest(form,actionName){
		var f = document.getElementById(form);
		f.action=actionName;
		f.submit();
	}
</script>

</head>

<body>
<table align="center">
	<tr>
		<jsp:include page="../common/header.jsp"></jsp:include>
	</tr>
</table>
<table align="left">
	<jsp:include page="../common/left.jsp"></jsp:include>
</table>

<div class="title">对象数量监控</div>
	<table class="content" cellspacing="1px" width="500px">
		<tr>
			<th>对象名:</th><th>数量</th>
		</tr>
		<%
			Map<String, Integer> beansInfo = (HashMap<String, Integer>) request.getAttribute("beansInfo");
			if (beansInfo != null) {
				Set<String> keys = beansInfo.keySet();
				for (String key : keys) {
					Integer num = beansInfo.get(key);
					num = (num == null ? 0 : num);
		%>
		<tr>
		<td><%=key %></td><td><%=num %></td>
		</tr>
		<%
			}
			}
		%>
	</table>
</body>
</html>
