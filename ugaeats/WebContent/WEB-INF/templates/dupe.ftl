<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <link href="CSS/sign.css" type="text/css" rel="stylesheet"/>
  <script src="JS/recipe.js" type="text/javascript"></script>
  <title>Sign Up</title>
</head>
<body>
	<div class="topDiv">
		<div class="buttons">
			<form class="formHome" action="RecipeServlet" method="get">
				<button class="button" name="home">Home</button>
				<button class="button right" name="openSigninPage">Sign-In</button>
			</form>
		</div>
	<img class="logo" src="https://pbs.twimg.com/profile_images/378800000274109762/d435f305c1c6737cf29659aa6592537c.jpeg">
	</div>
  <div class="center">
  <div class="well well2">
    <form action="UserServlet" method="post" enctype="multipart/form-data">
      <span class="text">First Name:</span> <input class="text input" type="text" name="firstName" maxlength="45" required/><br>
      <span class="text">Last Name:</span> <input class="text input" type="text" name="lastName" maxlength="45" required/><br>
      <span class="text">Username:</span> <input class="text input" id="user" type="text" name="username" maxlength="45" required/><br>
      <span class="text">Password:</span> <input class="text input" type="password" name="password" maxlength="45" required/><br>
      <span class="text">Email:</span> <input class="text input" type="text" name="email" maxlength="45" required/><br>
      <span class="text">Submit an image for your profile:</span> <br />
  			<input type="file" name="pic" accept="image/*" required>
      <input class="button" type="submit" value="Sign up" name="signup"/>
      <#if dupe>
      <br />
      <span class="text">Error: Username already exists.</span>
      </#if>
    </form>
    </div>
  </div>
</body>
</html>
