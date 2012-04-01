<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="com.fnst.officeapply.entity.*,java.util.ArrayList,java.util.List"%>
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
<title>欢迎进入办公用品预定系统</title>
<style>
.tableshow {
	width: 75%;
	height: 480px;
	margin-left: 200px;
	border: solid red;
	overflow: auto;
}
</style>
<script type="text/javascript">
function delGoods(){
	if(window.confirm("您确定清空吗?")){
		window.location.href="currentRecord/delOwnGoods.action";
	}
}
</script>
</head>

<body style="background-image: url('images/login/bg_form.PNG'); font-family: SimSun">
	<a name="top"></a>
	<table align="center">
		<jsp:include page="jsp/common/header.jsp" flush="false"></jsp:include>
	</table>
	<div align="left">
		<table>
			<jsp:include page="jsp/common/left.jsp" flush="false"></jsp:include>
		</table>
	</div>
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
<table align="center">
	<tr>
		<th><font color="bule" size="5">办公用品列表</font></th>
	</tr>
</table>
<form id="apply_form"action="currentRecord/goodsApply.action" method="post">
			<div class="tableshow">
				<table border="1" width="100%" height="100%" align="center"
					cellpadding="0" cellspacing="0"
					style="border: 1px; border-color: black;">
					<tr align="center" bgcolor="#FFFFF">
						<th>编号</th>
						<th>品名及规格</th>
						<th>数量</th>
						<th>单位</th>
					</tr>
					<%
						int i = 0;
						ArrayList<Goods> goods = (ArrayList<Goods>) request.getAttribute("goods");
						List<CurrentRecord> uglist = (ArrayList<CurrentRecord>) request.getAttribute("uglist");
						if (goods != null) {
							for (Goods g : goods) {
					%>
				<tr align="center">
					<%String color ="";String info = "";%>
					<td><%=++i%></td>
					<td align="left"><%=g.getGoodsName() %><input type="hidden" name="goodsID<%=i %>" value="<%=g.getId() %>"/><input type="hidden" id="isChanged<%=i %>" name="isChanged<%=i %>" value="false"/></td>
					<td align="left"><select name="selectcount<%=i%>" id="<%=i%>" onchange="isChanged('isChanged<%=i %>', 'true')">
							<%
								int temp = 0;
										if (uglist != null) {
											for (CurrentRecord cr : uglist) {
												if (g.getGoodsName().equals(cr.getGoodsName())) {
													temp = cr.getCount();
												}
											}
											for (int j = 0; j < 11; j++) {
												if (j == temp) {
													color = (j == 0 ? "" : "pink");
													info = (j == 0 ? "" : "√");
							%>
							<option value="<%=j%>" selected="selected"style="background-color: <%=color %>;"><%=j%></option>
							<%
								} else {
							%>
							<option value="<%=j%>"><%=j%></option>
							<%
								}	}
							%>
					</select> <%}%>
					<span style="color: red;"><%=info %></span>
					</td>
					<td><%=g.getGoodsUnit()%></td>
				</tr>

				<%
					}
					}
				%>
				<tr><td colspan="4"><%-- <input type="hidden" name="goodsCount" value="<%=i %>"/> --%></td></tr>
			</table>
			</div>
	<table align="center" width="50%">
		<tr>
			<td align="center"><input type="submit" name="apply"value="提交申请" style="background-color: #82daf0">
			<input type="button" name="del"value="清空" style="background-color: #82daf0" onclick="delGoods()"></td>
		</tr>
	</table>
</form>
</body>
</html>