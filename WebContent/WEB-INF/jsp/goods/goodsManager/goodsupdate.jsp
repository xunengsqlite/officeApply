<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.fnst.officeapply.entity.*" %>
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
<script type="text/javascript" src="js/jquery.js"></script>
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

<table>
	<tr align="center">
		<th><font color="bule" style="font-weight: bolder; font-size: xx-large;">物品修改</font></th>
	</tr>
</table>

<%Goods goods = (Goods)request.getAttribute("goods");
int goodsID = (goods == null ? -1 : goods.getId());
String goodsName = ComUtil.changeNullVal((goods == null ? null : goods.getGoodsName()),"");
String goodsUnit = ComUtil.changeNullVal((goods == null ? null : goods.getGoodsUnit()),"");
String qGoodsName = ComUtil.changeNullVal((String)request.getAttribute("qGoodsName"),"");
Integer pageNum = (Integer)request.getAttribute("pageNum_g");
pageNum = (pageNum == null ? 1 : pageNum);
%>

<form name ="goodsform" action="goods/manager/updateGoods.action" method="post" >
<table class="content">
	<tr>
		<th align="left">物品名:</th>
		<td align="left">
			<input type="hidden" name="goodsID" value="<%=goodsID %>"/>
			<input type="hidden" name="oldGoodsName" value="<%=goodsName %>"/>
			<input type="text" name="goodsName" value="<%=goodsName %>" id="goodsName">
			<input type="hidden" name="qGoodsName" value="<%=qGoodsName%>"/>
			<input type="hidden" name="pageNum" value="<%=pageNum%>"/>
			</td>
		<td><font style="color: red; font-size: small;">*长度大于0的任意非空字符</font></td>
	</tr>
	<tr>
	</tr>
	<tr>
		<th align="left">物品单位:</th>
		<td align="left">
			<input type="text" name="goodsUnit" value="<%=goodsUnit %>" id="goodsUnit">
		</td>
		<td><font style="color: red; font-size: small;">*长度大于0的任意非空字符</font></td>
	</tr>
	<tr>
		<td align="right" colspan="3">
		<a href="goods/manager/goodsNextList.action?pageNum=<%=pageNum %>&qGoodsName=<%=qGoodsName %>">返回列表</a>
		<input type="submit" value="确认修改" style="background-color: #82daf0;margin-left: 30px">
	</tr>
</table>
<table align="center">
	
</table>
</form>

</body>
</html>