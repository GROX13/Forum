<%@page import="forum.data.accounts.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<%
		User user = new User();
		String categoryTitle = user.viewcaCategory(Integer.parseInt(request.getParameter("id"))).getTitle();
	%>
	<title>Add Theme in <%=categoryTitle%></title>
		
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
		
		<link rel="stylesheet" href="CSS/css/demo.css">
		<link rel="stylesheet" href="CSS/css/sky-forms.css">
		<link rel="stylesheet" href="CSS/css/sky-forms-green.css">
		
		<link rel = "icon" href = "Icons/Wineass_W.ico" type = "icon">
</head>
<body class="bg-red">
		<div class="body">
		
			<form action="HandleThemes" method="post" class="sky-form">
				<header>Theme form</header>
					
				<fieldset>					
					<div class="input">
						<section>
						<label class="label">Title</label>
						<label class="input">
							<input type="text" id = "theme" name = "theme" required>
						</label>
					</section>
					</div>
					
					<section>
						<label class="label">Description</label>
						<label class="textarea">
							<i class="icon-append icon-comment"></i>
							<textarea rows="4" id = "theme_description" name = "theme_description" required></textarea>
						</label>
						<div class="note">You may use these HTML tags and attributes: &lt;a href="" title=""&gt;, &lt;abbr title=""&gt;, &lt;acronym title=""&gt;, &lt;b&gt;, &lt;blockquote cite=""&gt;, &lt;cite&gt;, &lt;code&gt;, &lt;del datetime=""&gt;, &lt;em&gt;, &lt;i&gt;, &lt;q cite=""&gt;, &lt;strike&gt;, &lt;strong&gt;.</div>
					</section>
				</fieldset>
				
				<footer>
					<button type="submit" class="button">Add Theme</button>
				</footer>
			</form>
			
		</div>
	</body>
</html>