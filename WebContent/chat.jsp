<%@page import="forum.data.objects.Message"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset = UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Chat Room</title>
	</head>
	<body>
		<form action = "HandleChat" method = "post">
			User name: <input type="text" name="username"><br>
			Message: <input type="text" name="message"><br>
			<input type="submit" value="Submit">
		</form>
		<% 
		Object ID = request.getSession().getAttribute("chatter_id"); 
		if(ID != null) {
			int id = Integer.parseInt(ID.toString());
			if (id > 0) {
				out.print("hdsss");
			}
		}
		%>
	</body>
</html>