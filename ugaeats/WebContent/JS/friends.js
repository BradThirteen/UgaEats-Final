// Add Friend

function addFriend(id) {
	$.ajax({
		url: "RecipeServlet",
		method: "POST",
		data: {
			"id": id,
			"friendRequest": "sendRequest"
		},
		dataType:"text",
		success: function(responseText) {
			if(responseText == "true") {
				alert("Friend Request Has Already\n Been Sent To This User!")
			} else {
				alert("Your Friend Request Has Been Sent!");
			}
		},
	});
}

// Reject request.

function rejectRequest(id) {
	$.ajax({
		url: "RecipeServlet",
		method: "POST",
		data: {
			"id": id,
			"rejectRequest": "rejectRequest"
		},
		dataType:"text",
		success: function(responseText) {
			if(responseText == "rejected") {
				window.location.reload(true);
			}
		},
	});
}

// Accepts request.

function acceptRequest(id) {
	$.ajax({
		url: "RecipeServlet",
		method: "POST",
		data: {
			"id": id,
			"acceptRequest": "acceptRequest"
		},
		dataType:"text",
		success: function(responseText) {
			if(responseText == "added") {
				window.location.reload(true);
			}
		},
	});
}