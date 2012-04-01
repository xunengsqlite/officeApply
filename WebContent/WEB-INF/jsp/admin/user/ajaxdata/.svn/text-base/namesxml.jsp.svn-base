<%@ page contentType="text/xml;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<names>
<%
List<String> names = (ArrayList<String>)request.getAttribute("names");
if(names != null){
	for(String name : names){
%>
    <name><%=name %></name>

<%}}%>
</names>