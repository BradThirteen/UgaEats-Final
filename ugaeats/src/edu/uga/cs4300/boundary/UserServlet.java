package edu.uga.cs4300.boundary;

import java.io.IOException;
import java.io.InputStream;
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

import edu.uga.cs4300.logiclayer.RecipeLogicImpl;
import edu.uga.cs4300.objectlayer.User;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;

/*	Brandon Lee, Bradley Reeves, Lakshay Sharma, James O'Boyle
 * 	Section 26666
 * 
 * 	Used to differentiate between two multi part forms and sign a user up
 *  to the database.
 */


/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String templateDir = "/WEB-INF/templates";
	
	private TemplateProcessor process;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		process = new TemplateProcessor(templateDir, getServletContext());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (ServletFileUpload.isMultipartContent(request))
		{
			createNewUser(request, response);
		}
	}

	//Method to create a new user based on the submitted form
    private void createNewUser(HttpServletRequest request, HttpServletResponse response) {
        RecipeLogicImpl userCtrl = new RecipeLogicImpl();
         
        InputStream fileContent = null;
        String fname = null;
        String lname = null;
        String uname = null;
        String password = null;
        String email = null;
         
        try {
            List<FileItem> formItems = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            for (FileItem formItem : formItems)
            {
                if (formItem.isFormField())
                {
                    String name = formItem.getFieldName();
                     
                    if (name.equals("firstName"))
                    {
                        fname = formItem.getString().trim();
                    }
                    else if (name.equals("lastName"))
                    {
                        lname = formItem.getString().trim();
                    }
                    else if (name.equals("username"))
                    {
                        uname = formItem.getString().trim();
                    }
                    else if (name.equals("password"))
                    {
                        password = formItem.getString().trim();
                    }
                    else if (name.equals("email"))
                    {
                        email = formItem.getString().trim();
                    }
                }
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
        //Call to check for duplicate username
        Boolean check = userCtrl.LogicCheckUser(uname);
        if(!check) {
        int temp = userCtrl.sendNewUserInfo(fname, lname, uname, password, email, fileContent);
        if (temp != 0) //if non duplicate username and sendNewUserInfo is successful
            {
            User user = userCtrl.validateLogin(uname, password);
            DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
            SimpleHash root = new SimpleHash(df.build());
            String templateName = "homepage.ftl";
         
            //Checks for session
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
        else
            {//if sendNewUserInfo is unsuccessful
                try {
                    response.sendRedirect("signup.html");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else { //if username is a dupe
            DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
            SimpleHash root = new SimpleHash(df.build());
            String templateName = "dupe.ftl";
            root.put("dupe", true);
            process.processTemplate(templateName, root, request, response);
        }
    }
}
