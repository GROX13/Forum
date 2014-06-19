<%@page import="forum.data.objects.Profile"%>
<%@page import="forum.data.accounts.Admin"%>
<%@page import="forum.data.accounts.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<% Profile p = (Profile)request.getSession().getAttribute("profile"); %>
	<% if(p!=null){ %>
		<ul>
			<li><%= p.UserName() %></li>
		</ul>
	<% } %>
</body>
</html>