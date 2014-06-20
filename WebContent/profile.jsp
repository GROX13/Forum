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
		<p><input type = "text" id = "lastName" style = "display:none"/>
		<button  id = "change2" style = "display:none" type = "submit">Save Changes</button>
		<% String gen; %>
		<% if(p.GetGender().equals("m")) gen = "male"; else gen = "female";%>
		<p>Gender: <%= gen %></p>
		<button onclick="myFunction('gender', 'change3')" id = "edit3" style = "display:none" type = "button">Edit</button>	
		<p><input type = "text" id = "gender" style = "display:none"/></p>
		<button  id = "change3" style = "display:none" type = "submit">Save Changes</button>
		<p>BirthDate: <%= p.GetBirthDate() %></p>
		<button onclick="myFunction('birthdate', 'change4')" id = "edit4" style = "display:none" type = "button">Edit</button>
		<p><input type = "text" id = "birthdate" style = "display:none"/></p>
		<button  id = "change4" style = "display:none" type = "submit">Save Changes</button>
		<p>Email: <%= p.GetEmail() %></p>
		<button onclick="myFunction('email', 'change5')" id = "edit5" style = "display:none" type = "button">Edit</button>
		<p><input type = "text" id = "email" style = "display:none"/></p>
		<button  id = "change5" style = "display:none" type = "submit">Save Changes</button>
		<p>Avatar: <%= p.GetAvatar() %></p>
		<button onclick="myFunction('avatar', 'change6')" id = "edit6" style = "display:none" type = "button">Edit</button>
		<p><input type = "text" id = "avatar" style = "display:none"/></p>
		<button  id = "change6" style = "display:none" type = "submit">Save Changes</button>
		<p>Signature: <%= p.GetSignature() %></p>
		<button onclick="myFunction('sign', 'change7')" id = "edit7" style = "display:none" type = "button">Edit</button>
		<p><input type = "text" id = "sign" style = "display:none"/></p>
		<button  id = "change7" style = "display:none" type = "submit">Save Changes</button>
	</form>
	<% if(p.GetUserID() == id) { %>
		 <script> 
		 	myFunction("edit","edit1", "edit2", "edit3", "edit4");
		 	myFunction("edit5","edit6", "edit7", "edit8");
		 </script>
	<% } %>
</body>
</html>