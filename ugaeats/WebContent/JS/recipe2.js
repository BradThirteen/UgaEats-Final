$(document).ready(function(){
    
    $("#addIngredient").click(function(){
    	var div = $('<div></div>');
        var input = $('<input type="text" class="input text steps" name="ingredients" required/>');
        div.append(input);
        div.append('<button type="button" class="delete">Delete</button>');
    	$("#ingredients").append(div);
    });
    
    $("#addStep").click(function(){
    	var div = $('<div></div>');
        var input = $('<input type="text" class="input text steps" name="steps" required/>');
        div.append(input);
        div.append('<button type="button" class="delete">Delete</button>');
    	$("#steps").append(div);
    });
    
    $(document).on('click', '.delete', function(){
    	$(this).parent().remove();
    });
});