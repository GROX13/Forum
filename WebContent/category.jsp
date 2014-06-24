<%@page import="forum.data.accounts.Admin"%>
<%@page import="forum.data.accounts.User"%>
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
  		<nav class="menu">
    		<ul class="menu-inner">
    			<a href="#" class="menu-link">
        			<li>Profile</li>
      			</a>
      			<a href="#" class="menu-link">
       				<li>Add category</li>
      			</a>
      			<a href="#" class="menu-link">
        			<li>Delete category</li>
      			</a>
      			<a href="#" class="menu-link">
        			<li>About us</li>
      			</a>
      			<a href="logout.jsp" class="menu-link">
        			<li>Log out</li>
      			</a>
    		</ul>
  		</nav>
  		
  		<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.js"></script>
  		<script src="JavaScript/menu-opener.js"></script>	
	</body>
</html>