<%@page import="java.sql.Date"%>
<%@page import="forum.data.objects.Warn"%>
<%@page import="forum.data.objects.Bann"%>
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
			
			out.print("<form method=\"POST\" action=\"upload?id=" + id + "\"" + "enctype=\"multipart/form-data\" >" +
					"<p>" +
					"<label for = \"post\"> Post: </label>" +	
					"<input type = \"textarea\" name = \"post\" cols = \"70\" rows = \"10\" required>" +	
						
					"File:" +
           			    " <input type=\"file\" name=\"file\" id=\"file\" /> <br/>" +
            		    "<input type=\"submit\" value=\"Add Post\" name=\"upload\" id=\"upload\" />" +
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

		Bann bann = new Bann(p.GetUserID());
		Warn warn = new Warn(p.GetUserID());
		if(!bann.isBanned() && warn.canPost(new Date(System.currentTimeMillis()))){
		
				out.print("<form method=\"POST\" action=\"upload?id=" + id + "\"" + "enctype=\"multipart/form-data\" >" +
						"<p>" +
						"<label for = \"post\"> Post: </label>" +	
						"<input type = \"textarea\" name = \"post\" cols = \"70\" rows = \"10\" required>" +	
							
						"File:" +
	           			 " <input type=\"file\" name=\"file\" id=\"file\" /> <br/>" +
	            		 "<input type=\"submit\" value=\"Add Post\" name=\"upload\" id=\"upload\" />" +
	            		 "</p>" +
	            		"</form>");
		
			}
		}%>
	<p><%=themeName + " Posts: "%></p>
	
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
            return "<li><a href=\"profile.jsp?id=" + id + "\">" + name + "</a></li>";
        }
    %>
    
    <%!
        public String editButtons(int i){
    		String changeName = "change" + i;
    		String editName = "edit" + i;
    		String passName = "pass" + i;
            return "<form action = \"HandlePostChanges?id=" + i + "\"" + "method = \"post\">" + 
            "<button onclick=\"myFunction('"+passName+"', '" + changeName +"')\" id = " + editName +" style = \"display:none\" type = \"button\">Edit</button>" +
            "<p><input type = \"text\" id = "+ passName +" style = \"display:none\" name = "+ passName +" /></p>" +
            "<button  id =" +changeName+" style = \"display:none\" type = \"submit\">Save Changes</button>" +
            "</form>";
            
        }
    %>
    
    <%!
        public String removePost(int i){
            return "<a href =\"HandlePostRemove?id=" + i + "\">" + "Remove Post </a>";
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
    
    
	<% PostManager pm = (PostManager)request.getServletContext().getAttribute("post"); %>
	<% Map<Integer, Post> all = pm.getAll(id); %>
	<% Iterator<Map.Entry<Integer, Post>> iter = all.entrySet().iterator(); %>
		<% while(iter.hasNext()){ %>
		<% 		Map.Entry<Integer, Post> entry = iter.next(); %>
		<%		int pId = entry.getKey(); %>
		<%		Post value = entry.getValue(); %> 		
		<% 		out.println(liDecorator(value.getUserId(), user.viewProfile(value.getUserId()).GetUsername() + " "));%>	
		<%		out.println("[ " + value.getDate() + " ]: " + value.getText()); %>
		<%		if(adm != null){ %>
		<%			out.print(editButtons(pId)); %>
		<%   		out.print(removePost(pId)); %>
		<%			out.print(showButtons(pId)); %>
		<%		}%>
		<% } %> 
</body>
</html>