<%@page import="forum.data.accounts.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<%
		User user = new User();
		int themeID = Integer.parseInt(request.getParameter("id"));
		
		String themeTitle = user.viewTheme(themeID).getTitle();
	%>
	<title>Add Post in <%=themeTitle%></title>
		
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
		
		<link rel="stylesheet" href="CSS/css/demo.css">
		<link rel="stylesheet" href="CSS/css/sky-forms.css">
		<link rel="stylesheet" href="CSS/css/sky-forms-green.css">
		<link rel="stylesheet" href="CSS/css/smileys.css">
		
		<link rel = "icon" href = "Icons/Wineass_W.ico" type = "icon">
		
		<script>

			function addSmiley (txt, ta) {
				var div = document.getElementById(ta);
				div.value = div.value + txt +' ' ;
			}

		</script>
</head>
<body class="bg-red">
		<div class="body">
		<%String handlePosts = "upload?id=" + themeID ; %>
			<form action=<% out.print("\"" + handlePosts + "\""); %> method="post" class="sky-form" >
				<header>Post form</header>
					
				<fieldset>					
					<section>
						<label class="label">Post Text: </label>
						<label class="textarea">
							<i class="icon-append icon-comment"></i>
							<textarea rows="4" id = "post" name = "post" required></textarea>
							<div id="emoticons" >
							    <a href="#"  onclick = "addSmiley('&#128507;', 'post');" class= "sm">&#128507;</a>
							    <a href="#"  onclick = "addSmiley('&#128508;', 'post');" class= "sm">&#128508;</a>
							    <a href="#"  onclick = "addSmiley('&#128509;', 'post');" class= "sm">&#128509;</a>
							    <a href="#"  onclick = "addSmiley('&#128510;', 'post');" class= "sm">&#128510;</a>
							    <a href="#"  onclick = "addSmiley('&#128511;', 'post');" class= "sm">&#128511;</a>
							    <a href="#"  onclick = "addSmiley('&#128513;', 'post');" class= "sm">&#128513;</a>
							    <a href="#"  onclick = "addSmiley('&#128514;', 'post');" class= "sm">&#128514;</a>
							    <a href="#"  onclick = "addSmiley('&#128515;', 'post');" class= "sm">&#128515;</a>
							    <a href="#"  onclick = "addSmiley('&#128516;', 'post');" class= "sm">&#128516;</a>
							    <a href="#"  onclick = "addSmiley('&#128517;', 'post');" class= "sm">&#128517;</a>
							    <a href="#"  onclick = "addSmiley('&#128518;', 'post');" class= "sm">&#128518;</a>
							    <a href="#"  onclick = "addSmiley('&#128519;', 'post');" class= "sm">&#128519;</a>
							    <a href="#"  onclick = "addSmiley('&#128520;', 'post');" class= "sm">&#128520;</a>
							    <a href="#"  onclick = "addSmiley('&#128521;', 'post');" class= "sm">&#128521;</a>
							    <a href="#"  onclick = "addSmiley('&#128522;', 'post');" class= "sm">&#128522;</a>
							    <a href="#"  onclick = "addSmiley('&#128523;', 'post');" class= "sm">&#128523;</a>
							    <a href="#"  onclick = "addSmiley('&#128524;', 'post');" class= "sm">&#128524;</a>
							    <a href="#"  onclick = "addSmiley('&#128525;', 'post');" class= "sm">&#128525;</a>
							    <a href="#"  onclick = "addSmiley('&#128526;', 'post');" class= "sm">&#128526;</a>
							    <a href="#"  onclick = "addSmiley('&#128527;', 'post');" class= "sm">&#128527;</a>
							    <a href="#"  onclick = "addSmiley('&#128528;', 'post');" class= "sm">&#128528;</a>
							    <a href="#"  onclick = "addSmiley('&#128549;', 'post');" class= "sm">&#128549;</a>
							    <a href="#"  onclick = "addSmiley('&#128530;', 'post');" class= "sm">&#128530;</a>
							    <a href="#"  onclick = "addSmiley('&#128531;', 'post');" class= "sm">&#128531;</a>
							    <a href="#"  onclick = "addSmiley('&#128532;', 'post');" class= "sm">&#128532;</a>
							    <a href="#"  onclick = "addSmiley('&#128545;', 'post');" class= "sm">&#128545;</a>
							    <a href="#"  onclick = "addSmiley('&#128534;', 'post');" class= "sm">&#128534;</a>
							    <a href="#"  onclick = "addSmiley('&#128541;', 'post');" class= "sm">&#128541;</a>
							    <a href="#"  onclick = "addSmiley('&#128536;', 'post');" class= "sm">&#128536;</a>
							    <a href="#"  onclick = "addSmiley('&#128565;', 'post');" class= "sm">&#128565;</a>
							    <a href="#"  onclick = "addSmiley('&#128575;', 'post');" class= "sm">&#128575;</a>
							    <a href="#"  onclick = "addSmiley('&#128585;', 'post');" class= "sm">&#128585;</a>
							    <a href="#"  onclick = "addSmiley('&#128581;', 'post');" class= "sm">&#128581;</a>
							</div>
						
						</label>
						<div class="note">You may use these HTML tags and attributes: &lt;a href="" title=""&gt;, &lt;abbr title=""&gt;, &lt;acronym title=""&gt;, &lt;b&gt;, &lt;blockquote cite=""&gt;, &lt;cite&gt;, &lt;code&gt;, &lt;del datetime=""&gt;, &lt;em&gt;, &lt;i&gt;, &lt;q cite=""&gt;, &lt;strike&gt;, &lt;strong&gt;.</div>
					</section>
					 
					<%--<section>
						<label class="label">File input</label>
						<label for="file" class="input input-file">
							<div class="button">
							<input type="file" name="file" id="file">Browse</div>
							<input type="text" readonly="">
						</label>
					</section> --%>
					
				</fieldset>
				
				<footer>
					<button type="submit" class="button" name="upload" id="upload">Add Post</button>
				</footer>
			</form>
			
		</div>
	</body>
</html>