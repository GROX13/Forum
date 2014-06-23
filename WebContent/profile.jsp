<%@page import="java.sql.Date"%>
<%@page import="forum.data.objects.Profile"%>
<%@page import="forum.data.accounts.User"%>
<%@ page language="java" contentType="text/html; charset = UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<% 
	int id = Integer.parseInt(request.getParameter("id")); 
	User user = new User();
	Profile profile = user.viewProfile(id);
	String username = profile.GetUsername();
	String firstname = profile.GetFirstName();
	String lastname = profile.GetLastName();
	String email =  profile.GetEmail();
	String gender = profile.GetGender();
	String signiture = profile.GetSignature();
	String picture = profile.GetAvatar();
	Date birth = profile.GetBirthDate();	
	%>
	<head>
		<!-- <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"> -->
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		
		<title><%out.print(firstname + " " + lastname);%></title>
		<link rel="icon" href="Icons/Wineass_W.ico" type="icon">
		
    	<link rel="stylesheet" type="text/css" href="CSS/css/reset.css"> 
    	<link rel="stylesheet" type="text/css" href="CSS/css/style.css">
		<link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Open+Sans:400,600,300,800,700,400italic|PT+Serif:400,400italic">
	</head>
	<body class="bg-cyan">
		<section id="content" data-easytabs="true">             
			<div id="profile" class="active" style="display: block;"> 
                <div class="about">
                   	<div class="photo-inner">
                   		<div class="caroufredsel_wrapper" style="display: block; text-align: start; float: none; position: relative; top: auto; right: auto; bottom: auto; left: auto; z-index: auto; width: 153px; height: 188px; margin: 0px; overflow: hidden;">
                    		<ul style="text-align: left; float: none; position: absolute; top: 0px; right: auto; bottom: auto; left: 0px; margin: 0px; width: 765px; height: 188px; z-index: auto; opacity: 1;">                          
                            	<li><img src="Images/default.jpg" height="186" width="153"></li>
                            	<li><img src="Images/default.jpg" height="186" width="153"></li>
                            </ul>
                        </div>
                    </div>        
                    <h1><% out.print(username); %></h1>
                    <h3><% out.print(firstname + " " + lastname); %></h3>
                    <p><% out.print(signiture); %></p>
                </div>
                
                <ul class="personal-info">
					<li><label>First Name</label><span><% out.print(firstname); %></span></li>
					<li><label>Last Name</label><span><% out.print(lastname); %></span></li>
                    <li><label>Birthday</label><span><% out.print(birth); %></span></li>
                    <li><label>Gender</label><span><% out.print(gender); %></span></li>
                    <li><label>Email</label><span class="word-wrap"><% out.print(email); %></span></li>
                </ul>
			</div>        
		</section>
	</body>
</html>