<%@ page language = "java" contentType = "text/html; charset = UTF-8"
	pageEncoding = "UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv = "Content-Type" content = "text/html; charset = UTF-8">
		<title> Welcome </title>
		<script src = "Login/fblogin.js" type = "text/javascript"> </script>	
		<script src = "Login/glogin.js" type = "text/javascript"> </script>
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
			<p>		
				<span id="signinButton">
					<span
						class="g-signin"
						data-callback="signinCallback"
						data-clientid="CLIENT_ID"
						data-cookiepolicy="single_host_origin"
						data-requestvisibleactions="http://schemas.google.com/AddActivity"
						data-scope="https://www.googleapis.com/auth/plus.login">
					</span>
				</span>
			
				<!--
					Below we include the Login Button social plugin. This button uses
  					the JavaScript SDK to present a graphical Login button that triggers
  					the FB.login() function when clicked.
				-->
				<fb:login-button scope="public_profile,email" onlogin="checkLoginState();">
				</fb:login-button>
				
				<div id="status">
				</div>
				
			</p>	
		</form>		
	</body>
</html>