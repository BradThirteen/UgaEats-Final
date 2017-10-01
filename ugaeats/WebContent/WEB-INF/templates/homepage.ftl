<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>UGA Eats</title>
<script src="JS/jquery.js" type="text/javascript"></script>
<script src="JS/recipe.js" type="text/javascript"></script>
<link href="CSS/index.css" type="text/css" rel="stylesheet"/>
</head>
<body>
	<div class="topDiv">
		<form class="formHome" action="RecipeServlet" method="get">
			<div class="buttons">
			<button class="left button" name="home">Home</button>
			<button class="left button" name="viewRecipe">View Recipes</button>
			<button class="left button" name="viewProfile">View Profile</button>
			<button class="button" name="friends">Friends</button>	
			</div>
			<#if checklogin == 0>
			<button class="button right" name="openSigninPage">Sign-In</button>
			<button class="button right" name="openSignupPage">Sign-Up</button>
			<#elseif checklogin == 1>
			<div class="sign">
			<p class="right label">${fname}</p>
			<button class="button" name="logout">Logout</button>
			</div>
			</#if>
			<img class="logo" src="https://pbs.twimg.com/profile_images/378800000274109762/d435f305c1c6737cf29659aa6592537c.jpeg">
		</form>
	</div>
	<form class="recipeListForm" action="RecipeServlet" method="get">
		<div id="recipeHolder"></div>
		<div id="recipeViewer"></div>
	</form>
</body>
</html>