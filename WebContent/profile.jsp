<%@page import="forum.data.objects.Bann"%>
<%@page import="forum.data.objects.Warn"%>
<%@page import="forum.data.accounts.Admin"%>
<%@page import="java.sql.Date"%>
<%@page import="forum.data.objects.Profile"%>
<%@page import="forum.data.accounts.User"%>
<%@ page language="java" contentType="text/html; charset = UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<% String idProfile = request.getParameter("id"); %>
	<% int id = 0;  %>
	<% Admin adm = (Admin)request.getSession().getAttribute("admin"); %>
	<% User usr = (User)request.getSession().getAttribute("user"); %>
	<% Profile profile = null; %>
	<% if(adm != null){  %>
		<% profile = adm.getProfile();} %>
	<% if(usr != null){ %>
		<% profile = usr.getProfile(); }%>
	<% if(idProfile == null && (usr != null || adm != null)){ 
		 id = profile.GetUserID(); } %>
	<% if(idProfile != null){ %>
		<% id = Integer.parseInt(idProfile);} %> 
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
			     
				<div id="profile"  style="display: block;">
				 <form action = ChangeData method = "post">       
	                <div class="about">
	                   	<div class="photo-inner">
	                   		<div class="caroufredsel_wrapper" style="display: block; text-align: start; float: none; position: relative; top: auto; right: auto; bottom: auto; left: auto; z-index: auto; width: 153px; height: 188px; margin: 0px; overflow: hidden;">
	                    		<ul style="text-align: left; float: none; position: absolute; top: 0px; right: auto; bottom: auto; left: 0px; margin: 0px; width: 765px; height: 188px; z-index: auto; opacity: 1;">                          
	                            	<li><img src="Images/default.jpg" height="186" width="153"></li>
	                            	<li><img src="Images/default.jpg" height="186" width="153"></li>
	                            </ul>
	                        </div>
	                        <button onclick="myFunction('avatar', 'changeImg')" id = "editPic" style = "display:none" type = "button" class = "button">Edit</button>
	                    	<p><input type = "file" id = "avatar" style = "display:none" name = "avatar"/></p>
							<button  id = "changeImg" style = "display:none" type = "submit" class = "button">Save Changes</button>
	                    </div>        
	                    <h1><% out.print(username); %></h1>
	                    <h1><% out.print(userType); %></h1>
	                    <h3><% out.print(firstname + " " + lastname); %></h3>
	                    <p><% out.print(signiture); %></p>
	                    <button onclick="myFunction('sign', 'changeSign')" id = "editsign" style = "display:none" type = "button" class = "button">Edit</button>
	              		<span >
			                 	<input type = "text" id = "sign" style = "display:none" name = "sign"/>
								<button  style = "display:none" id = "changeSign"  type = "submit" class = "button">Save Changes</button>
						</span>
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
	               		<% if(usr != null && profile.GetUserID() == id){ %>
	               			<% Warn warnU = new Warn(id); %>
	               			<% boolean isW = warnU.isWarned(); %>
	               			<% int fre = warnU.getFrequency(); %>
	               			<% Bann bannU = new Bann(id); %>
	               			<% boolean isB = bannU.isBanned(); %>
	               			<% if(isW){ %>
	               				 <li><label>Warned</label><span class="word-wrap"><% out.print(isW); %></span></li>
	               				 <li><label>1 Post In </label><span class="word-wrap"><% out.print(fre); %> hours</span></li>
	               				 <li><label>Warn Ends</label><span class="word-wrap"><% out.print(warnU.getEnd_date()); %></span></li>
	               			<% } %>
	               			<% if(isB){ %>
	               				 <li><label>Banned</label><span class="word-wrap"><% out.print(isB); %></span></li>
	               				 <li><label>Ban Ends</label><span class="word-wrap"><% out.print(bannU.getEnd_date()); %></span></li>
	               			<% } %>
	               		<% } %>
	                </ul>
	                <% if(profile.GetUserID() == id){%>
	                 <ul class="personal-infos">
	                 
	                 	<li>
		                 	<label><button  onclick="myFunction('firstName', 'changeName')" id = "name" style = "display:none" type = "button" class = "button">Edit</button></label>
		                 	<span >
		                 		<input type = "text" id = "firstName" style = "display:none" name = "firstName"/>
								<button  style = "display:none" id = "changeName"  type = "submit" class = "button">Save Changes</button>
							</span>
	                 	</li>
	                 	<li>
	                 		<label><button onclick="myFunction('lastname', 'changeLastName')" id = "lastName" style = "display:none" type = "button" class = "button">Edit</button></label>
		                 	<span >
			                 	<input type = "text" id = "lastname" style = "display:none" name = "lastname"/>
								<button  style = "display:none" id = "changeLastName"  type = "submit" class = "button">Save Changes</button>
							</span>
	                 	</li>
	                 	<li>
	                 		<label><button onclick="myFunction('password', 'changePass')"  id = "editPass" style = "display:none" type = "button" class = "button">Edit</button></label>
	                 		<span >
			                 	<input type = "text" id = "password" style = "display:none" name = "password"/>
								<button  style = "display:none" id = "changePass"  type = "submit" class = "button">Save Changes</button>
							</span>
	                 	</li>
	                 	<li>
	                 		<label><button onclick="myFunction('date', 'changeDate')" id = "editDate" style = "display:none" type = "button" class = "button">Edit</button></label>
	                 		<label style = "display:none" id = "date">
	                 		<select style = "display:inline" id="daydropdown"  name = "daydropdown">
							</select> 
							<select style = "display:inline" id="monthdropdown"  name = "monthdropdown">
							</select> 
							<select style = "display:inline" id="yeardropdown"  name = "yeardropdown">
							</select>
							</label> 
	                 		<script> 
				 				populatedropdown("daydropdown", "monthdropdown", "yeardropdown");
							</script>
							<button  id = "changeDate" style = "display:none" type = "submit" class = "button">Save Changes</button>
	                 	</li>
	                 	<li>
	                 		<label><button onclick="myFunction('gender', 'changeGender')" id = "editGender" style = "display:none" type = "button" class = "button">Edit</button></label>
		                 	<select id = "gender" name = "gender" style = "display:none">
								<option value = "" selected></option>
			  					<option value="male"> Male </option>
								<option value="female"> Female </option>
							</select>
							<button  style = "display:none" id = "changeGender"  type = "submit" class = "button">Save Changes</button>
	                 	</li>
						<li>
							<label><button onclick="myFunction('mail', 'changeMail')" id = "editmail" style = "display:none" type = "button" class = "button">Edit</button></label>
							<span >
			                 	<input type = "text" id = "mail" style = "display:none" name = "mail"/>
								<button  style = "display:none" id = "changeMail"  type = "submit" class = "button">Save Changes</button>
							</span>
						</li>
				 	</ul>
				 <%} %>
				 </form>
	             <ul class = "personal-info">
			      	<form action = <%= "WarnBann?id=" + id %> method = "post">
						<% if(adm != null && profile.GetUserID() != id && profile.GetUserType() != 1){ %>
							<% Warn warnUser = new Warn(id); %>
							<% boolean warned = warnUser.isWarned(); %>
							<% Bann bannUser = new Bann(id); %>
							<% boolean banned = bannUser.isBanned(); %>
							<li><label id = "warnLabel">Warn: <%= warned %></label></li>
							<select id = "warnUser" name = "warnUser" style = "display:none">
									<option value = "" selected></option>
				  					<option value = 1> 1 Month </option>
									<option value = 2> 2 Month </option>
									<option value = 3> 3 Month </option>
							</select><br>
							<select id = "freq" name = "freq" style = "display:none">
									<option value = "" selected></option>
				  					<option value = 5> 5 Hour </option>
									<option value = 10> 10 Hour </option>
									<option value = 15> 15 Hour </option>
							</select>
								<button  id = "warn" style = "display:none" type = "submit" class = "button">Warn User</button>
							<% 	if(!warned && !banned){ %>
									<script> 
						 				myFunction( "warn", "warnUser", "freq");
						 			</script>
						 	<% } %>
						 	<% if(warned && !banned){ %>
						 		<li><label>Warned till : <%= warnUser.getEnd_date() %></label></li>
						 		<li><label>Allowed 1 post in   <%= warnUser.getFrequency() %> hours</label></li>
						 		<button  id = "unWarn" type = "submit" name = "remove" value = "warn" class = "button">Remove Warn</button>
						 	<% } %>
							<li><label id = "bannLabel">Bann: <%= banned %></label></li>
							<select id = "bannUser" name = "bannUser" style = "display:none">
									<option value = "" selected></option>
				  					<option value = 1> 1 Month </option>
									<option value = 2> 2 Month </option>
									<option value = 3> 3 Month </option>
							</select>
								<button  id = "bann" style = "display:none" type = "submit" class = "button">Bann User</button>
							<% 	if(!banned){ %>
									<script> 
						 				myFunction("bann", "bannUser");
						 			</script>
						 	<% } %>
						 	<% if(banned){ %>
						 		<li><label>Banned till : <%= bannUser.getEnd_date() %></label></li>
						 		<button  id = "unBann" type = "submit" name = "remove" value = "bann" class = "button">Remove Bann</button>
						 	<% } %>
						<% } %>
				
						</form>
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