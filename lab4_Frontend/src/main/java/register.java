
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "register", urlPatterns = {"/register"})
public class register extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        if (username == null || username.isEmpty()
                || password == null || password.isEmpty()
                || email == null || email.isEmpty()) {
            request.setAttribute("error", "All fields are required.");
            RequestDispatcher rd = request.getRequestDispatcher("/lab4_Frontend/register.html");
            rd.forward(request, response);
            return;
        }

        User_CRUD ucrud = new User_CRUD();
        if (ucrud.userExists(username)) {
            request.setAttribute("error", "Username already exists.");
            RequestDispatcher rd = request.getRequestDispatcher("/lab4_Frontend/register.html");
            rd.forward(request, response);
            return;
        }

        if (ucrud.emailExists(email)) {
            request.setAttribute("error", "Email already exists.");
            RequestDispatcher rd = request.getRequestDispatcher("/lab4_Frontend/register.html");
            rd.forward(request, response);
            return;
        }

        UserInfo user = new UserInfo(username, password, email, 0);
        try {
            String result = ucrud.create(user);
            if (result.equalsIgnoreCase("inserted")) {
                response.sendRedirect("/lab4_Frontend/login.html");
            } else {
                request.setAttribute("error", "Registration failed. Please try again.");
                RequestDispatcher rd = request.getRequestDispatcher("/lab4_Frontend/register.html");
                rd.forward(request, response);
            }
        } catch (IOException ex) {
            Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("error", "An error occurred. Please try again.");
            RequestDispatcher rd = request.getRequestDispatcher("/lab4_Frontend/register.html");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("/lab4_Frontend/register.html");
    }

    @Override
    public String getServletInfo() {
        return "Handles user registration by adding new users to the database.";
    }
}
