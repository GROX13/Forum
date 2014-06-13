<%@page import="java.util.Map.Entry"%>
<%@page import="forum.data.objects.Category"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="forum.managers.objects.CategoryManager"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All Categories</title>
</head>
<body>
	<p>Categories</p>
	<ul>
		<% CategoryManager cm = (CategoryManager)request.getServletContext().getAttribute("categories"); %>
		<% Map<Integer, Category> all = cm.getAll(); %>
		<% Iterator<Map.Entry<Integer, Category>> iter = all.entrySet().iterator(); %>
		<% while(iter.hasNext()){ %>
		<% 		Map.Entry<Integer, Category> entry = iter.next(); %>
		<%		int id = entry.getKey(); %>
		<%		Category value = entry.getValue(); %>
		<li><a href = <%= "themes.jsp?" +  id %>><%= value.getTitle() %></a></li>
		<% } %>
	</ul>
</body>
</html>