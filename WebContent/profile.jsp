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
	String firstname = profile.GetFirstName();
	String lastname = profile.GetLastName();
	%>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title><%out.print(firstname + " " + lastname);%></title>
		<link rel="icon" href="Icons/Wineass_W.ico" type="icon">
    	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    	<link rel="stylesheet" type="text/css" href="CSS/css/reset.css"> 
    	<link rel="stylesheet" type="text/css" href="CSS/css/style.css">
		<link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Open+Sans:400,600,300,800,700,400italic|PT+Serif:400,400italic">
	</head>
	<body>

	<section id="content" data-easytabs="true">
            
            <header> 
            	<!-- Logo -->
            	<div id="logo">
                	<h2>ANDERSON SMITH</h2>
                    <h4>GRAPHIC &amp; WEB DESIGNER</h4>
                </div>
                <!-- /Logo -->
                
            </header>
            
              
                <div id="profile" class="active" style="display: block;"> 
                 	<!-- About section -->
                	<div class="about">
                    	<div class="photo-inner">
                            <div class="caroufredsel_wrapper" style="display: block; text-align: start; float: none; position: relative; top: auto; right: auto; bottom: auto; left: auto; z-index: auto; width: 153px; height: 188px; margin: 0px; overflow: hidden;"><ul style="text-align: left; float: none; position: absolute; top: 0px; right: auto; bottom: auto; left: 0px; margin: 0px; width: 765px; height: 188px; z-index: auto; opacity: 1;">
                                
                                
                            <li><img src="Images/default.jpg" height="186" width="153"></li>
                            <li><img src="Images/default.jpg" height="186" width="153"></li></ul></div>
                        </div>
                        <h1>ANDERSON SMITH</h1>
                        <h3>GRAPHIC &amp; WEB DESIGNER</h3>
                        <p>I like to make cool and creative designs. My design stash is always full of refreshing ideas. Feel free to take a look around my Vcard.</p>
                    </div>
                    <!-- /About section -->
                     
                    <!-- Personal info section -->
                	<ul class="personal-info">
						<li><label>Name</label><span>Anderson smith</span></li>
                        <li><label>Birthday</label><span>March 13, 1988</span></li>
                        <li><label>Address</label><span>Melbourne Victoria 3000 Australia</span></li>
                        <li><label>Email</label><span class="word-wrap">Anderson.smith@gmail.com</span></li>
                        <li><label>Phone</label><span>+123 456 789 111</span></li>
                        <li><label>Website</label><span class="word-wrap"><a href="#">www.Andersonsmith.com</a></span></li>
                    </ul>
                   
                </div>        
               
               <footer>
            	<div class="copyright">Copyright © 2013 John smith</div>
            </footer>

            </section>
	
	</body>
</html>