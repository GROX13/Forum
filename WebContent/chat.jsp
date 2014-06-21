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
		ArrayList<Message> list = (ArrayList<Message>) request.getSession().getAttribute("message_list"); 
		if (list != null) {
			for (int i = 0; i < list.size(); i++){
				out.print("Message: " + list.get(i).getMessageText() 
						+ " Send:" + list.get(i).getMessageSendDate() + "<br>");
			}
		}
		%>
	</body>
</html>