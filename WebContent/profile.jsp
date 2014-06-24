<%@page import="forum.data.accounts.Admin"%>
<%@page import="java.sql.Date"%>
<%@page import="forum.data.objects.Profile"%>
<%@page import="forum.data.accounts.User"%>
<%@ page language="java" contentType="text/html; charset = UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<% int id = Integer.parseInt(request.getParameter("id")); %> 
	<% Admin adm = (Admin)request.getSession().getAttribute("admin"); %>
	<% User usr = (User)request.getSession().getAttribute("user"); %>
	<% Profile profile = null; %>
	<% if(adm != null){  %>
		<% profile = adm.getProfile();} %>
	<% if(usr != null){ %>
		<% profile = usr.getProfile(); }%>
	<% if((profile != null && profile.GetUserID() != id) || profile == null){ %>
		<% User user = new User(); %>
		<% profile = user.viewProfile(id); %>
	<% } %>
	<% 
		String username = profile.GetUsername();
		String userType;
		if(profile.GetUserType() == 1)
			userType = "Admin";
		else
			userType = "User";
		String firstname = profile.GetFirstName();
		String lastname = profile.GetLastName();
		String email =  profile.GetEmail();
		String gender;
		if(profile.GetGender().equals("m"))
			gender = "male";
		else
			gender = "female";
		String signiture = profile.GetSignature();
		String picture = profile.GetAvatar();
		Date birth = profile.GetBirthDate();
		String password = profile.GetPasword();
	%>
		 
	<head>
		<!-- <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"> -->
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		
		<title><%out.print(firstname + " " + lastname);%></title>
		<link rel="icon" href="Icons/Wineass_W.ico" type="icon">
		
    	<link rel="stylesheet" type="text/css" href="CSS/css/reset.css"> 
    	<link rel="stylesheet" type="text/css" href="CSS/css/style.css">
		<link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Open+Sans:400,600,300,800,700,400italic|PT+Serif:400,400italic">
		
		<script type="text/javascript">
			function populatedropdown(dayfield, monthfield, yearfield){
				var today=new Date();
				var dayfield=document.getElementById(dayfield);
				var monthfield=document.getElementById(monthfield);
				var yearfield=document.getElementById(yearfield);
				for (var i=1; i<=31; i++)
					dayfield.options[i]=new Option(i, i);
				for (var m=1; m<=12; m++)
					monthfield.options[m]=new Option(m, m);
				var thisyear=today.getFullYear();
				for (var y=1; y<=100; y++){
					yearfield.options[y]=new Option(thisyear, thisyear--);
				}
			}
			
			function myFunction(arg1, arg2, arg3, arg4, arg5) {
				 document.getElementById(arg1).style.display = "block";
				 document.getElementById(arg2).style.display = "block";
				 document.getElementById(arg3).style.display = "block";
				 document.getElementById(arg4).style.display = "block";
				 document.getElementById(arg5).style.display = "block";
			}
		</script>
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
                        <button  id = "editPic" style = "display:none" type = "button" class = "button">Edit</button>
                    </div>        
                    <h1><% out.print(username); %></h1>
                    <h1><% out.print(userType); %></h1>
                    <h3><% out.print(firstname + " " + lastname); %></h3>
                    <p><% out.print(signiture); %></p>
                    <button  id = "editsign" style = "display:none" type = "button" class = "button">Edit</button>
                </div>
                
                <ul class="personal-info">
					<li><label>First Name</label><span><% out.print(firstname); %></span></li>
					<li><label>Last Name</label><span><% out.print(lastname); %></span></li>
					<li id = "YourPass" style = "display:none">
						<label>Password</label><span><% out.print(password); %></span>
					</li>
                    <li><label>Birthday</label><span><% out.print(birth); %></span></li>
                    <li><label>Gender</label><span><% out.print(gender); %></span></li>
                    <li><label>Email</label><span class="word-wrap"><% out.print(email); %></span></li>
                </ul>
                 <ul class="personal-info">
                 
                 	<li><button  id = "name" style = "display:none" type = "button" class = "button">Edit</button></li>
                 	<li><button  id = "lastName" style = "display:none" type = "button" class = "button">Edit</button></li>
                 	<li><button  id = "editPass" style = "display:none" type = "button" class = "button">Edit</button></li>
                 	<li><button  id = "editDate" style = "display:none" type = "button" class = "button">Edit</button></li>
                 	<li><button  id = "editGender" style = "display:none" type = "button" class = "button">Edit</button></li>
					<li><button  id = "editmail" style = "display:none" type = "button" class = "button">Edit</button></li>
				
                </ul>
                
			</div>        
		</section>
		<% if(profile.GetUserID() == id) { %>
		 <script> 
		 	myFunction("YourPass", "editPass", "name", "lastName", "editDate");
		 	myFunction("editGender", "editmail", "editsign", "editPic");
		 </script>
		 <% } %>
	</body>
</html>