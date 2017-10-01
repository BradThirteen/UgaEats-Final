<!DOCTYPE html> 
<html>
<head>
<meta charset="ISO-8859-1">
<title>UGA Eats</title>
<script src="JS/jquery.js" type="text/javascript"></script>
<script src="JS/friends.js" type="text/javascript"></script>
<link rel="stylesheet" href="CSS/bootstrap.min.css" type="text/css">
<link href="CSS/index.css" type="text/css" rel="stylesheet"/>
</head>
<body>
	<div class="topDiv">
		<form class="formHome" action="RecipeServlet" method="get">
			<div class="buttons">
			<button class="button" name="home">Home</button>
			<button class="button" name="viewRecipe">View Recipes</button>
			<button class="button" name="viewProfile">View Profile</button>
			<button class="button" name="friends">Friends</button>
			</div>
			<#if checklogin == 0>
			<div class="sign">
			<button class="button right" name="openSigninPage">Sign-In</button>
			<button class="button right" name="openSignupPage">Sign-Up</button>
			</div>
			<#elseif checklogin == 1>
			<div class="sign">
			<p class="right label">${fname}</p>
			<button class="button" name="logout">Logout</button>
			</div>
			</#if>
			<img class="logo" src="https://pbs.twimg.com/profile_images/378800000274109762/d435f305c1c6737cf29659aa6592537c.jpeg">
		</form>
	</div>
	<br />
	<br />
	
	<div class="container">
		<div class="row">
			<div class="col" id="Friends">
				<h2>Your Friends</h2>
				<#list friends as friend>
					<div>
						<img height="100" width="100" src="data:image/jpeg;base64,${friend.getImage64()}">
						 <p>${friend.getFirst_name()} ${friend.getLast_name()}</p>
					</div><br />
				</#list>
			</div>
			<div class="col">
				<h2>Your Friend Requests</h2>
				<#list requests as request>
					<div>
						<img margin-bottom="5px" height="100" width="100" src="data:image/jpeg;base64,${request.getImage64()}">
						<p>${request.getFirst_name()} ${request.getLast_name()}</p>
						<button class="button" onClick="acceptRequest(${request.getId()})">Accept Request</button>
						<button class="button" onClick="rejectRequest(${request.getId()})">Delete Request</button>
					</div><br />
				</#list>
			</div>
			<div class="col">
				<h2>People You May Know?</h2>
				<#list users as user>
					<div>
						<img height="100" width="100" src="data:image/jpeg;base64,${user.getImage64()}">
						<p>${user.getFirst_name()} ${user.getLast_name()}</p>
						<button class="button" onClick="addFriend(${user.getId()})">Add Friend</button>
					</div><br />
				</#list>
			</div>
		</div>
	</div>
</body>
</html>