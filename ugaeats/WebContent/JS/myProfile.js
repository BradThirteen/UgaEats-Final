function openRecipes(evt, recipe) {
	$("#recipeViewer").empty();
	$("#recipeHolder").show();
	$("#favoriteHolder").show();
	$("#sharedHolder").show();
    // Declare all variables
    var i, tabcontent, tablinks;

    // Get all elements with class="tabcontent" and hide them
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }

    // Get all elements with class="tablinks" and remove the class "active"
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }

    // Show the current tab, and add an "active" class to the button that opened the tab
    document.getElementById(recipe).style.display = "block";
    evt.currentTarget.className += " active";
}

//Load myRecipes
$(document).ready(function loadPopular() {
	$.ajax({
		type: "POST",
		url: "RecipeServlet",
	data: {
		myRecipes : "recipes"
	}, dataType: "json",
	async:"false",
	success: function(responseText) {
		$.each(responseText, function(key, value) {
			var div = $('<div class="parent"></div>');
			var rightDiv = $('<div class="infoDiv"></div>');
			var leftDiv = $('<div class="infoDiv"></div>');
			var image = $('<img class="testimage"/>');
			var para = $('<button type="submit" class="user" value="' + value.users_id + '">' + value.username + '</button>');
			para.attr('name', "otherUser");
			var rank = $('<p>Rank: ' + value.rank + '/5</p>');
			image.attr('src', "data:image/jpeg;base64," + value.image64);
			var link = $('<input type="button" class="viewRecipe"/>')
			link.attr('value', value.name);
			link.attr('name', value.id);
			leftDiv.append(image);
			rightDiv.append(link);
			rightDiv.append(para);
			rightDiv.append(rank);
			div.append(leftDiv);
			div.append(rightDiv);
			$("#recipeHolder").append(div);
		});
	}
});
	
	//Load favorite recipes
	$(document).ready( function() {
		$.ajax({
			type: "POST",
			url: "RecipeServlet",
		data: {
			favoriteRecipes : "recipes"
		}, dataType: "json",
		async:"false",
		success: function(responseText)  {
			$.each(responseText, function(key, value) {
				var div = $('<div class="parent"></div>');
				var rightDiv = $('<div class="infoDiv"></div>');
				var leftDiv = $('<div class="infoDiv"></div>');
				var image = $('<img class="testimage"/>');
				var para = $('<button type="submit" class="user" value="' + value.users_id + '">' + value.username + '</button>');
				para.attr('name', "otherUser");
				var rank = $('<p>Rank: ' + value.rank + '/5</p>');
				image.attr('src', "data:image/jpeg;base64," + value.image64);
				var link = $('<input type="button" class="viewRecipe2"/>')
				link.attr('value', value.name);
				link.attr('name', value.id);
				leftDiv.append(image);
				rightDiv.append(link);
				rightDiv.append(para);
				rightDiv.append(rank);
				div.append(leftDiv);
				div.append(rightDiv);
				$("#favoriteHolder").append(div);
			});
			}
		});
	});
	
	//Load shared recipes
	$(document).ready( function() {
		$.ajax({
			type: "POST",
			url: "RecipeServlet",
		data: {
			sharedRecipes : "recipes"
		}, dataType: "json",
		async:"false",
		success: function(responseText)  {
			$.each(responseText, function(key, value) {
				var div = $('<div class="parent"></div>');
				var rightDiv = $('<div class="infoDiv"></div>');
				var leftDiv = $('<div class="infoDiv"></div>');
				var image = $('<img class="testimage"/>');
				var para = $('<p>' + value.username + '</p>');
				var rank = $('<p>Rank: ' + value.rank + '</p>');
				image.attr('src', "data:image/jpeg;base64," + value.image64);
				var link = $('<input type="button" class="viewRecipe3"/>')
				link.attr('value', value.name);
				link.attr('name', value.id);
				leftDiv.append(image);
				rightDiv.append(link);
				rightDiv.append(para);
				rightDiv.append(rank);
				div.append(leftDiv);
				div.append(rightDiv);
				$("#sharedHolder").append(div);
			});
			}
		});
	});
	
	//Load list of friends
    $.ajax({
        type: "POST",
        url: "RecipeServlet",
    data: {
        getMyFriends : "friends"
    }, dataType: "json",
    async:"false",
    success: function(responseText)  {
        $.each(responseText, function(key, value) {
            var div = $('<div class="friendObj"></div>');
            var divInside = $('<div display="inline"></div>')
            var input = $('<input type="checkbox" class="check">');
            input.attr('name', value.id);
            var username = $('<p>' + value.first_name + '</p>');
            var image = $('<img class="testimage"/>');
            image.attr('src', "data:image/jpeg;base64," + value.image64);
            divInside.append(image);
            divInside.append(input);
            div.append(divInside);
            div.append(username);
            $("#friends").append(div);
            $(".check").hide();
        });
        }
    });

	$(document).on('click', '.viewRecipe2', function() {
		$("#favoriteHolder").hide();
		$(".check").show();
		var id = $(this).attr('name');
		$.ajax({
			type: "POST",
			url: "RecipeServlet",
			data: {
				"loadRecipe" : "load",
				"id" : id
			}, dataType: "json",
			success: function (responseText) {
				var div = $('<div></div>');
				
				var button = $('<input type="button" id="back2" value="Back"/>');
				
				//Sharebutton
				var shareButton = $('<button type="button" id="share" value="' +id+ '" > Share Recipe </button>');
				
				// Gets the image and creates an image tag.
				var image = $('<img class="testimage"/>');
				image.attr('src', "data:image/jpeg;base64," + responseText.image64);
				
				// Gets all of the ingredients into an unordered list.
				
				var ingredientList = $('<ul></ul>');
				$.each(responseText.ingredients, function(index, value) {
					var ingredient = $('<li>' + value + '</li>');
					ingredientList.append(ingredient);
				});
				
				// Gets all of the instructions into an unordered list.
				
				var instructionList = $('<ol></ol>');
				$.each(responseText.instructions, function(index, value) {
					var instruction = $('<li>' + value + '</li>');
					instructionList.append(instruction);
				});
				
				var recipeName = $('<h2>' + responseText.name + '</h2>');
				var header1 = $('<h3>Ingredients</h3>');
				var header2 = $('<h3>Instructions</h3>');
				
				div.append(image);
				div.append(recipeName);
				div.append(header1);
				div.append(ingredientList);
				div.append(header2);
				div.append(instructionList);
				div.append(button);
				div.append(shareButton);
				$("#recipeViewer").append(div);
				$("#recipeViewer").show();
			}
		});
	});

$(document).on('click', '.viewRecipe', function() {
	$("#recipeHolder").hide();
	$(".check").show();
	var id = $(this).attr('name');
	$.ajax({
		type: "POST",
		url: "RecipeServlet",
		data: {
			"loadRecipe" : "load",
			"id" : id
		}, dataType: "json",
		success: function (responseText) {
			var div = $('<div></div>');
			
			var button = $('<input type="button" id="back" value="Back"/>');
			
			//Sharebutton
			var shareButton = $('<button type="button" id="share" value="' +id+ '" > Share Recipe </button>');
			
			// Gets the image and creates an image tag.
			var image = $('<img class="testimage"/>');
			image.attr('src', "data:image/jpeg;base64," + responseText.image64);
			
			// Gets all of the ingredients into an unordered list.
			
			var ingredientList = $('<ul></ul>');
			$.each(responseText.ingredients, function(index, value) {
				var ingredient = $('<li>' + value + '</li>');
				ingredientList.append(ingredient);
			});
			
			// Gets all of the instructions into an unordered list.
			
			var instructionList = $('<ol></ol>');
			$.each(responseText.instructions, function(index, value) {
				var instruction = $('<li>' + value + '</li>');
				instructionList.append(instruction);
			});
			
			var recipeName = $('<h2>' + responseText.name + '</h2>');
			var header1 = $('<h3>Ingredients</h3>');
			var header2 = $('<h3>Instructions</h3>');
			
			div.append(image);
			div.append(recipeName);
			div.append(header1);
			div.append(ingredientList);
			div.append(header2);
			div.append(instructionList);
			div.append(button);
			div.append(shareButton);
			$("#recipeViewer").append(div);
		}
	});
});

// View info for shared recipes.

$(document).on('click', '.viewRecipe3', function() {
	$("#sharedHolder").hide();
	$(".check").show();
	var id = $(this).attr('name');
	$.ajax({
		type: "POST",
		url: "RecipeServlet",
		data: {
			"loadRecipe" : "load",
			"id" : id
		}, dataType: "json",
		success: function (responseText) {
			var div = $('<div></div>');
			
			var button = $('<input type="button" id="back3" value="Back"/>');
			
			//Sharebutton
			var shareButton = $('<button type="button" id="share" value="' +id+ '" > Share Recipe </button>');
			
			// Gets the image and creates an image tag.
			var image = $('<img class="testimage"/>');
			image.attr('src', "data:image/jpeg;base64," + responseText.image64);
			
			// Gets all of the ingredients into an unordered list.
			
			var ingredientList = $('<ul></ul>');
			$.each(responseText.ingredients, function(index, value) {
				var ingredient = $('<li>' + value + '</li>');
				ingredientList.append(ingredient);
			});
			
			// Gets all of the instructions into an unordered list.
			
			var instructionList = $('<ol></ol>');
			$.each(responseText.instructions, function(index, value) {
				var instruction = $('<li>' + value + '</li>');
				instructionList.append(instruction);
			});
			
			var recipeName = $('<h2>' + responseText.name + '</h2>');
			var header1 = $('<h3>Ingredients</h3>');
			var header2 = $('<h3>Instructions</h3>');
			
			div.append(image);
			div.append(recipeName);
			div.append(header1);
			div.append(ingredientList);
			div.append(header2);
			div.append(instructionList);
			div.append(button);
			div.append(shareButton);
			$("#recipeViewer").append(div);
		}
	});
});


//Share recipe with selected user
$(document).on('click', '#share', function() {
	
	//id of the recipe to be shared
	var shareRecipeID = $('#share').attr('value');
	
	//Array to store IDs of friends that you are sharing with 
	var friendIDs = [];
	$('#friends input:checked').each(function(){
		
		friendIDs.push(this.name);
	});
	
	alert("Recipe has been shared");
	
	$.ajax({
		url:"RecipeServlet",
		type:"POST",
		dataType: "json",
		data: {friendIDs:friendIDs,
			   shareRecipeID:shareRecipeID},
		success:function(data)
		{
			
		},
	});//ajax
	
	
});


// Returns back to the displayed list of user's recipes.
$(document).on('click', '#back', function() {
	$('#recipeViewer').empty();
	$('#recipeHolder').show();
	$(".check").hide();
});

//Returns back to the displayed list of favorite recipes.
$(document).on('click', '#back2', function() {
	$('#recipeViewer').empty();
	$('#favoriteHolder').show();
	$(".check").hide();
});

//Returns back to the displayed list of shared recipes.
$(document).on('click', '#back3', function() {
	$('#recipeViewer').empty();
	$('#sharedHolder').show();
	$(".check").hide();
});

});