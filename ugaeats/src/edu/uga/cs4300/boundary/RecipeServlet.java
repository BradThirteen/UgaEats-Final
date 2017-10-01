package edu.uga.cs4300.boundary;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;

import edu.uga.cs4300.logiclayer.RecipeLogicImpl;
import edu.uga.cs4300.objectlayer.Recipe;
import edu.uga.cs4300.objectlayer.User;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;

/*	Brandon Lee, Bradley Reeves, Lakshay Sharma, James O'Boyle
 * 	Section 26666
 * 
 * 	Main Servlet used for pretty much everything (Ajax, Forms, ...etc).
 */

/**
 * Servlet implementation class RecipeServlet
 */
@WebServlet("/RecipeServlet")
public class RecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String templateDir = "/WEB-INF/templates";
	
	private TemplateProcessor process;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecipeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		// Creates template processor
		
		process = new TemplateProcessor(templateDir, getServletContext());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Checks to see if sign in button is clicked.
		
		String signin = request.getParameter("signin");
		
		// Checks to see if home button is clicked.
		
		String home = request.getParameter("home");
		
		// Checks to see if viewRecipe button is clicked.
		
		String viewRecipes = request.getParameter("viewRecipe");
		
		// Checks to see if createRecipe button is clicked.
		
		String createRecipe = request.getParameter("createButton");
		
		// Checks to see if the ajax call to load popular recipes is made.
		
		String loadPopularRecipes = request.getParameter("loadPopular");
		
		// Checks to see if the ajax call to view the information of a recipe
		
		String viewRecipe = request.getParameter("loadRecipe");
		
		// Checks to see if the ajax call to get the user's favorite recipes
		
		String favoriteRecipes = request.getParameter("favoriteRecipes");
		
		// Checks to see if the ajax call to get the user's shared recipes
		
		String sharedRecipes = request.getParameter("sharedRecipes");
		
		// Checks to see if the ajax call to get the user's created recipes.

		String myRecipes = request.getParameter("myRecipes");
		
		// Checks to see if the logout button is clicked.
		
		String logout = request.getParameter("logout");
		
		// Checks to see if the viewProfile button is clicked.
		
		String viewProfile = request.getParameter("viewProfile");
		
		// Checks to see if an ajax call to change the category is made.
		
		String loadByCategory = request.getParameter("loadByCategory");
		
		// Checks to see if a button to view another user's profile.
		
		String[] viewOtherUser = request.getParameterValues("otherUser");
		
		// Checks to see if the ajax call to search the database is made.
		
		String search = request.getParameter("searchButton");
		
		// Checks to see if the ajax call to get the selected user's recipes is made.
		
		String getUserRecipes = request.getParameter("getUserRecipes");
		
		// Checks to see if the ajax call to favorite a recipe is made.
		
		String favoriteRecipe = request.getParameter("favoriteRecipe");
		
		// Checks to see if the friends button is clicked.
		
		String friends = request.getParameter("friends");
		
		// Checks to see if a friend request is sent.
		
		String friendRequest = request.getParameter("friendRequest");
		
		// Checks to see if a friend request is accepted.
		
		String acceptRequest = request.getParameter("acceptRequest");
		
		// Checks to see if a friend request is rejected.
		
		String rejectRequest = request.getParameter("rejectRequest");
		
		// Checks to see if a recipe is rated.
		
		String rating = request.getParameter("rankRecipe");
		
		// Checks to see if signin page link button is clicked.
		
		String openSignin = request.getParameter("openSigninPage");
		
		// Checks to see if signup page link button is clicked.
		
		String openSignup = request.getParameter("openSignupPage");
		
		// Gets all of the friends that the user can share with.
		
		String[] friendsToShareWith = request.getParameterValues("friendIDs[]");
		
		// Gets all of the user's friends.
		
		String getMyFriends = request.getParameter("getMyFriends");
		
		// Sign's the user into the website.
		
		if (signin != null)
		{
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			RecipeLogicImpl loginUser = new RecipeLogicImpl();
			User user = loginUser.validateLogin(username.trim(), password.trim());
			if(user != null) {
				openHomePage(user, request, response);
			} else 
			{
				reloadLoginPage(request, response);
			}
		}
		
		// Redirects the user to the homepage.
		
		else if (home != null)
		{
			goToHome(request, response);
		}
		
		// Redirects the user to the viewRecipes.ftl file.
		
		else if (viewRecipes != null)
		{
			viewRecipes(request, response);
		}
		
		// Redirects the user to the createRecipe.ftl file.
		
		else if (createRecipe != null)
		{
			showRecipeMaker(request, response);
		}
		
		// Creates a new recipe using a multipart form.
		
		else if (ServletFileUpload.isMultipartContent(request))
		{
			createNewRecipe(request, response);
		}
		
		// Loads the popular recipes from the database.
		
		else if (loadPopularRecipes != null)
		{
			getPopularRecipes(request, response);
		}
		
		// Loads the information for the selected recipe.
		
		else if (viewRecipe != null)
		{
			viewSelectedRecipe(request, response);
		}
		
		// Logs the user out of the webpage and session.
		
		else if (logout != null) {
			HttpSession session = request.getSession(false);
			session.invalidate();
			response.sendRedirect("index.html");
		}
		
		// Views the user's profile.
		
		else if (viewProfile != null) {
			viewProfile(request, response);
		}
		
		// Loads the recipes for a certain category.
		
		else if (loadByCategory != null)
		{
			loadCategory(request, response, loadByCategory);
		}
		
		// Checks the dynamically created buttons to view another user's webpage.
		
		else if (viewOtherUser != null)
		{
			for (int i = 0; i < viewOtherUser.length; i++)
			{
				if (viewOtherUser[i] != null)
				{
					viewUser(request, response, viewOtherUser[i]);
				}
			}
		}
		
		// Searches the database.
		
		else if(search != null) {
			String term = request.getParameter("term");
			String category = request.getParameter("category");
			Searched(request,response, category, term);
		}
		
		// Gets the recipes for another user.
		
		else if (getUserRecipes != null)
		{
			getOtherUserRecipes(request, response);
		}
		
		// Favorites the current recipe.
		
		else if (favoriteRecipe != null)
		{
			favoriteCurrentRecipe(request, response);
		}
		
		//james
		//Display myRecipes in myProfile
		else if(myRecipes != null)
			{
				HttpSession session = request.getSession(false);	
					
				//UserId to get recipes for the profile
				int userId = (Integer)(session.getAttribute("id"));
					
				//used to call getMyRecipes
				RecipeLogicImpl userCtrl = new RecipeLogicImpl();
					
				//Array List that has recipes/ingredients/instructions
				ArrayList<Recipe> recipes = userCtrl.getMyRecipes(userId);
					
				//Put the recipes into JSON
				Gson g = new Gson();
				response.setContentType("application/json");
				try {
					response.getWriter().write(g.toJson(recipes));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			//james
		
			// Gets the user's favorited recipes.
		
			else if(favoriteRecipes !=null)
				{
					HttpSession session = request.getSession(false);	
					
					//UserId to get recipes for the profile
					int userId = (Integer)(session.getAttribute("id"));
					
					//used to call getMyRecipes
					RecipeLogicImpl userCtrl = new RecipeLogicImpl();
					
					//Array List that has recipes/ingredients/instructions
					ArrayList<Recipe> recipes = userCtrl.getFavoriteRecipes(userId);
					
					//Put the recipes into JSON
					Gson g = new Gson();
					response.setContentType("application/json");
					try {
						response.getWriter().write(g.toJson(recipes));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
		
			// Gets shared recipes.
				
			else if(sharedRecipes != null)
				{
					HttpSession session = request.getSession(false);	
					
					//UserId to get recipes for the profile
					int userId = (Integer)(session.getAttribute("id"));
					
					//used to call getMyRecipes
					RecipeLogicImpl userCtrl = new RecipeLogicImpl();
					
					//list of shared recipes
					ArrayList<Recipe> recipeList = userCtrl.getSharedRecipes(userId);
					
					//Put the recipes into JSON
					Gson g = new Gson();
					
					response.setContentType("application/json");
					try {
						response.getWriter().write(g.toJson(recipeList));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			else if (friends != null) 
				{ //lakshay
					if(request.getSession(false) != null) {
						viewFriends(request, response);
					} 
					else 
					{
						response.sendRedirect("signin.html");
					}
				}
		
			// Sends friend request.
		
			else if (friendRequest != null) 
				{
					String friendId = request.getParameter("id");
					try {
						addFriend(friendId, request, response);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		
			// Accepts friend request.
		
			else if (acceptRequest != null) 
				{
					String friendId = request.getParameter("id");
					acceptRequest(friendId, request, response);
				}
		
			// Rejects friend request.
		
			else if (rejectRequest != null) 
				{
					String friendId = request.getParameter("id");
					rejectRequest(friendId, request, response);
				} 
		
			// Rates the current recipe.
		
			else if(rating!=null) 
				{
					String i = request.getParameter("id");
					String r = request.getParameter("rank");
					int id = Integer.parseInt(i);
					int rank = Integer.parseInt(r);
					Rank(request,response,id,rank);
				}
		
			// Opens signin page.
		
			else if (openSignin != null) 
				{
					response.sendRedirect("signin.html");
				} 
		
			// Opens signup page.
		
			else if (openSignup != null) 
				{
					response.sendRedirect("signup.html");
				}
		
			// Gets friend to share a recipe with.
		
			else if(friendsToShareWith != null)
				{
				//get user id 
				HttpSession session = request.getSession(false);	
				int myID = (Integer)(session.getAttribute("id"));

				ArrayList<Integer> friendsList = new ArrayList<Integer>();

				//ID of the recipe to be shared
				int recipeID = Integer.parseInt(request.getParameter("shareRecipeID"));


				for(int i = 0; i < friendsToShareWith.length; i++)
				{
				int temp = Integer.parseInt(friendsToShareWith[i]);
				friendsList.add(temp);
				}

				//Send recipe to be shared
				shareRecipe(recipeID, myID, friendsList );

				}
		
			// Gets friendlist.
		
			else if (getMyFriends != null)
				{
				HttpSession session = request.getSession(false);	
				int userId = (Integer)(session.getAttribute("id"));

				getMyFriends(response, userId);
				}
	}
	
	
	// Rejects a friend request from another user.
	
	private void rejectRequest(String friendId, HttpServletRequest request, HttpServletResponse response) {
		if(request.getSession(false) != null) {
			int userId = (Integer) request.getSession(false).getAttribute("id");
			RecipeLogicImpl userCtrl = new RecipeLogicImpl();
			String success = userCtrl.rejectRequest(userId, friendId);
			response.setContentType("text/plain");
			try {
				response.getWriter().write(success);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//lakshay
	// Accepts a friend request from another user.
	
	private void acceptRequest(String friendId, HttpServletRequest request, HttpServletResponse response) {
		if(request.getSession(false) != null) {
			int userId = (Integer) request.getSession(false).getAttribute("id");
			RecipeLogicImpl userCtrl = new RecipeLogicImpl();
			String success = userCtrl.acceptRequest(userId, friendId);
			response.setContentType("text/plain");
			try {
				response.getWriter().write(success);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//lakshay
	// Sends a friend request to another user.
	private void addFriend(String friendId, HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, SQLException {
		if(request.getSession(false) != null) {
			int userId = (Integer) request.getSession(false).getAttribute("id");
			RecipeLogicImpl userCtrl = new RecipeLogicImpl();
			String result = userCtrl.addFriend(friendId, userId);
			try {
				response.setContentType("text/plain");
				response.getWriter().write(result);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
		}
	}
	
	//lakshay
	// Loads the friends.ftl page.
	private void viewFriends(HttpServletRequest request, HttpServletResponse response) {
		if(request.getSession(false) != null) {
			int userId = (Integer)request.getSession(false).getAttribute("id");
			RecipeLogicImpl userCtrl = new RecipeLogicImpl();
			List<User> friends = userCtrl.getFriends(userId);
			List<User> users = userCtrl.getUsers(userId);
			List<User> requests = userCtrl.getFriendRequests(userId);
			DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
			SimpleHash root = new SimpleHash(df.build());
			root.put("friends", friends);
			root.put("users", users);
			root.put("fname", request.getSession(false).getAttribute("firstName"));
			root.put("checklogin", 1);
			root.put("requests", requests);
			String templateName = "friends.ftl";
			process.processTemplate(templateName, root, request, response);
		} else {
		}
		
	}

	// Favorites the current recipe.
	
	private void favoriteCurrentRecipe(HttpServletRequest request, HttpServletResponse response) {
		RecipeLogicImpl userCtrl = new RecipeLogicImpl();
		String id = request.getParameter("id");
		int recipeId = Integer.parseInt(id);
		HttpSession session = request.getSession(false);
		if (session != null)
		{
			int tempId = (int) session.getAttribute("id");
			Boolean check = userCtrl.checkFavorites(recipeId, tempId);
			if (check == false)
			{
				int temp = userCtrl.favoriteRecipe(recipeId, tempId);
				if (temp != 0)
				{
					response.setContentType("text/plain");
					try {
						response.getWriter().write("favorited");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
				{
					response.setContentType("text/plain");
					try {
						response.getWriter().write("failed");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} // if
			else
			{
				response.setContentType("text/plain");
				try {
					response.getWriter().write("alreadyFavorited");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else
		{
			response.setContentType("text/plain");
			try {
				response.getWriter().write("nosession");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// Gets the recipes for another user for the viewSelectedUser.ftl page
	
	private void getOtherUserRecipes(HttpServletRequest request, HttpServletResponse response) {
		RecipeLogicImpl userCtrl = new RecipeLogicImpl();
		String id = request.getParameter("userId");
		int userId = Integer.parseInt(id);
		List<Recipe> temp = new ArrayList<Recipe>();
		temp = userCtrl.getUserRecipes(userId);
		Gson g = new Gson();
		response.setContentType("application/json");
		try {
			response.getWriter().write(g.toJson(temp));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Gets the information for a user when displaying another user's profile page.

	private void viewUser(HttpServletRequest request, HttpServletResponse response, String otherUser) {
		RecipeLogicImpl userCtrl = new RecipeLogicImpl();
		int id = Integer.parseInt(otherUser);
		User temp = userCtrl.getUserInfo(id);
		if (temp != null)
		{
			HttpSession session = request.getSession(false);
			if (session == null)
			{
				DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
				SimpleHash root = new SimpleHash(df.build());
				String templateName = "viewSelectedUser.ftl";
				root.put("checklogin", 0);
				root.put("fname", "Not Logged In");
				root.put("user", temp);
				root.put("otherId", id);
				process.processTemplate(templateName, root, request, response);
			}
			else
			{
				HttpSession session2 = request.getSession();
				DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
				SimpleHash root = new SimpleHash(df.build());
				String templateName = "viewSelectedUser.ftl";
				root.put("fname", session2.getAttribute("firstName"));
				root.put("checklogin", 1);
				root.put("user", temp);
				root.put("otherId", id);
				process.processTemplate(templateName, root, request, response);
			}
		}
	}

	// Loads the recipes for a certain category.
	
	private void loadCategory(HttpServletRequest request, HttpServletResponse response, String category) {
		RecipeLogicImpl userCtrl = new RecipeLogicImpl();
		List<Recipe> recipes = new ArrayList<Recipe>();
		recipes = userCtrl.viewByCategory(category);
		
		Gson g = new Gson();
		response.setContentType("application/json");
		try {
			response.getWriter().write(g.toJson(recipes));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Views the information for a selected recipe ajax.

	private void viewSelectedRecipe(HttpServletRequest request, HttpServletResponse response) {
		String tempId = request.getParameter("id");
		int id = Integer.parseInt(tempId);
		RecipeLogicImpl userCtrl = new RecipeLogicImpl();
		Recipe recipe = userCtrl.getRecipeById(id);
		Gson g = new Gson();
		response.setContentType("application/json");
		try {
			response.getWriter().write(g.toJson(recipe));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//JAMES 
	//Used for session checking
	private void viewProfile(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session = request.getSession(false);	
		
		//User is logged in continue
		if (session != null)
		{
			//FTL stuff
			DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
			SimpleHash root = new SimpleHash(df.build());
			
			int userId = (Integer)(session.getAttribute("id"));
			RecipeLogicImpl userCtrl = new RecipeLogicImpl();
			
			User profileUser = userCtrl.getUserByID(userId);
			
			
			String templateName = "viewProfile.ftl";
			root.put("userName", profileUser.getUsername());
			root.put("proPic", profileUser.getImage64());
			root.put("checklogin", 1); 
			process.processTemplate(templateName, root, request, response);
		}
		//User not logged in go to signin page
		else
		{
			try {
				response.sendRedirect("signin.html");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// Gets the top ten popular recipes from the database ajax.
	
	private void getPopularRecipes(HttpServletRequest request, HttpServletResponse response) {
		RecipeLogicImpl userCtrl = new RecipeLogicImpl();
		List<Recipe> recipes = new ArrayList<Recipe>();
		recipes = userCtrl.getPopRecipes();
		
		Gson g = new Gson();
		response.setContentType("application/json");
		try {
			response.getWriter().write(g.toJson(recipes));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Shows the createRecipe page.
	
	private void showRecipeMaker(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		if (session != null)
		{
			DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
			SimpleHash root = new SimpleHash(df.build());
			String templateName = "createrecipe.ftl";
			root.put("fname", session.getAttribute("firstName"));
			root.put("checklogin", 1);
			process.processTemplate(templateName, root, request, response);
		}
		else
		{
			try {
				response.sendRedirect("signin.html");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// Creates a new recipe in the database.

	private void createNewRecipe(HttpServletRequest request, HttpServletResponse response) {
		RecipeLogicImpl userCtrl = new RecipeLogicImpl();
		InputStream fileContent = null;
		String recipeName = null;
		String permission = null;
		String category = null;
		List<String> ingredients = new ArrayList<String>();
		List<String> instructions = new ArrayList<String>();
		
		// Used Apache Commons FileUpload to parse multipart form.
		// Loops through all the FileItems in the form.
		
		try {
			List<FileItem> formItems = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
			for (FileItem formItem : formItems)
			{
				if (formItem.isFormField())
				{
					String name = formItem.getFieldName();
					
					// Sets recipe name.
					
					if (name.equals("recipename"))
					{
						recipeName = formItem.getString();
					}
					
					// Sets ingredients
					
					else if (name.equals("ingredients"))
					{
						ingredients.add(formItem.getString());
					}
					
					// Sets steps
					
					else if (name.equals("steps"))
					{
						instructions.add(formItem.getString());
					}
					
					// Sets visibility
					
					else if (name.equals("visibility"))
					{
						permission = formItem.getString();
					}
					
					// Sets category
					
					else if (name.equals("category"))
					{
						category = formItem.getString();
					}
				}
				
				// Gets file content
				else
				{
					fileContent = formItem.getInputStream();
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession(false);
		int user_id = (Integer)(session.getAttribute("id"));
		int check = userCtrl.createRecipe(recipeName, ingredients, instructions, permission, user_id, fileContent, category);
		if (check != 0)
		{
			DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
			SimpleHash root = new SimpleHash(df.build());
			String templateName = "createrecipe.ftl";
			root.put("fname", session.getAttribute("firstName"));
			root.put("checklogin", 1);
			process.processTemplate(templateName, root, request, response);
		}
	}

	// Loads the viewRecipe page.
	
	private void viewRecipes(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		if (session == null)
		{
			DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
			SimpleHash root = new SimpleHash(df.build());
			String templateName = "viewrecipe.ftl";
			root.put("checklogin", 0);
			root.put("fname", "Not Logged In");
			process.processTemplate(templateName, root, request, response);
		}
		else
		{
			HttpSession session2 = request.getSession();
			DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
			SimpleHash root = new SimpleHash(df.build());
			String templateName = "viewrecipe.ftl";
			root.put("fname", session2.getAttribute("firstName"));
			root.put("checklogin", 1);
			process.processTemplate(templateName, root, request, response);
		}
	}

	// Goes to homepage.ftl
	
	private void goToHome(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		if (session == null)
		{
			DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
			SimpleHash root = new SimpleHash(df.build());
			String templateName = "homepage.ftl";
			root.put("checklogin", 0);
			root.put("fname", "Not Logged In");
			process.processTemplate(templateName, root, request, response);
		}
		else
		{
			HttpSession session2 = request.getSession();
			DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
			SimpleHash root = new SimpleHash(df.build());
			String templateName = "homepage.ftl";
			root.put("fname", session2.getAttribute("firstName"));
			root.put("checklogin", 1);
			process.processTemplate(templateName, root, request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	// Opens the homepage when the user signs in.
	
	public void openHomePage(User user, HttpServletRequest request, HttpServletResponse response) {
		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());
		String templateName = "homepage.ftl";
		
		HttpSession session = request.getSession();
		synchronized(session) {
			session.setMaxInactiveInterval(-1);
			session.setAttribute("id", user.getId());
			session.setAttribute("firstName", user.getFirst_name());
			session.setAttribute("lastName", user.getLast_name());
			session.setAttribute("username", user.getUsername());
			session.setAttribute("email", user.getEmail());
			session.setAttribute("picture", user.getImage64());
		}
		root.put("fname", session.getAttribute("firstName"));
		root.put("checklogin", 1);
		
		process.processTemplate(templateName, root, request, response);
	}
	
	// Reloads the signin page.
	
	public void reloadLoginPage(HttpServletRequest request, HttpServletResponse response) {
		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());
		String templateName = "signin.ftl";
		process.processTemplate(templateName, root, request, response);
	}
	
	// Searches the database.
	
	public void Searched(HttpServletRequest request, HttpServletResponse response, String category, String term) {		
		RecipeLogicImpl userCtrl = new RecipeLogicImpl();
		List<Recipe> recipes = new ArrayList<Recipe>();
		recipes = userCtrl.Search(category, term);
		
		Gson g = new Gson();
		response.setContentType("application/json");
		try {
			response.getWriter().write(g.toJson(recipes));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Ranks a recipe.
	
	public void Rank(HttpServletRequest request, HttpServletResponse response, int id, int rank) {		
		RecipeLogicImpl userCtrl = new RecipeLogicImpl();
		int i = userCtrl.Ranked(id, rank);
		if(i == 0)
		{
			
		}
	}

	// Shares a recipe with multiple users.
	
	private void shareRecipe(int recipeID, int myID, ArrayList<Integer> friendsList)
	{
	RecipeLogicImpl userCtrl = new RecipeLogicImpl();

	for(int i = 0; i < friendsList.size(); i++)
	{
	userCtrl.shareRecipe(recipeID, myID, friendsList.get(i));
	}
	}

	//James
	//Get's list of friends for myProfile
	private void getMyFriends(HttpServletResponse response, int id)
	{
	RecipeLogicImpl userCtrl = new RecipeLogicImpl();
	List<User> friendList = userCtrl.getFriends(id);

	//Put the friends into JSON
	Gson g = new Gson();
	response.setContentType("application/json");
	try {
	response.getWriter().write(g.toJson(friendList));
	} catch (IOException e) {
	e.printStackTrace();
	}
	}

}
