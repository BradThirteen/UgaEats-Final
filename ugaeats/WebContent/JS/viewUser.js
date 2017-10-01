$(document).ready(function checkme() {
	var id = $("#userId").val();
	$.ajax({
		type: "POST",
		url: "RecipeServlet",
		data: {
			getUserRecipes : "getRecipe",
			userId : id
		},
		dataType : "json",
		async: "false",
		success: function(responseText) {
			$("#recipeList").empty();
			$.each(responseText, function(key, value) {
				var div = $('<div class="parent"></div>');
				var leftDiv = $('<div class="infoDiv"></div>');
				var rightDiv = $('<div class="infoDiv"></div>');
				var image = $('<img class="testimage"/>');
				var para = $('<button type="submit" class="user" value="' + value.users_id + '">' + value.username + '</button>');
				para.attr('name', "otherUser");
				var rank = $('<p>' + value.rank + '/5</p>');
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
				$("#recipeList").append(div);
			});
		}
	});
	
	$(document).on('click', '.viewRecipe', function() {
		$("#recipeList").hide();
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
				
				var favorite = $('<button type="button" class="favorite" value="' + id + '">Favorite</button>');
				
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
				
				var select = $('<form class="formHome" action="RecipeServlet" method="post">' +
						'<select name="rank" id="ranking" class="text">' + 
						'<option selected="selected" disabled="disabled">Select a ranking</option>' +
		    				'<option value="1">1</option>' +
		    				'<option value="2">2</option>'+
		  			  	'<option value="3">3</option>'+
		  			 	 '<option value="4">4</option>'+
		  			 	   '<option value="5">5</option> </select>' +
		  	  			'<button class="button2" type="button" name="ratingButton" value="' + id + '">Rate!</button></form>');
				
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
				div.append(favorite);
				div.append(select);
				$("#recipeView").append(div);
			}
		});
	});
	
	$(document).on('click', '.button2', function() {
		var i = $(".button2").val();
		var r = $("#ranking").val();
		$.ajax({
			type: "POST",
			url: "RecipeServlet",
			data: {
				rank: r,
				id: i,
				rankRecipe: "rankRecipe"
			},
			success : function () {
				alert("You have ranked this recipe!");
			}
		});
	});

	$(document).on('click', '.favorite', function() {
		var recipeId = $(this).attr('value');
		$.ajax({
			type: "POST",
			url: "RecipeServlet",
			data: {
				favoriteRecipe : "Favorite",
				id : recipeId
			},
			dataType : "text",
			success : function(text) {
				if (text == "nosession")
					{
					alert("You must be logged in to favorite recipes.");
					}
				else if (text == "alreadyFavorited")
					{
					alert("This recipe is already in your favorites.");
					}
				else if (text == "favorited")
					{
					alert("This recipe has been added to your favorites.");
					}
				else if (text == "failed")
					{
					alert("Failed to add this recipe to your favorites.")
					}
			}
		});
	});
	
	$(document).on('click', '#back', function() {
		$('#recipeView').empty();
		$('#recipeList').show();
	});
	
});