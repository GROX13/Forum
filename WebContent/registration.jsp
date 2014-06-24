<%@ page language="java" contentType="text/html; charset = UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Register</title>
		
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
		
		<link rel="stylesheet" href="CSS/css/demo.css">
		<link rel="stylesheet" href="CSS/css/sky-forms.css">
		<link rel = "icon" href = "Icons/Wineass_W.ico" type = "icon">
		
		<script src="JavaScript/datesetup.js"></script>	
	</head>
		<body class="bg-red">
		<div class="body body-s">
		
			<form action = "HandleRegistration" method = "post" class="sky-form">
				<header>Registration form</header>
				
				<fieldset>					
					<section>
						<label class="input">
							<i class="icon-append icon-user"></i>
							<input type="text" placeholder="Username" id="username" name="username" required>
							<b class="tooltip tooltip-bottom-right">Only latin characters and numbers</b>
						</label>
					</section>
					
					<section>
						<label class="input">
							<i class="icon-append icon-user"></i>
							<input type="text" placeholder="Signiture" id="signiture" name="signiture" required>
							<b class="tooltip tooltip-bottom-right">Only latin characters and numbers</b>
						</label>
					</section>
					
					<section>
						<label class="input">
							<i class="icon-append icon-envelope-alt"></i>
							<input type="text" placeholder="Email address" id="email" name="email" required>
							<b class="tooltip tooltip-bottom-right">Needed to verify your account</b>
						</label>
					</section>
					
					<section>
						<label class="input">
							<i class="icon-append icon-lock"></i>
							<input type="password" placeholder="Password" id = "password" name = "password" required>
							<b class="tooltip tooltip-bottom-right">Only latin characters and numbers</b>
						</label>
					</section>
				</fieldset>
					
				<fieldset>
					<div class="row">
						<section class="col col-6">
							<label class="input">
								<input type="text" placeholder="First name" id="firstname" name="firstname">
							</label>
						</section>
						<section class="col col-6">
							<label class="input">
								<input type="text" placeholder="Last name" id="lastname" name="lastname">
							</label>
						</section>
					</div>
					
					<section>
						<label class="select">
							<select id="gender" name="gender">
								<option value="0" selected disabled>Gender</option>
								<option value="male">Male</option>
								<option value="female">Female</option>
								<option value="other">Other</option>
							</select>
							<i></i>
						</label>
					</section>
					
					<div class = "row">
						<section class="col col-4">
							<label class="select" for="birth"> Birth Day:
								<select id="daydropdown"  name = "daydropdown"></select>
								<script> 
				 					populatedropdown("daydropdown", "monthdropdown", "yeardropdown");
								</script>
							</label>	
						</section> 
					
						<section class="col col-4">
							<label class="select" for="birth"> Birth Month:
								<select id="monthdropdown" name = "monthdropdown"></select>
								<script> 
				 					populatedropdown("daydropdown", "monthdropdown", "yeardropdown");
								</script>
							</label>	
						</section> 
						
						<section class="col col-4">
							<label class="select" for="birth"> Birth Year:
								<select id="yeardropdown" name = "yeardropdown"></select> 
								<script> 
				 					populatedropdown("daydropdown", "monthdropdown", "yeardropdown");
								</script>
							</label>	
						</section> 		
					</div>
					
					<section>
						<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>I agree to the Terms of Service</label>
					</section>
				</fieldset>
				<footer>
					<button type="submit" class="button">Submit</button>
				</footer>
			</form>
			
		</div>
	</body>
</html>