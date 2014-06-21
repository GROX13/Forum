<%@page import="servlet.themes.HandleThemes"%>
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
<% int id = Integer.parseInt(request.getParameter("id")); 
	User user = new User();
	String categoryName = user.viewcaCategory(id).getTitle();
	%>
<title><%= categoryName %></title>
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
					"<form action = \"HandleThemes?id=" + id + "\""+ "method = \"post\">" +	
					  "<p>" +
							"<label for = \"theme\"> Theme: </label>" +	
							"<input type = \"text\" id = \"theme\" name = \"theme\" required>" +	
							"<label for = \"theme_description\"> Theme Description: </label>" +		
							"<input type = \"text\" id = \"theme_description\" name = \"theme_description\" required>" +
							"<label for = \"theme_isOpen\"> Theme Is Open for Guest: </label>" +		
							"<select id = \"theme_isOpen\" name = \"theme_isOpen\">" +
		  					"<option value=\"Open\"> Open </option>" +
							"<option value=\"notOpen\"> Closed </option>" +
							"</select>" +
							"<input type = \"submit\" value = \"Add Theme\">" +	
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
				"<form action = \"HandleThemes?id=" + id + "\""+ "method = \"post\">" +	
				  "<p>" +
						"<label for = \"theme\"> Theme: </label>" +	
						"<input type = \"text\" id = \"theme\" name = \"theme\" required>" +	
						"<label for = \"theme_description\"> Theme Description: </label>" +		
						"<input type = \"text\" id = \"theme_description\" name = \"theme_description\" required>" +
						"<label for = \"theme_isOpen\"> Theme Is Open for Guest: </label>" +		
						"<select id = \"theme_isOpen\" name = \"theme_isOpen\">" +
	  						"<option value=\"Open\"> Open </option>" +
							"<option value=\"notOpen\"> Closed </option>" +
						"</select>" +
						"<input type = \"submit\" value = \"Add Theme\">" +	
					"</p>" +	
				"</form>");
		
	} %>
	<p><%= categoryName + " Themes: " %></p>
	<script>
	function myFunction(arg1, arg2, arg3, arg4, arg5) {
		 document.getElementById(arg1).style.display = "block";
		 document.getElementById(arg2).style.display = "block";
		 document.getElementById(arg3).style.display = "block";
		 document.getElementById(arg4).style.display = "block";
		 document.getElementById(arg5).style.display = "block";
	}
	</script>
	<%!
        public String liDecorator(int id, String name){
            return "<li><a href=\"Posts.jsp?id=" + id + "\">" + name + "</a></li>";
        }
    %>
    <%!
        public String editButtons(int i){
    		String changeName = "change" + i;
    		String editName = "edit" + i;
    		String passName = "pass" + i;
            return "<form action = \"HandleThemes\" method = \"post\">" + 
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
    
	<% ThemeManager tm = (ThemeManager)request.getServletContext().getAttribute("themes"); %>
	
	<% Map<Integer, Theme> all = tm.getAll(id); %>
	<% Iterator<Map.Entry<Integer, Theme>> iter = all.entrySet().iterator(); %>
	<% int i = 0; %>
		<% while(iter.hasNext()){ %>
		<% 		i++; %>
		<% 		Map.Entry<Integer, Theme> entry = iter.next(); %>
		<%		int tId = entry.getKey(); %>
		<%		Theme value = entry.getValue(); %>
		<% 		out.print(liDecorator(tId, value.getTitle())); %>
		<%		if(adm != null){ %>
		<%			out.print(editButtons(i)); %>
		<%			out.print(showButtons(i)); %>
		<%		}%>
		<% } %> 
</body>
</html>