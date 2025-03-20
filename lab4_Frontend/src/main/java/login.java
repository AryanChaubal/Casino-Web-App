import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Login", urlPatterns = {"/auth/login"}) // Updated to match frontend
public class login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve form data
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Validate input
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            // If input is invalid, redirect back to the login page with an error message
            request.setAttribute("error", "Username and password are required.");
            RequestDispatcher rd = request.getRequestDispatcher("/frontend/login.html"); // Adjust path as needed
            rd.forward(request, response);
            return;
        }

        // Check user credentials
        UserInfo userInfo = User_CRUD.read(username, password);

        if (userInfo == null) {
            // If credentials are invalid, redirect back to the login page with an error message
            request.setAttribute("error", "Invalid username or password.");
            RequestDispatcher rd = request.getRequestDispatcher("/frontend/login.html"); // Adjust path as needed
            rd.forward(request, response);
        } else {
            // If credentials are valid, set session attributes and redirect to account page
            request.getSession().setAttribute("username", username);
            request.getSession().setAttribute("userInfo", userInfo);
            response.sendRedirect("/frontend/account.jsp"); // Adjust path as needed
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirect GET requests to the login page
        response.sendRedirect("/frontend/login.html"); // Adjust path as needed
    }
}