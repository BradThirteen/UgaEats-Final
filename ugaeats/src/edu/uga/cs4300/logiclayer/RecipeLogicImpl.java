package edu.uga.cs4300.logiclayer;


import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.uga.cs4300.objectlayer.Recipe;
import edu.uga.cs4300.objectlayer.User;
import edu.uga.cs4300.persistlayer.RecipePersistImpl;

public class RecipeLogicImpl {
	RecipePersistImpl userPersist;
	
	// Constructor for RecipeLogicImpl
	public RecipeLogicImpl() {
		userPersist = new RecipePersistImpl();
	}
	 
	// Validates the login credentials 
	// Returns a user object if user exists. Else, returns null
	public User validateLogin(String username, String password) {
		return userPersist.checkUser(username, password);
	}

	// Creates a recipe with name, ingredients, instructions, and permissions (public or private)
	// Adds recipe to the database
	public int createRecipe(String recipeName, List<String> ingredients, List<String> instructions, String permission,
		int user_id, InputStream fileContent, String category) {
		return userPersist.addRecipeToDatabase(recipeName, ingredients, instructions, permission, user_id, fileContent, category);
	}

	// Returns a list of the popular recipes 
	public List<Recipe> getPopRecipes() {
		return userPersist.getPopularRecipes();
	}

	// returns a Recipe object with a specific id to display information on the html page specific to that recipe
	public Recipe getRecipeById(int id) {
		return userPersist.getRecipe(id);
	}

	// Adds user to data base
	// Returns an integer 
	public int sendNewUserInfo(String fname, String lname, String uname, String password, String email, InputStream fileContent) {
		User u = new User(fname, lname, uname, password, email);
		return userPersist.persistUserProfile(u, fileContent);
	}
	
	// Returns a list of recipes that belong to a certain userID
	public ArrayList<Recipe> getMyRecipes(int userID) {
		return userPersist.persistMyRecipes(userID);
	}
	
	//Calls persist layer to sort recipes by category and display them.
	public List<Recipe> viewByCategory(String category) {
		return userPersist.viewByCategory(category);
	}
	
	//Calls persist layer to search based on user input  of category and search term.
	public List<Recipe> Search(String category, String term) {
		return userPersist.Search(category, term);
	}

	// Returns a list of recipes that belong to a certain userID
	public List<Recipe> getUserRecipes(int id) {
		return userPersist.getRecipesByUserId(id);
	}

	// Returns a User object of a user with a specific id
	public User getUserInfo(int id) {
		return userPersist.getInfoUser(id);
	}

	// Checks to see if a certain recipe is already in the favorites database for a certain user
	public Boolean checkFavorites(int recipeId, int userId) {
		return userPersist.checkFavoriteRecipes(recipeId, userId);
	}

	// Adds a specific recipe to a specific user's favorites table
	public int favoriteRecipe(int recipeId, int userId) {
		return userPersist.addToFavorites(recipeId, userId);
	}
	
	// Returns a User object with a specific ID
	public User getUserByID(int userID) {
		return userPersist.getUserByID(userID);
	}
	
	// Returns a list of all the favorite recipes for a specific user
	public ArrayList<Recipe> getFavoriteRecipes (int userID) {
		return userPersist.getFavoriteRecipes(userID);
	}
	
	// Returns a list of all the recipes shared with a specific user
	public ArrayList<Recipe> getSharedRecipes (int userID) {
		return userPersist.getSharedRecipes(userID);
	}
	
	// returns a list of user objects for all the friends for a specific user
	public List<User> getFriends(int id) {
		return userPersist.getFriends(id);
	}

	// Returns a list of all the users in the user database
	public List<User> getUsers(int id) {
		return userPersist.getUsers(id);
	}
	
	// Adds a friend to a certain user
	// Returns a string that determines a successful/unsuccessful addition
	public String addFriend(String friendId, int userId) throws NumberFormatException, SQLException {
		return userPersist.addFriend(friendId, userId);
	}
	
	//  Returns a list of all the friend requests for a certain user
	public List<User> getFriendRequests(int id) {
		return userPersist.getFriendRequests(id);
	}
	
	// Accepts a friend request and populates the friendslist database to make two users friends
	public String acceptRequest(int userId, String friendId) {
		return userPersist.acceptRequest(userId, friendId);
	}
	
	// Rejects a friend request and removes is from the friendrequests table in the database
	public String rejectRequest(int userId, String friendId) {
		return userPersist.rejectRequest(userId, friendId);
	}

	//Logic layer to implement a ranking system for recipes
	//Calls persist layer
	public int Ranked(int id, int rank) {
		return userPersist.UpdateRank(id, rank);
	}
	
	//Logic layer to check for duplicate username
	//Calls persist layer
	public boolean LogicCheckUser(String username) {
		return userPersist.PersistCheckUser(username);
	}
	
	// Share a specific recipe with a specific user
	public void shareRecipe(int recipeID, int myID, int shareID) {
		userPersist.shareRecipe(recipeID, myID, shareID);
	}
	
}
