<%@ page language = "java" contentType = "text/html; charset = UTF-8"
	pageEncoding = "UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv = "Content-Type" content = "text/html; charset = UTF-8">
		<title> Welcome </title>	
	</head>
	<body>
		<h1> Register </h1>
		<form action = "HandleRegistration" method = "post">
			<p>
				<label for = "username"> User name: </label>	
				<input type = "text" id = "username" name = "username" required>
			</p>
			<p>
				<label for = "firstname"> First name: </label>	
				<input type = "text" id = "firstname" name = "firstname">
			</p>
			<p>
				<label for = "lastname"> Last name: </label>	
				<input type = "text" id = "lastname" name = "lastname">
			</p>
			<p>
				<label for = "email"> Email: </label>	
				<input type = "text" id = "email" name = "email">
			</p>
			<p>
				<label for = "avatar"> Avatar: </label>	
				<input type = "file" id = "file" name = "file">
			</p>
			<p>
				<label for = "birth"> Birth Date: </label>	
				<input type = "date" id = "birth" name = "birth" required>
			</p>
			<p>
				<label for = "signiture"> Signiture: </label>	
				<input type = "text" id = "signiture" name = "signiture" required>
			</p>
			<p>
				<label for = "gender"> Gender: </label>	
				<select id = "gender" name = "gender">
  					<option value="male"> Male </option>
					<option value="female"> Female </option>
				</select>
			</p>
			<p>		
				
				<label for = "password"> Password: </label>		
				<input type = "password" id = "password" name = "password" required>
			</p>
			<p> 
				<a href ="category.jsp"> Login As Guest </a>
			</p>
			<p>		
				<input type = "submit" value = "Register">	
			</p>
		</form>	
	</body>
</html>