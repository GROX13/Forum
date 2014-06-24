<%@page import="forum.data.accounts.Admin"%>
<%@page import="forum.data.accounts.User"%>
<%@page import="forum.data.objects.Message"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset = UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Chat Room</title>
		
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
		
		<link rel="stylesheet" href="CSS/css/demo.css">
		<link rel="stylesheet" href="CSS/css/sky-forms.css">
		<link rel="stylesheet" href="CSS/css/sky-forms-green.css">
		
		<link rel = "icon" href = "Icons/Wineass_W.ico" type = "icon">
	</head>
	<body class="bg-red">
		<div class="body">
		
			<form action="HandleChat" method = "post" class="sky-form">
				<header>Chat Room</header>
					
				<fieldset>					
					<div class="input">
						<section>
						<label class="label">Username</label>
						<label class="input">
							<input type="text" id = "username" name = "username" required>
						</label>
						</section>
					</div>
					
					<section>
						<label class="label">Message</label>
						<label class="textarea">
							<i class="icon-append icon-comment"></i>
							<textarea rows="4" id = "message" name = "message" required></textarea>
						</label>
						<div class="note">You may use these HTML tags and attributes: &lt;a href="" title=""&gt;, &lt;abbr title=""&gt;, &lt;acronym title=""&gt;, &lt;b&gt;, &lt;blockquote cite=""&gt;, &lt;cite&gt;, &lt;code&gt;, &lt;del datetime=""&gt;, &lt;em&gt;, &lt;i&gt;, &lt;q cite=""&gt;, &lt;strike&gt;, &lt;strong&gt;.</div>
					</section>
				</fieldset>
				
				<footer>
					<button type="submit" class="button">Send</button>
				</footer>
			</form>
			
		</div>
	</body>
</html>