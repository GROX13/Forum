<%@ page language = "java" contentType = "text/html; charset = UTF-8"
	pageEncoding = "UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="no-js">

    <head>
        <meta charset="utf-8">
        <title>Registration</title>
        <link rel = "icon" href = "Icons/Wineass_W.ico" type = "icon">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- CSS -->
        <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'>
        <link rel="stylesheet" href="CSS/reset.css">
        <link rel="stylesheet" href="CSS/supersized.css">
        <link rel="stylesheet" href="CSS/style.css">

        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

    </head>

    <body>

        <div class="page-container">
            <h1>Welcome to our website</h1>
            <form action="" method="post">
                <input type="text" name="username" class="username" placeholder="Username">
                <input type="text" name="email" class="username" placeholder="Email">
                <input type="text" name="firstname" class="username" placeholder="First Name">
                <input type="text" name="lastname" class="username" placeholder="Last Name">
                <input type="text" name="Gender" class="username" placeholder="Sex">
                <input type="text" name="signature" class="username" placeholder="Signature">
                <input type="date" name="birthdate" class="username">
                <input type="password" name="password" class="password" placeholder="Password">
                <button type="submit">Register me</button>
                <div class="error"><span>+</span></div>
            </form>
        </div>

        <!-- Javascript -->
        <script src="JavaScript/jquery-1.8.2.min.js"></script>
        <script src="JavaScript/supersized.3.2.7.min.js"></script>
        <script src="JavaScript/supersized-init.js"></script>
        <script src="JavaScript/scripts.js"></script>

    </body>

</html>