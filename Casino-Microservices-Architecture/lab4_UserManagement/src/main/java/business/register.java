package business;

import helper.UserInfo;
import persistence.User_CRUD;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

<<<<<<< HEAD
<<<<<<< HEAD
public class Register {

    public boolean registerUser(String username, String password, String email) {
        
        UserInfo user = new UserInfo(username, password, email, 0);
        User_CRUD userCRUD = new User_CRUD();
        userCRUD.create(user);
        
        return true;
=======
@WebServlet(name = "register", urlPatterns = {"/register"})
public class register extends HttpServlet {

=======
@WebServlet(name = "register", urlPatterns = {"/register"})
public class register extends HttpServlet {

>>>>>>> parent of deaee41 (attempting to fix the urls)
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        // Validate the input fields
        if (username == null || username.isEmpty() || password == null || password.isEmpty() || email == null || email.isEmpty()) {
            request.setAttribute("error", "All fields are required.");
            request.getRequestDispatcher("/register.html").forward(request, response);
            return;
        }

        // Check if the username already exists
        if (User_CRUD.userExists(username)) {
            request.setAttribute("error", "Username already exists.");
            request.getRequestDispatcher("/register.html").forward(request, response);
            return;
        }

        // Check if the email already exists
        if (User_CRUD.emailExists(email)) {
            request.setAttribute("error", "Email already exists.");
            request.getRequestDispatcher("/register.html").forward(request, response);
            return;
        }

        // Create a new user and insert into the database
        UserInfo newUser = new UserInfo(username, password, email, 0);  // Assuming balance starts at 0
        User_CRUD userCrud = new User_CRUD();  // Create an instance of User_CRUD
        String result = userCrud.create(newUser);  // Call the non-static create method

        // Check if the user was successfully created
        if ("inserted".equalsIgnoreCase(result)) {
            response.sendRedirect("/login.html");
        } else {
            request.setAttribute("error", "Registration failed. Please try again.");
            request.getRequestDispatcher("/register.html").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("/register.html");
<<<<<<< HEAD
>>>>>>> parent of deaee41 (attempting to fix the urls)
=======
>>>>>>> parent of deaee41 (attempting to fix the urls)
    }
}
