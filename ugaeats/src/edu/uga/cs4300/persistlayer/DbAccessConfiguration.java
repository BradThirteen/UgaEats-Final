package edu.uga.cs4300.persistlayer;

/* Bradley Reeves
 * Section 26666
 * 
 * Contains the information that is needed to connect to the database
 * with DbAccessImpl.
 * 
 */

public abstract class DbAccessConfiguration {
	
	protected static final String DB_DRIVER_NAME = "com.mysql.jdbc.Driver";
	
	protected static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/recipedb";
	
	protected static final String DB_CONNECTION_USERNAME = "root";
	
	protected static final String DB_CONNECTION_PASSWORD = "Copper13!";

}
