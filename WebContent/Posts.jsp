<%@page import="forum.data.objects.Post"%>
<%@page import="forum.managers.objects.PostManager"%>
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
<% int id = Integer.parseInt(request.getParameter("id")); %>
<% 
	User user = new User();
	String themeName = user.viewTheme(id).getTitle();
	%>
<title><%=themeName%></title>
</head>
<body>

	<% User usr = (User) request.getSession().getAttribute("user");%>
	<% Admin adm = (Admin) request.getSession().getAttribute("admin");%>
	<% if(usr == null) {
		if (adm == null) {
			%>
			<p><a href ="index.jsp">Log In</a></p>
			<% 
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
			%>
				<p><a href = <%= "profile.jsp?id=" + p.GetUserID() %>> Profile </a></p>
				<p><a href ="log_out.jsp">Log Out</a></p>
			<%
			
			out.print(
					"<form action = \"HandlePosts\" method = \"post\">" +	
					  "<p>" +
							"<label for = \"post\"> Post: </label>" +	
							"<input type = \"textarea\" name = \"post\" cols = \"70\" rows = \"10\" required>" +	
							"<label for = \"images\"> Images: </label>" +
							"<input type = \"file\" name = \"imageFiles\" >" +
							"<label for = \"videos\"> Videos: </label>" +
							"<input type = \"file\" name = \"videoFiles\" >" +
							"<input type = \"submit\" value = \"ADD\">" +	
						"</p>" +	
					"</form>");
		}
	} else {
		Profile p = usr.getProfile();
		if (p != null) 
			out.print("<h1> Welcome " + p.GetFirstName() + " " + p.GetLastName() + "</h1>");
		out.print("<h3> Status: User </h3>");
		%>
			<p><a href = <%= "profile.jsp?id=" + p.GetUserID() %>> Profile </a></p>
			<p><a href ="log_out.jsp">Log Out</a></p>
		<% 
		
		out.print(
				"<form action = \"HandlePosts\" method = \"post\">" +	
				  "<p>" +
						"<label for = \"post\"> Post: </label>" +	
						"<input type = \"textarea\" name = \"post\" cols = \"70\" rows = \"10\" required>" +	
						"<label for = \"images\"> Images: </label>" +
						"<input type = \"file\" name = \"imageFiles\" >" +
						"<label for = \"videos\"> Videos: </label>" +
						"<input type = \"file\" name = \"videoFiles\" >" +
						"<input type = \"submit\" value = \"ADD\">" +	
					"</p>" +	
				"</form>");
		
	} %>
	<p><%=themeName + " Posts: "%></p>
	<%!
        public String liDecorator(int id, String name){
            return "<li><a href=\"profile.jsp?id=" + id + "\">" + name + "</a></li>";
        }
    %>
    
	<% PostManager pm = (PostManager)request.getServletContext().getAttribute("post"); %>
	
	<% Map<Integer, Post> all = pm.getAll(id); %>
	<% Iterator<Map.Entry<Integer, Post>> iter = all.entrySet().iterator(); %>
		<% while(iter.hasNext()){ %>
		<% 		Map.Entry<Integer, Post> entry = iter.next(); %>
		<%		int pId = entry.getKey(); %>
		<%		Post value = entry.getValue(); %>
				
		<% 		
				out.println(liDecorator(value.getUserId(), user.viewProfile(value.getUserId()).GetUsername() + " "));
				out.println("[ " + value.getDate() + " ]: " + value.getText()); 
		%>
		<% } %> 
		<% if(usr != null && adm != null){
			 
		}%>
		
		<%-- <textarea name="paragraph_text" cols="100" rows="10"></textarea>
		<input type="submit" value="ADD"/> --%>
</body>
</html>