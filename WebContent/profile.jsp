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
	<% int id = Integer.parseInt(request.getParameter("id")); %>
	<% Admin adm = (Admin)request.getSession().getAttribute("admin"); %>
	<% User usr = (User)request.getSession().getAttribute("user"); %>
	<% Profile p = null; %>
	<% if(adm != null) { %>
	<% p = adm.getProfile(); }%>
	<% if(usr != null){ %>
	<% p = usr.getProfile(); } %>
	<% if(p!=null){ %>
			<p>UserName: <%= p.UserName() %></p>
			<% String usType = ""; %>
			<% if(p.GetUserType() == 1) usType = "Admin"; if(p.GetUserType() == 0) usType= "User"; %>
			<p>UserType: <%= usType %></p>
			<p>FirstName: <%= p.GetFirstName() %></p>
			<p>LastName: <%= p.GetLastName() %></p>
			<% String gen; %>
			<% if(p.GetGender().equals("m")) gen = "male"; else gen = "female";%>
			<p>Gender: <%= gen %> </p>
			<p>BirthDate: <%= p.GetBirthDate() %></p>
			<p>Email: <%= p.GetEmail() %></p>
			<p>Avatar: <%= p.GetAvatar() %></p>
			<p>Signature: <%= p.GetSignature() %></p>
			<% if(p.GetUserID() == id) { %>
				<p>Password: <%= p.GetPasword() %></p>
				<form action = "Change" method = "post">
					<input type = 'submit' value = 'change'/>
				</form>
			<% }%>
	<% } %>
</body>
</html>