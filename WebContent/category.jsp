<%@page import="forum.data.objects.Profile"%>
<%@page import="forum.data.accounts.User"%>
<%@page import="forum.data.accounts.Admin"%>
<%@ page language="java" contentType="text/html; charset = UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<% User user = (User) request.getSession().getAttribute("user");%>
	<% Admin admin = (Admin) request.getSession().getAttribute("admin");%>
	<% boolean isUser = (user != null);%>
	<% boolean isAdmin = (admin != null);%>
	<% boolean isGuest = (!isUser && !isAdmin);%>
	<% String myProfileLink = "profile.jsp"; %> 
	<% if (!isGuest){ %>
	<% 		if (isAdmin){ %>
	<% 			myProfileLink += "?id=" + admin.getProfile().GetUserID();%>
	<% 		}%>
	<%		if (isUser){ %>
	<% 			myProfileLink += "?id=" + user.getProfile().GetUserID();%>
	<% 		}%>
	<% }%>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Categories</title>
		
		<link rel="stylesheet" href="CSS/css/demo.css">
		<link rel="stylesheet" href="CSS/css/sky-forms.css">
		<link rel="stylesheet" href="CSS/css/stylemenu.css">
		
		<link rel = "icon" href = "Icons/Wineass_W.ico" type = "icon">
	</head>
	<body class="bg-red">
  		<nav class="menu-opener">
    		<div class="menu-opener-inner"></div>
  		</nav>
  		<nav class="menu">			 		
  			<ul class="menu-inner">
  				<% if (isGuest){ %>
  				<a href="index.jsp" class="menu-link">
        			<li>Log In</li>
      			</a>
      			<a href="#" class="menu-link">
        			<li>About us</li>
      			</a>
  				<% %> 
  				<% }%> 
  				<% if (isAdmin){ %>
  				<a href=<% out.print("\"" + myProfileLink + "\""); %> class="menu-link">
        			<li>Profile</li>
      			</a>
      			<a href="categoryform.jsp" class="menu-link">
       				<li>Add category</li>
      			</a>
      			<a href="logout.jsp" class="menu-link">
        			<li>Log out</li>
      			</a>
      			<a href="#" class="menu-link">
        			<li>About us</li>
      			</a>
  				<% %> 
  				<% }%> 
  				<% if (isUser){ %>
  				<a href=<% out.print("\"" + myProfileLink + "\""); %> class="menu-link">
        			<li>Profile</li>
      			</a> 
      			<a href="logout.jsp" class="menu-link">
        			<li>Log out</li>
      			</a>
      			<a href="#" class="menu-link">
        			<li>About us</li>
      			</a>
  				<% %> 
  				<% }%> 
    		</ul>
  		</nav>
  		<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.js"></script>
  		<script src="JavaScript/menu-opener.js"></script>	
	</body>
</html>