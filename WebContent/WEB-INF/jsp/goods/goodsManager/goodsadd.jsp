<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.fnst.officeapply.common.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String contextPath = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ contextPath + "/";
%>
<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>goods add</title>
<script type="text/javascript" src="js/checkGoods.js"></script>
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
</style>
</head>
<body style="background-image: url('images/login/bg_form.PNG'); font-family: SimSun">
<table align="center">
	<tr>
		<jsp:include page="../../common/header.jsp"></jsp:include>
	</tr>
</table>
<table align="left">
	<jsp:include page="../../common/left.jsp"></jsp:include>
</table>
	<%
		String flag = request.getParameter("isOK");
		if ("true".equals(flag)) {
	%>
	<span style="color: red">成功!!</span>
	<%
		} else if ("false".equals(flag)) {
	%>
	<span style="color: red">失败!!(可能已经存在该物品)</span>
	<%
		}
	%>
	<%
		String qGoodsName = ComUtil.changeNullVal((String)request.getAttribute("qGoodsName"),"");
		Integer pageNum = (Integer)request.getAttribute("pageNum_g");
		pageNum = (pageNum == null ? 1 : pageNum);
	%>
	
<table>
	<tr align="center">
		<th><font color="bule" style="font-weight: bolder; font-size: xx-large;">物品添加</font></th>
	</tr>
</table>
<form name ="goodsform" action="goods/manager/addGoods.action" method="post">
<table class="content">
	<tr>
		<th align="left">物品名:</th>
		<td align="left">
			<input type="text" name="goodsName" id="goodsName">
			<input type="hidden" name="qGoodsName" value="<%=qGoodsName%>"/>
			<input type="hidden" name="pageNum" value="<%=pageNum%>"/>
		</td>
		<td><font style="color: red; font-size: small;">*长度大于0的任意非空字符</font></td>
	</tr>
	<tr>
	</tr>
	<tr>
		<th align="left">物品单位:</th>
		<td align="left"><input type="text" name="goodsUnit" id="goodsUnit">
		</td>
		<td><font style="color: red; font-size: small;">*长度大于0的任意非空字符</font></td>
	</tr>
	<tr>
		<td align="right" colspan="3"><a href="goods/manager/goodsNextList.action?pageNum=<%=pageNum %>&qGoodsName=<%=qGoodsName %>">返回列表</a><input type="submit" name="add" value="确认添加"
			style="background-color: #82daf0;margin-left: 30px">
	</tr>
</table>
<table align="center">
	
</table>
</form>

</body>
</html>