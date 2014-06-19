<%@page import="forum.data.accounts.Admin"%>
<%@page import="forum.data.accounts.User"%>
<%@page import="forum.data.objects.Profile"%>
<%@page import="java.util.Iterator"%>
<%@page import="forum.data.objects.Theme"%>
<%@page import="java.util.Map"%>
<%@page import="forum.managers.objects.ThemeManager"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Themes</title>
</head>
<body>
	<% User usr = (User) request.getSession().getAttribute("user");%>
	<% Admin adm = (Admin) request.getSession().getAttribute("admin");%>
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
			if (p != null) 
				out.print("<h1> Welcome " + p.GetFirstName() + " " + p.GetLastName() + "</h1>");
			out.print("<h3> Status: Admin </h3>");
			out.print(
					"<form action = \"HandleThemes\" method = \"post\">" +	
					  "<p>" +
							"<label for = \"theme\"> Theme: </label>" +	
							"<input type = \"text\" id = \"theme\" name = \"theme\" required>" +	
							"<label for = \"theme_description\"> Theme Description: </label>" +		
							"<input type = \"text\" id = \"theme_description\" name = \"theme_description\" required>" +
							"<input type = \"submit\" value = \"Add Theme\">" +	
						"</p>" +	
					"</form>");
		}
	} else {
		Profile p = usr.getProfile();
		if (p != null) 
			out.print("<h1> Welcome " + p.GetFirstName() + " " + p.GetLastName() + "</h1>");
		out.print("<h3> Status: User </h3>");
		out.print(
				"<form action = \"HandleThemes\" method = \"post\">" +	
				  "<p>" +
						"<label for = \"theme\"> Theme: </label>" +	
						"<input type = \"text\" id = \"theme\" name = \"theme\" required>" +	
						"<label for = \"theme_description\"> Theme Description: </label>" +		
						"<input type = \"text\" id = \"theme_description\" name = \"theme_description\" required>" +
						"<input type = \"submit\" value = \"Add Theme\">" +	
					"</p>" +	
				"</form>");
	} %>
	<% ThemeManager tm = (ThemeManager)request.getServletContext().getAttribute("themes"); %>
	<% int id = Integer.parseInt(request.getQueryString()); %>
	<% Map<Integer, Theme> all = tm.getAll(id); %>
	<% Iterator<Map.Entry<Integer, Theme>> iter = all.entrySet().iterator(); %>
		<% while(iter.hasNext()){ %>
		<% 		Map.Entry<Integer, Theme> entry = iter.next(); %>
		<%		int tId = entry.getKey(); %>
		<%		Theme value = entry.getValue(); %>
		<% out.print("<li><a href = <%= \"posts.jsp?theme_id=" +  tId + "\"><" + value.getTitle() + "</a></li>"); %>
		<% } %> 
</body>
</html>