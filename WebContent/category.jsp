<%@page import="forum.data.objects.Category"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@page import="forum.managers.objects.CategoryManager"%>
<%@page import="forum.data.objects.Profile"%>
<%@page import="forum.data.accounts.User"%>
<%@page import="forum.data.accounts.Admin"%>
<%@ page language="java" contentType="text/html; charset = UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<% User user = (User) request.getSession().getAttribute("user");%>
	<% Admin admin = (Admin) request.getSession().getAttribute("admin");%>
	<% CategoryManager cm = (CategoryManager)request.getServletContext().getAttribute("categories"); %>
	<% boolean isUser = (user != null);%>
	<% boolean isAdmin = (admin != null);%>
	<% boolean isGuest = (!isUser && !isAdmin);%>
	<% String myProfileLink = "profile.jsp"; %> 
	<% if (!isGuest){ %>
	<% 		if (isAdmin){ %>
	<% 			myProfileLink += "?id=" + admin.getProfile().GetUserID();%>
	<% 		}%>
	<%		if (isUser){ %>
	<% 			myProfileLink += "?id=" + user.getProfile().GetUserID();%>
	<% 		}%>
	<% }%>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Categories</title>
		
		<link rel="stylesheet" href="CSS/css/stylemenu.css">
		<link rel="stylesheet" href="CSS/css/style-categories.css">
		<link rel="stylesheet" href="CSS/css/demo.css">
		<link rel="stylesheet" href="CSS/css/sky-forms.css">
		
		<link rel = "icon" href = "Icons/Wineass_W.ico" type = "icon">
	</head>
	<body class="bg-red">
  		<nav class="menu-opener">
    		<div class="menu-opener-inner"></div>
  		</nav>
  		<nav class="menu">			 		
  			<ul class="menu-inner">
  				<% if (isGuest){ %>
  				<a href="index.jsp" class="menu-link">
        			<li>Log In</li>
      			</a>
      			<a href="#" class="menu-link">
        			<li>About us</li>
      			</a> 
  				<% }%> 
  				<% if (isAdmin){ %>
  				<a href=<% out.print("\"" + myProfileLink + "\""); %> class="menu-link">
        			<li>Profile</li>
      			</a>
      			<a href="categoryform.jsp" class="menu-link">
       				<li>Add category</li>
      			</a>
      			<a href="logout.jsp" class="menu-link">
        			<li>Log out</li>
      			</a>
      			<a href="#" class="menu-link">
        			<li>About us</li>
      			</a> 
  				<% }%> 
  				<% if (isUser){ %>
  				<a href=<% out.print("\"" + myProfileLink + "\""); %> class="menu-link">
        			<li>Profile</li>
      			</a> 
      			<a href="logout.jsp" class="menu-link">
        			<li>Log out</li>
      			</a>
      			<a href="#" class="menu-link">
        			<li>About us</li>
      			</a> 
  				<% }%> 
    		</ul>
  		</nav>
  		<script>
	function myFunction(arg1, arg2, arg3, arg4, arg5) {
		 document.getElementById(arg1).style.display = "block";
		 document.getElementById(arg2).style.display = "block";
		 document.getElementById(arg3).style.display = "block";
		 document.getElementById(arg4).style.display = "block";
		 document.getElementById(arg5).style.display = "block";
	}
	</script>
  		 <%!
        public String editButtons(int i){
    		String changeName = "change" + i;
    		String editName = "edit" + i;
    		String passName = "pass" + i;
            return "<form action = \"HandleCategoryChanges?id=" + i + "\"" + "method = \"post\">" + 
            "<button onclick=\"myFunction('"+passName+"', '" + changeName +"')\" id = " + editName +" style = \"display:none\" type = \"button\">Edit</button>" +
            "<p><input type = \"text\" id = "+ passName +" style = \"display:none\" name = "+ passName +" /></p>" +
            "<button  id =" +changeName+" style = \"display:none\" type = \"submit\">Save Changes</button>" +
            "</form>";
            
        }
    %>
     <%!
        public String showButtons(int i){
    	 	String editName = "edit" + i;
    	 //	String removeName = "remove" + i;
            return "<script>" + 
		 	"myFunction('" + editName +"')" +
			"</script>";
            
        }
    %>
  		<article class="categories">
			<% Map<Integer, Category> all = cm.getAll(); %>
			<% Iterator<Map.Entry<Integer, Category>> iter = all.entrySet().iterator(); %>
			<% iter = all.entrySet().iterator(); %>
			<% while(iter.hasNext()){ %>
			<% 		Map.Entry<Integer, Category> entry = iter.next(); %>
			<%		int id = entry.getKey(); %>
			<%		Category cat = entry.getValue(); %>	
			<% 		out.print("<div><p><a href = \"themes.jsp?id=" 
						+ id + "\">" + cat.getTitle() + "</a></p><p>"  
						+ cat.getDescription() + "</p></div>"); 
				if (isAdmin) {
					out.print(editButtons(id)); 
					out.print("<a href =\"HandleCategoryRemove?id=" + id + "\">" + "Remove Category </a>");
					out.print(showButtons(id)); 
			}
			%>
			<% } %>  
		</article>
  		<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.js"></script>
  		<script src="JavaScript/menu-opener.js"></script>
	</body>
</html>