<<<<<<< HEAD
<%@page import="forum.data.accounts.Admin"%>
<%@page import="forum.data.accounts.User"%>
=======
<%@page import="forum.data.objects.Profile"%>
<%@page import="forum.data.accounts.User"%>
<%@page import="forum.data.accounts.Admin"%>
>>>>>>> origin/master
<%@ page language="java" contentType="text/html; charset = UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<% User usr = (User) request.getSession().getAttribute("user");%>
	<% Admin adm = (Admin) request.getSession().getAttribute("admin");%>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Categories</title>
		
		<link rel="stylesheet" href="CSS/css/demo.css">
		<link rel="stylesheet" href="CSS/css/sky-forms.css">
		
		<link rel = "icon" href = "Icons/Wineass_W.ico" type = "icon">
	</head>
	<body class="bg-red">
		<link rel="stylesheet" href="CSS/css/stylemenu.css">
  		<nav class="menu-opener">
    		<div class="menu-opener-inner"></div>
  		</nav>
  	<% User usr = (User) request.getSession().getAttribute("user");%>
	<% Admin adm = (Admin) request.getSession().getAttribute("admin");
	String profileLink = "profile.jsp";
	boolean guest = true;
	Profile p = null;%>
	<% if(adm != null) {
			profileLink += "?id=" + adm.getProfile().GetUserID();
			guest = false;
		}else if(usr != null){
			profileLink += "?id=" + usr.getProfile().GetUserID();
			guest = false;
		}%>
  		<nav class="menu">
    		<ul class="menu-inner">
    		<%if(!guest){ %>
    			<a href=<%= profileLink%> class="menu-link">
        			<li>Profile</li>
      			</a>
<<<<<<< HEAD
      			<a href="#" class="menu-link">
       				<li>Add category</li>
      			</a>
      			<a href="#" class="menu-link">
        			<li>Delete category</li>
      			</a>
      			<a href="#" class="menu-link">
=======
      			<%} %>
      			<a href="categorybc.jsp" class="menu-link">
       				<li>Categories</li>
      			</a>
      			<a href="#" class="menu-link">
>>>>>>> origin/master
        			<li>About us</li>
      			</a>
      			<%if(!guest){ %>
      			<a href="logout.jsp" class="menu-link">
        			<li>Log out</li>
      			</a>
      			<%}else{ %>
      			<a href="index.jsp" class="menu-link">
        			<li>Log In</li>
      			</a>
      			<%} %>
    		</ul>
  		</nav>
  		
  		<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.js"></script>
  		<script src="JavaScript/menu-opener.js"></script>	
	</body>
</html>