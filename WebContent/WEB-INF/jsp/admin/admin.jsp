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
<title>管理配置</title>

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
<body>
<%
	Map<String, String> adminInfos = (HashMap<String, String>)request.getAttribute("adminInfos");
	//String DBPoolName = "";
	String serverURL = "";
	String adminMailAddr = "";
	if(adminInfos != null){
		//DBPoolName = ComUtil.changeNullVal(adminInfos.get(AdminConfig.DATABASE_POOL_NAME),"");
		serverURL = ComUtil.changeNullVal(adminInfos.get(AdminConfig.SERVER_URL),"");
		adminMailAddr = ComUtil.changeNullVal(adminInfos.get(AdminConfig.ADMIN_MAIL_ADDRESS),"");
	}
%>
<table>
	<tr>
	<td><a href="admin/orgz/defOrgzList.action?orgzType=0">部门管理</a>|</td>
	<td><a href="admin/orgz/defOrgzList.action?orgzType=1">课室管理</a>|</td>
	<td><a href="admin/orgz/defOrgzList.action?orgzType=2">项目组管理</a>|</td>
	<td><a href="admin/user/defUserList.action">用户管理</a>|</td>
	<td><a href="admin/beanMonitor.action">对象监控</a></td>
	</tr>
</table>
<div class="title">系统设置</div>
<form id="form1" action="" method="POST">
	<table class="content" cellspacing="1px" width="500px">
		<%-- <tr>
			<th colspan="2">连接池设定</th>
		</tr>
		<tr>
			<td width="150px">连接池名字：</td><td><input size="40" type="text" name="DBPoolName" value="<%=DBPoolName %>" /></td>
		</tr> --%>
		<tr>
			<th colspan="2">系统信息设定</th>
		</tr>
		<tr>
			<td width="150px">系统登录地址:</td><td><input size="40" type="text" name="serverURL" value="<%=serverURL %>" /></td>
		</tr>
		<tr>
			<th colspan="2">管理者邮件地址设定</th>
		</tr>
		<tr>
			<td>管理者邮件地址:</td><td><input size="40" type="text" name="adminMailAddr" value="<%=adminMailAddr %>" /></td>
		</tr>
		<tr>
		<td colspan="2"><input class="button" type="button" value="保存" onclick="sendRequest('form1','admin/save.action')" />
						<!-- <input type="button" value="reload" onclick="sendRequest('form1','admin/reload.action')" /> --></td>
		</tr>
	</table>
</form>
</body>
</html>
