<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <script src="JS/jquery.js" type="text/javascript"></script>
  <link href="CSS/sign.css" type="text/css" rel="stylesheet"/>
  <title>Sign Up</title>
</head>
<body>
	<div class="topDiv">
		<form class="formHome" action="RecipeServlet" method="get">
			<div class="buttons">
				<button class="button" name="home">Home</button>
     			<button class="button right" name="openSignupPage">Sign-Up</button>
			</div>
			<img class="logo" src="https://pbs.twimg.com/profile_images/378800000274109762/d435f305c1c6737cf29659aa6592537c.jpeg">
		</form>
	</div>
  <div class="center">
  <div class="well">
   <form action="RecipeServlet" method="post" id="loginForm" name="loginForm">
      <span class="text">Username:</span> <input id="username" class="text input" type="text" name="username" maxlength="45" required/><label>Incorrect username and/or password</label><br>
      <span class="text">Password:</span> <input id="password" class="text input" type="password" name="password" maxlength="45" required/><br>
      <input id="loginButton" class="button" type="submit" value="Sign in" name="signin"/>
  	</form>
  	</div>
  </div>
</body>
</html>