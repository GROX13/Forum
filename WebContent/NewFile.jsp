<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="NewFile.css"> 
</head>
<body>

<script>

	function addSmiley (txt, ta) {
		var div = document.getElementById(ta);
		div.value = div.value + txt +' ' ;
	}

</script>

<textarea id="description" name="description"></textarea>

<div id="emoticons">
    <a href="#"  onclick = "addSmiley('&#128513;', 'description');">&#128513;</a>
    <a href="#"  onclick = "addSmiley('&#128514;', 'description');">&#128514;</a>
    <a href="#"  onclick = "addSmiley('&#128515;', 'description');">&#128515;</a>
    <a href="#"  onclick = "addSmiley('&#128516;', 'description');">&#128516;</a>
    <a href="#"  onclick = "addSmiley('&#128517;', 'description');">&#128517;</a>
    <a href="#"  onclick = "addSmiley('&#128518;', 'description');">&#128518;</a>
    <a href="#"  onclick = "addSmiley('&#128519;', 'description');">&#128519;</a>
    <a href="#"  onclick = "addSmiley('&#128520;', 'description');">&#128520;</a>
</div>

</body>
</html>