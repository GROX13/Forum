<%@page import="forum.data.objects.Bann"%>
<%@page import="forum.data.objects.Warn"%>
<%@page import="forum.data.objects.Profile"%>
<%@page import="forum.data.accounts.Admin"%>
<%@page import="forum.data.accounts.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<% int id = Integer.parseInt(request.getParameter("id")); 
	User u = new User();
	Profile prof = u.viewProfile(id);
	
%>
<title><%=prof.GetUsername() + "'s Profile" %></title>
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
</script>
</head>
<body>
	<script>
	function myFunction(arg1, arg2, arg3, arg4, arg5) {
		 document.getElementById(arg1).style.display = "block";
		 document.getElementById(arg2).style.display = "block";
		 document.getElementById(arg3).style.display = "block";
		 document.getElementById(arg4).style.display = "block";
		 document.getElementById(arg5).style.display = "block";
	}
	</script>
	<% Admin adm = (Admin)request.getSession().getAttribute("admin"); %>
	<% User usr = (User)request.getSession().getAttribute("user"); %>
	<% Profile p = null; %>
	<% Profile temp = null; %>
	<% if(adm != null) { %>
		<% p = adm.getProfile(); %>
		<% temp = adm.viewProfile(id); %>
	<% } %>
	<% if(usr != null){ %>
		<% p = usr.getProfile(); %>
		<% temp = usr.viewProfile(id); %>
	<% } %>
	<% if(p != null && p.GetUserID() != id){ %>
		<% p = temp;} %>
	<% if(p==null){ %>
		 <% User tempUser = new User(); %>
		 <% p = tempUser.viewProfile(id); } %>
	<form action = "ChangeData" method = "post">
		<p>UserName: <%= p.UserName() %></p>
		<% String usType = ""; %>
		<% if(p.GetUserType() == 1) usType = "Admin"; if(p.GetUserType() == 0) usType= "User"; %>
		<p>UserType: <%= usType %></p>
		<p id = "edit" style = "display:none">Password: <%= p.GetPasword() %></p>
		<button onclick="myFunction('pass', 'change8')" id = "edit8" style = "display:none" type = "button">Edit</button>
		<p><input type = "text" id = "pass" style = "display:none" name = "pass"/></p>
		<button  id = "change8" style = "display:none" type = "submit">Save Changes</button>
		<p>FirstName: <%= p.GetFirstName() %></p>
		<button onclick="myFunction('firstName','change1')" id = "edit1" style = "display:none" type = "button">Edit</button>	
		<p><input type = "text" id = "firstName" style = "display:none" name = "firstname"/></p>
		<button  id = "change1" style = "display:none" type = "submit">Save Changes</button>
		<p>LastName: <%= p.GetLastName() %></p>
		<button onclick="myFunction('lastName', 'change2')" id = "edit2" style = "display:none" type = "button">Edit</button>
		<p><input type = "text" id = "lastName" style = "display:none" name = "lastname"/>
		<button  id = "change2" style = "display:none" type = "submit">Save Changes</button>
		<% String gen; %>
		<% if(p.GetGender().equals("m")) gen = "male"; else gen = "female";%>
		<p>Gender: <%= gen %></p>
		<button onclick="myFunction('gender', 'change3')" id = "edit3" style = "display:none" type = "button">Edit</button>	
		<select id = "gender" name = "gender" style = "display:none">
					<option value = "" selected></option>
  					<option value="male"> Male </option>
					<option value="female"> Female </option>
		</select>
		<button  id = "change3" style = "display:none" type = "submit">Save Changes</button>
		<p>BirthDate: <%= p.GetBirthDate() %></p>
		<button onclick="myFunction('daydropdown', 'change4', 'monthdropdown', 'yeardropdown')" id = "edit4" style = "display:none" type = "button">Edit</button>
		<select id="daydropdown" style = "display:none" name = "daydropdown">
		</select> 
		<select id="monthdropdown" style = "display:none" name = "monthdropdown">
		</select> 
		<select id="yeardropdown" style = "display:none" name = "yeardropdown">
		</select> 
		<script> 
		 	populatedropdown("daydropdown", "monthdropdown", "yeardropdown");
		</script>
		<button  id = "change4" style = "display:none" type = "submit">Save Changes</button>
		<p>Email: <%= p.GetEmail() %></p>
		<button onclick="myFunction('email', 'change5')" id = "edit5" style = "display:none" type = "button">Edit</button>
		<p><input type = "text" id = "email" style = "display:none" name = "email"/></p>
		<button  id = "change5" style = "display:none" type = "submit">Save Changes</button>
		<p>Avatar:<img src = <%= p.GetAvatar() %>></p>
		<button onclick="myFunction('avatar', 'change6')" id = "edit6" style = "display:none" type = "button">Edit</button>
		<p><input type = "file" id = "avatar" style = "display:none" name = "avatar"/></p>
		<button  id = "change6" style = "display:none" type = "submit">Save Changes</button>
		<p>Signature: <%= p.GetSignature() %></p>
		<button onclick="myFunction('sign', 'change7')" id = "edit7" style = "display:none" type = "button">Edit</button>
		<p><input type = "text" id = "sign" style = "display:none" name = "signature"/></p>
		<button  id = "change7" style = "display:none" type = "submit">Save Changes</button>
		</form>
		<form action = <%= "WarnBann?id=" + id %> method = "post">
		<% if(adm != null && p.GetUserID() != id){ %>
			<% Warn warnUser = new Warn(id); %>
			<% boolean warned = warnUser.isWarned(); %>
			<p>Warn: <%= warned %></p>
			<select id = "warnUser" name = "warnUser" style = "display:none">
					<option value = "" selected></option>
  					<option value = 1> 1 Month </option>
					<option value = 2> 2 Month </option>
					<option value = 3> 3 Month </option>
			</select>
			<select id = "freq" name = "freq" style = "display:none">
					<option value = "" selected></option>
  					<option value = 5> 5 Post </option>
					<option value = 10> 10 Post </option>
					<option value = 15> 15 Post </option>
			</select>
				<button  id = "warn" style = "display:none" type = "submit">Warn User</button>
			<% 	if(!warned){ %>
					<script> 
		 				myFunction("warn", "warnUser", "freq");
		 			</script>
		 	<% } %>
		 	<% if(warned){ %>
		 		<p>Warned till : <%= warnUser.getEnd_date() %></p>
		 		<p>Allowed number of posts : <%= warnUser.getFrequency() %></p>
		 		<button  id = "unWarn" type = "submit" name = "remove" value = "warn">Remove Warn</button>
		 	<% } %>
		 	<% Bann bannUser = new Bann(id); %>
			<% boolean banned = bannUser.isBanned(); %>
			<p>Bann: <%= banned %></p>
			<select id = "bannUser" name = "bannUser" style = "display:none">
					<option value = "" selected></option>
  					<option value = 1> 1 Month </option>
					<option value = 2> 2 Month </option>
					<option value = 3> 3 Month </option>
			</select>
				<button  id = "bann" style = "display:none" type = "submit">Bann User</button>
			<% 	if(!banned){ %>
					<script> 
		 				myFunction("bann", "bannUser");
		 			</script>
		 	<% } %>
		 	<% if(banned){ %>
		 		<p>Banned till : <%= bannUser.getEnd_date() %></p>
		 		<button  id = "unBann" type = "submit" name = "remove" value = "bann">Remove Bann</button>
		 	<% } %>
		<% } %>
		</form>
	<% if(p.GetUserID() == id) { %>
		 <script> 
		 	myFunction("edit","edit1", "edit2", "edit3", "edit4");
		 	myFunction("edit5","edit6", "edit7", "edit8");
		 </script>
	<% } %>
</body>
</html>