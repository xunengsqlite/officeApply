<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.fnst.officeapply.common.*" %>
<%@ page import="com.fnst.officeapply.framework.*" %>
<%@ page import="com.fnst.officeapply.framework.RedirectBean.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String contextPath = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ contextPath + "/";

	RedirectBean bean = (RedirectBean) request.getAttribute("redirectBean");
	String method = "POST";
	String requestName = "homePage.action";
	if (bean != null) {
		method = ComUtil.changeNullVal(bean.getRequestWay(),method);
		requestName = ComUtil.changeNullVal(bean.getRequest(),requestName);
	}
%>

<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>RedirectPage</title>
</head>
<body onload="document.getElementById('autoform').submit();">
		<form id="autoform" action="<%=requestName %>" method="<%=method%>">
			跳转中....
		<%
			if (bean != null) {
				Iterator<SimpleEntry> it = bean.iterator();
				while (it.hasNext()) {
					SimpleEntry en = it.next();
		%>
		<input type="hidden" name="<%=en.getName()%>" value="<%=en.getValue()%>" />
		<%
			}
			}
		%>
	</form>
	</body>
</html>