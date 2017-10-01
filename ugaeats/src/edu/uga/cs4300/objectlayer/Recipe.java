package edu.uga.cs4300.objectlayer;

import java.sql.Blob;
import java.util.List;

public class Recipe {
	private int id;
	private String name;
	private String permissions;
	private float rank;
	private int num_ratings;
	private int users_id;
	private Blob image;
	private String image64;
	private List<String> ingredients;
	private List<String> instructions;
	private String username;
	
	//Constructor
	public Recipe(int id, String name, String permission, float rank, int num_ratings,
			 int users_id, Blob image, List<String> ingredients, List<String> instructions) {
		this.setId(id);
		this.setName(name);
		this.setPermissions(permission);
		this.setRank(rank);
		this.setNum_ratings(num_ratings);
		this.setUsers_id(users_id);
		this.setImage(image);
		this.setIngredients(ingredients);
		this.setInstructions(instructions);
	}
	
	//Constructor
	public Recipe(int id, String name, float rank, String username, Blob image, int user_id) {
		this.setId(id);
		this.setName(name);
		this.setRank(rank);
		this.setUsername(username);
		this.setImage(image);
		this.setUsers_id(user_id);
	}

	//Constructor
	public Recipe(int id2, String name2, float rank2, String username2, String image642, int user_id) {
		this.setId(id2);
		this.setName(name2);
		this.setRank(rank2);
		this.setUsername(username2);
		this.setImage64(image642);
		this.setUsers_id(user_id);
	}
	
	//Constructor
	public Recipe(int id2, String name2, float rank2, String username2, String image642, int user_id, List<String> ingredients, List<String> 
	instructions) {
		this.setId(id2);
		this.setName(name2);
		this.setRank(rank2);
		this.setUsername(username2);
		this.setImage64(image642);
		this.setUsers_id(user_id);
		this.setIngredients(ingredients);
		this.setInstructions(instructions);
	}

	//Constructor
	public Recipe(int id2, String recipeName, float rank2, int numRating, List<String> ingredients2,
			List<String> instructions2, String image642) {
		this.setId(id2);
		this.setName(recipeName);
		this.setRank(rank2);
		this.setNum_ratings(numRating);
		this.setIngredients(ingredients2);
		this.setInstructions(instructions2);
		this.setImage64(image642);
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the permissions
	 */
	public String getPermissions() {
		return permissions;
	}

	/**
	 * @param permissions the permissions to set
	 */
	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	/**
	 * @return the rank
	 */
	public float getRank() {
		return rank;
	}

	/**
	 * @param rank the rank to set
	 */
	public void setRank(float rank) {
		this.rank = rank;
	}

	/**
	 * @return the num_ratings
	 */
	public int getNum_ratings() {
		return num_ratings;
	}

	/**
	 * @param num_ratings the num_ratings to set
	 */
	public void setNum_ratings(int num_ratings) {
		this.num_ratings = num_ratings;
	}

	/**
	 * @return the users_id
	 */
	public int getUsers_id() {
		return users_id;
	}

	/**
	 * @param users_id the users_id to set
	 */
	public void setUsers_id(int users_id) {
		this.users_id = users_id;
	}

	/**
	 * @return the image
	 */
	public Blob getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(Blob image) {
		this.image = image;
	}

	/**
	 * @return the image64
	 */
	public String getImage64() {
		return image64;
	}

	/**
	 * @param image64 the image64 to set
	 */
	public void setImage64(String image64) {
		this.image64 = image64;
	}

	/**
	 * @return the ingredients
	 */
	public List<String> getIngredients() {
		return ingredients;
	}

	/**
	 * @param ingredients the ingredients to set
	 */
	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}

	/**
	 * @return the instructions
	 */
	public List<String> getInstructions() {
		return instructions;
	}

	/**
	 * @param instructions the instructions to set
	 */
	public void setInstructions(List<String> instructions) {
		this.instructions = instructions;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
}
