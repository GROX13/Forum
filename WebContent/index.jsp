<%@ page language = "java" contentType = "text/html; charset = UTF-8"
	pageEncoding = "UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv = "Content-Type" content = "text/html; charset = UTF-8">
		<title> Welcome </title>	
	</head>
	<body>
		<h1> Log In! </h1>	
		<form action = "HandleLogin" method = "post">	
			<p>
				<label for = "username"> User name: </label>	
				<input type = "text" id = "username" name = "username" required>
			</p>
			<p>		
				<label for = "password"> Password: </label>		
				<input type = "password" id = "password" name = "password" required>
			</p>
			<p> 
				<a href ="registration.jsp"> Create New Account </a>
			</p>
			<p> 
				<a href ="category.jsp"> Login As Guest </a>
			</p>
			<p>		
				<input type = "submit" value = "Login">	
			</p>	
		</form>		
	</body>
</html>