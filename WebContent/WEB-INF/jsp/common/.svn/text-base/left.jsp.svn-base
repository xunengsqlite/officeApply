<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String basePathPath = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ basePathPath + "/";
	String listID = (String)session.getAttribute("listID");
	//System.out.print(listID);
%>
<head>
<base href="<%=basePath %>"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style type="text/css">
span{color: #00008B;}
span:hover {
	color: red;
	background-color:blue; 
} /* 当有鼠标悬停在链接上 */

li {
	list-style: none;
	margin-left: 14px;
}

ul {
	padding-left: 1px;
}
a:hover {color: #FF00FF;background-color: blue;}
a:VISITED{color: #473C8B;}
a {
	text-decoration: none;
	color: #473C8B;
}

.unfold{
	cursor: pointer;
}
</style>
</head>

<body style="font-family:SimSun">

<table align="left"
	style="width:14%; height: 100%; border-right: solid 2px #666; background-image: url('<%=basePath %>/images/login/bg_form.PNG'); background-attachment: fixed;">
	<tr align="center">
		<td style="font-family:SimSun ; font-size: 33px; font-weight: bold;">索引</td>
	</tr>
	<tr><td height="50"><ul class="u1">
		<span class="unfold" id="true">-办公用品申请</span>
		<li><a href="homePage.action?listID=10001a">申请页面</a></li>
		<li><a href='currentRecord/currRecordByUser.action?listID=10002a'>本次申请汇总</a></li>
		<li><a href='history/historyNextList.action?listID=10003a'>办公用品历史记录</a></li>
		<li><a href="currentRecord/enableApplyPage.action?listID=10004a">办公用品申请确认</a></li>
	</ul></td></tr>
	<tr><td height="50"><ul class="u1">
		<span class="unfold" id="true">-用户管理</span>
		<li><a href="user/userNextList.action?listID=10005a">用户列表</a></li>
		<li><a href='user/userAdderPage.action?listID=10006a'>用户添加</a></li>
		<li><a href='user/mailPage.action?listID=10007a'>邮件提醒</a></li>
	</ul></td></tr>
	<tr><td height="50"><ul class="u1">
		<span class="unfold" id="true">-超级管理</span>
		<li><a href="admin/orgz/defOrgzList.action?listID=10009a&orgzType=0">部门管理</a></li>
		<li><a href="admin/orgz/defOrgzList.action?listID=100010&orgzType=1">课管理</a></li>
		<li><a href="admin/orgz/defOrgzList.action?listID=100011&orgzType=2">项目组管理</a></li>
		<li><a href="admin/user/userNextList.action?listID=100012">高级用户管理</a></li>
		<li><a href="goods/manager/goodsNextList.action?listID=10013">物品管理</a></li>
	</ul></td></tr>
	<tr><td height="50"><ul class="u1">
		<span class="unfold" id="true">-系统管理</span>
		<li><a href="admin/adminPage.action?listID=10008a">系统配置</a></li>
		<li><a href="admin/beanMonitor.action?listID=10008b">对象监控</a></li>
	</ul></td></tr>
	<tr><td height="50"><ul class="u1">
		<span class="unfold" id="true">-个人设置</span>
		<li><a href="user/psdUpdatePage.action?listID=100014">密码修改</a></li>
	</ul></td></tr>
</table>

<script type="text/javascript">
var obj = document.getElementsByTagName("a");
for(var i=0;i<obj.length;i++){
	var href = (String)(obj[i].href);
	var index = href.indexOf("listID=" + '<%=listID%>');
	<%-- //console.log(href + '<%=listID%>'); --%>
	if(index != -1){
		obj[i].style.background="red";
	}
}

</script>

</body>
</html>
