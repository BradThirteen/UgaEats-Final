<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create a Recipe</title>
<script src="JS/jquery.js" type="text/javascript"></script>
<script src="JS/recipe.js" type="text/javascript"></script>
<script src="JS/recipe2.js" type="text/javascript"></script>
<link href="CSS/sign.css" type="text/css" rel="stylesheet"/>
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
	<div class="center">
		<div class="well">
		<form class="formHome" action="RecipeServlet" method="post" enctype="multipart/form-data">
			<span class="text">Enter a name for your recipe:</span>  <br />
			  <input class="text input" type="text" name="recipename" maxlength="120" required/>
			  <br />
			  <span class="text">Select a category for your recipe:</span> <br />
				  <select name="category" class="text" required>
    				<option value="Italian" selected="selected">Italian</option>
    				<option value="American">American</option>
  			  	<option value="Mexican">Mexican</option>
  			 	 <option value="Vegetarian">Vegetarian</option>
  			 	   <option value="Other">Other</option>
 				 </select>
 				 <br />
			<span class="text">Submit an image for your recipe:</span> <br />
  			<input type="file" name="pic" accept="image/*" required/>
  			<br />
  			<div id="ingredients">
  			<span class="text">List the ingredients for your recipe:</span> <br />
  			  - <input class="text input steps" type="text" name="ingredients" maxlength="45" required/><br />
  			  </div>
  			  <button id="addIngredient" class="button" type="button">Add Ingredient</button>
  			  <div id="steps">
  			  <span class="text">List the steps for your recipe:</span> <br />
  			    - <input class="input text steps" type="text" name="steps" required/><br />
  			    </div>
  			    <button id="addStep" class="button" type="button">Add Step</button>
  			<br />
  			<span class="text">Please choose the visibility for your recipe:</span> <br />
  			  <input class="text input" type="radio" name="visibility" value="public" checked="checked"><span class="text"/>Public</span>
			  <input class="text input" type="radio" name="visibility" value="private"><span class="text"/>Private</span>
			<br />
  			
  			<input class="button" type="submit" name="createrecipe">
		</form>
		</div>
	</div>
</body>
</html>