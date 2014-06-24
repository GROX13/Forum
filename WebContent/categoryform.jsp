<%@page import="forum.data.accounts.Admin"%>
<%@page import="forum.data.accounts.User"%>
<%@ page language="java" contentType="text/html; charset = UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<% User user = (User) request.getSession().getAttribute("user");%>
	<% Admin admin = (Admin) request.getSession().getAttribute("admin");%>
	<% boolean isUser = (user != null);%>
	<% boolean isAdmin = (admin != null);%>
	<% boolean isGuest = (!isUser && !isAdmin);%>
	<head>
		<title>Add Category</title>
		
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
		
		<link rel="stylesheet" href="CSS/css/demo.css">
		<link rel="stylesheet" href="CSS/css/sky-forms.css">
		<link rel="stylesheet" href="CSS/css/sky-forms-green.css">
		
		<link rel = "icon" href = "Icons/Wineass_W.ico" type = "icon">
	</head>
	<body class="bg-red">
		<div class="body">
		
			<form action="#" class="sky-form">
				<header>Category form</header>
					
				<fieldset>					
					<div class="input">
						<section class="col col-4">
							<label class="label">Name</label>
							<label class="input">
								<i class="icon-append icon-user"></i>
								<input type="text">
							</label>
						</section>
					</div>
					
					<section>
						<label class="label">Comment</label>
						<label class="textarea">
							<i class="icon-append icon-comment"></i>
							<textarea rows="4"></textarea>
						</label>
						<div class="note">You may use these HTML tags and attributes: &lt;a href="" title=""&gt;, &lt;abbr title=""&gt;, &lt;acronym title=""&gt;, &lt;b&gt;, &lt;blockquote cite=""&gt;, &lt;cite&gt;, &lt;code&gt;, &lt;del datetime=""&gt;, &lt;em&gt;, &lt;i&gt;, &lt;q cite=""&gt;, &lt;strike&gt;, &lt;strong&gt;.</div>
					</section>
				</fieldset>
				
				<footer>
					<button type="submit" class="button">Add comment</button>
				</footer>
			</form>
			
		</div>
	

<!-- Mirrored from voky.com.ua/showcase/sky-forms/examples/demo-comment.html by HTTrack Website Copier/3.x [XR&CO'2013], Mon, 23 Jun 2014 12:58:14 GMT -->
</body></html>