<%@page import="java.util.ArrayList"%>
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

		<!-- <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"> -->
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		
		<link rel="icon" href="Icons/Wineass_W.ico" type="icon">
		
    	<link rel="stylesheet" type="text/css" href="CSS/css/reset.css"> 
    	<link rel="stylesheet" type="text/css" href="CSS/css/style.css">
		<link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Open+Sans:400,600,300,800,700,400italic|PT+Serif:400,400italic">
	
<% int id = Integer.parseInt(request.getParameter("id")); %>
<% 
	User user = new User();
	String themeName = user.viewTheme(id).getTitle();
	%>
<title><%=themeName%></title>
</head>
<body class="bg-cyan">

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
			return "<a href=\"profile.jsp?id=" + id + "\">" + name + "</a>"; 
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
				
		<section id="content" data-easytabs="true">             
			<div id="profile" class="active" style="display: block;"> 
                <div class="about">
                   	<div class="photo-inner">
                   		<div class="caroufredsel_wrapper" style="display: block; text-align: start; float: none; position: relative; top: auto; right: auto; bottom: auto; left: auto; z-index: auto; width: 153px; height: 188px; margin: 0px; overflow: hidden;">
                    		<ul style="text-align: left; float: none; position: absolute; top: 0px; right: auto; bottom: auto; left: 0px; margin: 0px; width: 765px; height: 188px; z-index: auto; opacity: 1;">                          
                            	<%if(user.viewProfile(value.getUserId()).GetAvatar() == null){ %>
                            	<li><img src="Images/default.jpg" height="186" width="153"></li>
                            	<%}else{ %>
                            	<%String image = "Images/UploadedFiles/" + user.viewProfile(value.getUserId()).GetAvatar(); %>
                            	<li><img src= <%=image%> + " height="186" width="153"></li>
                            <%} %>
                            </ul>
                        </div>
                    </div> 
                          
                    <h1><% out.print(liDecorator(value.getUserId(), user.viewProfile(value.getUserId()).GetUsername())); %></h1>
                   <p><%out.print(user.viewProfile(value.getUserId()).GetSignature());%></p>
                </div>
                
                <ul class="personal-info">
                
					<li><% out.print(value.getText()); %></li>
				 <%--	<%ArrayList<String> files = value.getFiles(); 
						int size = files.size();
						String file = "";
						for(int i = 0; i < size; i++){
							file = files.get(i);
					%>
					<img src="Images/UploadedFiles/" + <%= file %> + "height="186" width="153">
					<%} %> --%>
                </ul>
			</div>        
		</section>
				
		<%		if(adm != null){ %>
		<%			out.print(editButtons(pId)); %>
		<%   		out.print(removePost(pId)); %>
		<%			out.print(showButtons(pId)); %>
		<%		}%>
		<% } %> 
</body>
</html>