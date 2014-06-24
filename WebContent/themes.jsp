<%@page import="forum.managers.objects.CategoryManager"%>
<%@page import="servlet.themes.HandleThemes"%>
<%@page import="forum.data.accounts.Admin"%>
<%@page import="forum.data.accounts.User"%>
<%@page import="forum.data.objects.Profile"%>
<%@page import="java.util.Iterator"%>
<%@page import="forum.data.objects.Theme"%>
<%@page import="java.util.Map"%>
<%@page import="forum.managers.objects.ThemeManager"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Categories</title>
		
		<link rel="stylesheet" href="CSS/css/stylemenu.css">
		<link rel="stylesheet" href="CSS/css/style-themes.css">
		<link rel="stylesheet" href="CSS/css/demo.css">
		<link rel="stylesheet" href="CSS/css/sky-forms.css">
		
		<link rel = "icon" href = "Icons/Wineass_W.ico" type = "icon">
<% int id = Integer.parseInt(request.getParameter("id")); 
	User usr = new User();
	String categoryName = usr.viewcaCategory(id).getTitle();
	%>
<title><%= categoryName %></title>

</head>
<body>
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
      			<%String s = "themeform.jsp?id=" + id; %>
      			<a href=<% out.print("\"" + s+ "\""); %> class="menu-link">
       				<li>Add Theme</li>
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
        public String liDecorator(int id, String name){
            return "<li><a href=\"Posts.jsp?id=" + id + "\">" + name + "</a></li>";
        }
    %>
    <%!
        public String editButtons(int i){
    		String changeName = "change" + i;
    		String editName = "edit" + i;
    		String passName = "pass" + i;
            return "<form action = \"HandleThemeChanges?id=" + i + "\"" + "method = \"post\">" + 
            "<button onclick=\"myFunction('"+passName+"', '" + changeName +"')\" id = " + editName +" style = \"display:none\" type = \"button\">Edit</button>" +
            "<p><input type = \"text\" id = "+ passName +" style = \"display:none\" name = "+ passName +" /></p>" +
            "<button  id =" +changeName+" style = \"display:none\" type = \"submit\">Save Changes</button>" +
            "</form>";
            
        }
    %>
    
    <%!
        public String removeTheme(int i){
            return "<a href =\"HandleThemeRemove?id=" + i + "\">" + "Remove Theme </a>";
        }
    %>
    
     <%!
        public String showButtons(int i){
    	 	String editName = "edit" + i;
            return "<script>" + 
		 	"myFunction('" + editName +"')" +
			"</script>";
            
        }
    %>
    <article class="themes">
	<% ThemeManager tm = (ThemeManager)request.getServletContext().getAttribute("themes"); %>
	
	<% Map<Integer, Theme> all = tm.getAll(id); %>
	<% Iterator<Map.Entry<Integer, Theme>> iter = all.entrySet().iterator(); %>
		<% while(iter.hasNext()){ %>
		<% 		Map.Entry<Integer, Theme> entry = iter.next(); %>
		<%		int tId = entry.getKey(); %>
		<%		Theme value = entry.getValue(); %>
		<%		if(value.getOpen()){											 
					out.print(liDecorator(tId, value.getTitle()));
				}else{
					if(isAdmin || isUser)
						out.print(liDecorator(tId, value.getTitle()));
				}
		%>
		<%		if(isUser){ %>
		<%			out.print(editButtons(tId)); %>
		<%			out.print(removeTheme(tId)); %>
		<%			out.print(showButtons(tId)); %>
		<%		}%>
		<% } %> 
		</article>
		<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.js"></script>
  		<script src="JavaScript/menu-opener.js"></script>
</body>
</html>