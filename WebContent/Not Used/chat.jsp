<%@ page language="java" contentType="text/html; charset = UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Chat - Customer Module</title>
		<link type="text/css" rel="stylesheet" href="CSS/chatstyle.css" />
	</head>
	<body>
		<div id="wrapper">
			<div id="menu">
				<p class="welcome">Welcome, <b></b></p>
				<form name="message" action="">
					<input name="usermsg" type="text" id="usermsg" size="63" /> 
					<input name="submitmsg" type="submit" id="submitmsg" value="Send" />
				</form>
				<p class="logout"><a id="exit" href="#">Exit Chat</a></p>
				<div style="clear: both"></div>
			</div>
			<div id="chatbox"></div>

			<form name="message" action="">
				<input name="usermsg" type="text" id="usermsg" size="63" /> 
				<input name="submitmsg" type="submit" id="submitmsg" value="Send" />
			</form>
		</div>
		<script type="text/javascript"
			src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js">
		</script>
		<script type="text/javascript">
			// jQuery Document
			$(document).ready(function() {});
		</script>
	</body>
</html>