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
				ArrayList<Message> mess = null;
				User usr = (User) request.getSession().getAttribute("user");
				Admin adm = (Admin) request.getSession().getAttribute("admin");
				if (usr != null) {
					mess = usr.seeFullConversation(id);
				} else if(adm != null) {
					mess = adm.seeFullConversation(id);
				}
				if (mess != null) {
					for (int i = 0; i < mess.size(); i++) {
						out.print("Sent:" + mess.get(i).getMessageSendDate() + "<br>");
						out.print("Sent:" + mess.get(i).getMessageText() + "<br>");
					}
				}
			}
		}
		%>
	</body>
</html>