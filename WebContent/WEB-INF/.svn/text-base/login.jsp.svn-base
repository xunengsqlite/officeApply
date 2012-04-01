<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.fnst.officeapply.framework.*,java.util.*,com.fnst.officeapply.framework.RedirectBean.*" %>
<%@ page import="com.fnst.officeapply.common.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String basePathPath = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ basePathPath + "/";
%>
<head>
<base href="<%=basePath %>"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>办公用品管理系统</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/cookie.js"></script>
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
	.mouse{
		cursor: pointer;
	}
	a:hover {color: #FF00FF;background-color: blue;}

a {
	text-decoration: none;
	color: #473C8B;
}
</style>
</head>
<body onload="checkCookie()" style="background-image: url('images/login/officeApply3.jpg'); background-repeat: no-repeat; background-attachment: fixed; background-position: center; font-family:SimSun ">
<!-- <table align="left"  border="1" style="width: 12%;height: 100%; border-right: solid 2px #666;">
	<tr align="center">
		<td style="font-family:SimSun ; font-size: 33px; font-weight: bold;">索引</td>
	</tr>
	<tr>
		<td height="50"><a href="homePage.action">办公用品申请页面</a></td>
	</tr>
	<tr>
		<td height="50"><a href='currentRecord/currRecordByUser.action'>本次申请汇总</a></td>
	</tr>
	<tr>
		<td height="50"><a href='history/historyNextList.action'>办公用品历史记录</a></td>
	</tr>
	<tr>
		<td height="50"><a href="currentRecord/enableApplyPage.action">办公用品申请确认</a></td>
	</tr>
	<tr>
		<td height="50"><a href="user/userList.action">用户管理</a></td>
	</tr>
	<tr>
		<td height="50"><a href="goods/manager/goodsNextList.action">物品管理</a></td>
	</tr>
	<tr>
		<td height="50"><a href="user/psdUpdatePage.action">密码修改</a></td>
	</tr>
	<tr>
		<td height="50"><a href="admin/adminPage.action">系统配置</a></td>
	</tr>
</table> -->
<table align="left"  style="width: 15%;height: 100%;margin-top: 30px;border-right: solid 2px #666;"">
	<tr height="30">
		<td>每</td><td>就</td><td>忍</td><td>最</td>
	</tr>
	<tr height="30">
		<td>一</td><td>是</td><td>受</td><td>后</td>
	</tr>
	<tr height="30">
		<td>个</td><td>那</td><td>孤</td><td>度</td>
	</tr>
	<tr height="30">
		<td>优</td><td>一</td><td>独</td><td>过</td>
	</tr>
	<tr height="30">
		<td>秀</td><td>段</td><td>和</td><td>了</td>
	</tr>
	<tr height="30">
		<td>的</td><td>时</td><td>寂</td><td>这</td>
	</tr>
	<tr height="30">
		<td>人</td><td>光</td><td>寞</td><td>段</td>
	</tr>
	<tr height="30">
		<td> </td><td> </td><td> </td><td>感</td>
	</tr>
	<tr height="30">
		<td>都</td><td>是</td><td>不</td><td>动</td>
	</tr>
	<tr height="30">
		<td>有</td><td>付</td><td>抱</td><td>自</td>
	</tr>
	<tr height="30">
		<td>一</td><td>出</td><td>怨</td><td>己</td>
	</tr>
	<tr height="30">
		<td>段</td><td>了</td><td> </td><td>的</td>
	</tr>
	<tr height="30">
		<td>沉</td><td>很</td><td>不</td><td>日</td>
	</tr>
	<tr height="30">
		<td>默</td><td>多</td><td>诉</td><td>子</td>
	</tr>
	<tr height="30">
		<td>的</td><td>的</td><td>苦</td><td></td>
	</tr>
	<tr height="30">
		<td>时</td><td>努</td><td></td><td></td>
	</tr>
	<tr height="30">
		<td>光</td><td>力</td><td></td><td></td>
	</tr>
</table>
<form action="login.action" method="post">
		<%
			RedirectBean bean = (RedirectBean) request.getAttribute("redirectBean");
			if (bean != null) {
				Iterator<SimpleEntry> it = bean.iterator();
				while (it.hasNext()) {
					SimpleEntry en = it.next();
		%>
		<input type="hidden" name="<%=en.getName()%>" value="<%=en.getValue()%>" />
		<%
				}
			}
			String name = ComUtil.changeNullVal((String) request.getAttribute("name"),"");
			String psd =  ComUtil.changeNullVal((String) request.getAttribute("psd"),"");
			String errMsg =  ComUtil.changeNullVal((String) request.getAttribute("errMsg"),"");
		%>
		
		<table class="content" align="left" border="0" width="300px" height="100px" style="margin-top: 30px;margin-left: 1px;">
			<tr height="5"></tr>
			<tr>
				<th colspan="2" style="color: olive; font-weight: bolder; font-size: xx-large; font-family: SimSun">办公用品管理系统</th>
			</tr>
			<tr><td colspan="2" height="20">
			<span style="color: red"><%=errMsg %></span>
			</td></tr>
			<tr>
				<td height="35" align="right">账号：</td>
				<td align="left"><input type="text" name="name" value="<%=name %>"
					style="width: 150px"></td>
			</tr>
			<tr>
				<td height="35" align="right">密码：</td>
				<td align="left"><input type="password" name="psd" value="<%=psd %>" onpaste="return false"
					style="width: 150px"></td>
			</tr>
			<tr>
				<td height="35" align="right">验证码：</td>
				<td align="left"><input type="text" size="2" name="validateCode" />&nbsp;&nbsp;<img id="validate" alt="验证码" src="validateCode.action"  />&nbsp;
				<span class="mouse" onclick="changeIMG('validate','validateCode.action?t=')">换一张</span></td>
			</tr>
			<tr height="10px"></tr>
			<tr>
				<td align="center" colspan="2"><input type="submit" name="login" value="登录"
					style="background-color: #39b9d9">
				<input type="reset" name="reset" value="重置"
					style="background-color: #ee587f"></td>
			</tr>
			<tr><td>Cookie保存期限：</td>
			<td><label><input type="radio" name="cookieTimeout" value="-1" checked="checked"/>即时</label>
				<label><input type="radio" name="cookieTimeout" value="<%=24 * 60 * 60%>"/>一天</label>
				<label><input type="radio" name="cookieTimeout" value="<%=7 * 24 * 60 * 60%>"/>一周</label>
				<label><input type="radio" name="cookieTimeout" value="<%=30 * 24 * 60 * 60%>"/>一个月</label>
				<label><input type="radio" name="cookieTimeout" value="<%=90 * 24 * 60 * 60%>"/>三个月</label>
				</td></tr>
		</table>
</form>
</body>
</html>
