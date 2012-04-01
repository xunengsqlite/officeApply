<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String contextPath = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ contextPath + "/";
%>
<head>
<base href="<%=basePath %>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>上传页面</title>
<script language="javascript">
function uploadBegin(){
	theFeats = "height=200,width=320,location=no,menubar=no,resizable=no,scrollbars=no,status=no,toolbar=no";
	strAppVersion = navigator.appVersion;
	if (document.uploadForm.file1.value != ""||document.uploadForm.file2.value != "")
	{
	   // if (strAppVersion.indexOf('MSIE') != -1 && strAppVersion.substr(strAppVersion.indexOf('MSIE')+5,1) > 4)
	   // {	
			winstyle = "dialogWidth=320px; dialogHeight:200px; center:yes";
			window.showModelessDialog("progressbar.jsp",window,winstyle);
	  //  }
	}
}
</script>
</head>

<body leftmargin="0" topmargin="0">
<tr><td colspan="2"><div>回到首页：<a href="/OfficeApply/jsp/goods/goodslist.jsp">请点这</a></div>
<div>下载文件链接：<a href="/OfficeApply/FileShowServlet">请点这</a>
<p>上传文件</p>
<form action=<%="\""+response.encodeURL("../../FileUploadServlet")+"\""%> enctype="multipart/form-data" method="post" name="uploadForm" id="uploadForm" onsubmit="uploadBegin();">
   	<input type="text" name ="name"/><br/>
    <input type="file" name="file1" maxlength="100"><br/>
    <input type="file" name="file2" maxlength="100"><br/>
    <input type="submit"  name="submit" value="开始上传">
</form>
</body>
</html>
