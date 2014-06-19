<%@page import="forum.data.objects.Profile"%>
<%@page import="forum.data.accounts.Admin"%>
<%@page import="forum.data.accounts.User"%>
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
	<% User usr = (User) request.getServletContext().getAttribute("user");%>
	<% Admin adm = (Admin) request.getServletContext().getAttribute("admin");%>
	<% if(usr == null) {
		if (adm == null) {
			out.print(
				"<form action = \"HandleLogin\" method = \"post\">" +	
				  "<p>" +
						"<label for = \"username\"> User name: </label>" +	
						"<input type = \"text\" id = \"username\" name = \"username\" required>" +	
						"<label for = \"password\"> Password: </label>" +		
						"<input type = \"password\" id = \"password\" name = \"password\" required>" +
						"<input type = \"submit\" value = \"Login\">" +	 
						"<a href =\"registration.jsp\"> Create New Account </a>" +	
					"</p>" +	
				"</form>");
		} else {
			Profile p = adm.getProfile();
			out.print("Welcome " + p.GetFirstName() + " " + p.GetLastName());
		}
	} else {
		Profile p = usr.getProfile();
		if (p != null) 
			out.print("Welcome: " + p.GetFirstName() + " " + p.GetLastName());
	}
	%>
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