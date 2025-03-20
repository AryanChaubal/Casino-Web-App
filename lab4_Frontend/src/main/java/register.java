import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "register", urlPatterns = {"/auth/register"}) // Updated to match frontend
public class register extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve form data
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        // Validate input
        if (username == null || username.isEmpty() ||
            password == null || password.isEmpty() ||
            email == null || email.isEmpty()) {
            // If input is invalid, redirect back to the register page with an error message
            request.setAttribute("error", "All fields are required.");
            RequestDispatcher rd = request.getRequestDispatcher("/frontend/register.html"); // Adjust path as needed
            rd.forward(request, response);
            return;
        }

        // Check if the username or email already exists
        User_CRUD ucrud = new User_CRUD();
        if (ucrud.userExists(username)) {
            request.setAttribute("error", "Username already exists.");
            RequestDispatcher rd = request.getRequestDispatcher("/frontend/register.html"); // Adjust path as needed
            rd.forward(request, response);
            return;
        }

        if (ucrud.emailExists(email)) {
            request.setAttribute("error", "Email already exists.");
            RequestDispatcher rd = request.getRequestDispatcher("/frontend/register.html"); // Adjust path as needed
            rd.forward(request, response);
            return;
        }

        // Create a new user
        UserInfo user = new UserInfo(username, password, email, 0); // Assuming balance is 0 for new users
        try {
            String result = ucrud.create(user);
            if (result.equalsIgnoreCase("inserted")) {
                // If registration is successful, redirect to the login page
                response.sendRedirect("/frontend/login.html"); // Adjust path as needed
            } else {
                // If registration fails, redirect back to the register page with an error message
                request.setAttribute("error", "Registration failed. Please try again.");
                RequestDispatcher rd = request.getRequestDispatcher("/frontend/register.html"); // Adjust path as needed
                rd.forward(request, response);
            }
        } catch (IOException ex) {
            Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
            // Log the error and redirect back to the register page with an error message
            request.setAttribute("error", "An error occurred. Please try again.");
            RequestDispatcher rd = request.getRequestDispatcher("/frontend/register.html"); // Adjust path as needed
            rd.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirect GET requests to the register page
        response.sendRedirect("/frontend/register.html"); // Adjust path as needed
    }

    @Override
    public String getServletInfo() {
        return "Handles user registration by adding new users to the database.";
    }
}