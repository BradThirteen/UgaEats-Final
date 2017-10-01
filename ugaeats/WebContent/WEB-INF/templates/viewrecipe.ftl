<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>UGA Eats</title>
<script src="JS/jquery.js" type="text/javascript"></script>
<script src="JS/viewrecipe.js" type="text/javascript"></script>
<link href="CSS/index.css" type="text/css" rel="stylesheet"/>
</head>
<body>
	<div>
		<form class="formHome" action="RecipeServlet" method="get">
		<div class="topDiv">
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
			<label class="right label">${fname}</label>
			<button class="button" name="logout">Logout</button>
			</div>
			</#if>
			<img class="logo" src="https://pbs.twimg.com/profile_images/378800000274109762/d435f305c1c6737cf29659aa6592537c.jpeg">
		</div>
		<div class="center">
		<div class="searchDiv">
			<input id="term" class="text input" type="text" name="searchTerm"/>
			<select name="category" id="selectBox" class="text">
			    <option value="All" class="selected">All</option>
    			<option value="Italian" class="selected">Italian</option>
    			<option value="American" class="selected">American</option>
  				<option value="Mexican" class="selected">Mexican</option>
  				<option value="Vegetarian" class="selected">Vegetarian</option>
  		 		<option value="Other" class="selected">Other</option>
 			</select>
			<input id="search" class="button" type="button" value="Search" name="searchButton"/>
			<input class="button" type="submit" value="Create Recipe" name="createButton"/>
			<div id="recipeList"></div>
			<div id="recipeView"></div>
		</div>
		</div>
		</form>
	</div>
</body>
</html>