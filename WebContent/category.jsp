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
				<p><a href = <%= "chat.jsp?id=" + p.GetUserID() %>> Chat </a></p>
				<p><a href = <%= "profile.jsp?id=" + p.GetUserID() %>> Profile </a></p>
				<p><a href ="log_out.jsp">Log Out</a></p>
			<% 
			out.print(
					"<form action = \"HandleCategory\" method = \"post\">" +	
					  "<p>" +
							"<label for = \"category\"> Category: </label>" +	
							"<input type = \"text\" id = \"category\" name = \"category\" required>" +	
							"<label for = \"category_description\"> Category Description: </label>" +		
							"<input type = \"text\" id = \"category_description\" name = \"category_description\" required>" +
							"<input type = \"submit\" value = \"Create\">" +	
						"</p>" +	
					"</form>");
		}
	} else {
		Profile p = usr.getProfile();
		if (p != null) 
			out.print("<h1> Welcome " + p.GetFirstName() + " " + p.GetLastName() + "</h1>");
		out.print("<h3> Status: User </h3>");
		%>
			<p><a href = <%= "chat.jsp?id=" + p.GetUserID() %>> Chat </a></p>
			<p><a href = <%= "profile.jsp?id=" + p.GetUserID() %>> Profile </a></p>
			<p><a href ="log_out.jsp">Log Out</a></p>
		<% 
	}
	%>
	<script>
	function myFunction(arg1, arg2, arg3, arg4, arg5) {
		 document.getElementById(arg1).style.display = "block";
		 document.getElementById(arg2).style.display = "block";
		 document.getElementById(arg3).style.display = "block";
		 document.getElementById(arg4).style.display = "block";
		 document.getElementById(arg5).style.display = "block";
	}
	</script>
	<p>Categories</p>
	<%!
        public String liDecorator(int id, String name){
            return "<li><a href=\"themes.jsp?id=" + id + "\">" + name + "</a></li>";
        }
    %>
    
    <%!
        public String editButtons(int i){
    		String changeName = "change" + i;
    		String editName = "edit" + i;
    		String passName = "pass" + i;
            return "<form action = \"HandleCategoryChanges?id=" + i + "\"" + "method = \"post\">" + 
            "<button onclick=\"myFunction('"+passName+"', '" + changeName +"')\" id = " + editName +" style = \"display:none\" type = \"button\">Edit</button>" +
            "<p><input type = \"text\" id = "+ passName +" style = \"display:none\" name = "+ passName +"/></p>" +
            "<button  id =" +changeName+" style = \"display:none\" type = \"submit\">Save Changes</button>" +
            "</form>";
            
        }
    %>
    
     <%!
        public String showButtons(int i){
    	 	String editName = "edit" + i;
            return "<script>" + 
		 	"myFunction('" + editName +"')" +
			"</script>";
            
        }
    %>
    
	<ul>
	    <% CategoryManager cm = (CategoryManager)request.getServletContext().getAttribute("categories"); %>
		<% Map<Integer, Category> all = cm.getAll(); %>
		<% Iterator<Map.Entry<Integer, Category>> iter = all.entrySet().iterator(); %>
		<% while(iter.hasNext()){ %>
		<% 		Map.Entry<Integer, Category> entry = iter.next(); %>
		<%		int id = entry.getKey(); %>
		<%		Category value = entry.getValue(); %>
		<%		out.print(liDecorator(id, value.getTitle()));; %>
		<%		if(adm != null){ %>
		<%			out.print(editButtons(id)); %>
		<%			out.print(showButtons(id)); %>
		<%		}%> 
		<% } %> 
	</ul>
</body>
</html>