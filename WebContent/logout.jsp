<%@ page language="java" contentType="text/html; charset = UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Log out</title>
		
		<link rel="stylesheet" href="CSS/css/demo.css">
		<link rel="stylesheet" href="CSS/css/sky-forms.css">
		
		<link rel = "icon" href = "Icons/Wineass_W.ico" type = "icon">
	</head>
	<body class="bg-red">
		<div class="body body-s">
		
			<form action = "HandleLogout" method = "post" class="sky-form">
				<header>Sure you want to log out?</header>
				<footer>
					<button type="submit" class="button">Log out</button>
					<a href="category.jsp" class="button button-secondary">Go back</a>		
				</footer>
			</form>
			
		</div>
	</body>
</html>