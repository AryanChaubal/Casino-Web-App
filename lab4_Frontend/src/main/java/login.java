import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Login", urlPatterns = {"/auth/login"})
public class login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            request.setAttribute("error", "Username and password are required.");
            RequestDispatcher rd = request.getRequestDispatcher("/frontend/login.html");
            rd.forward(request, response);
            return;
        }

        UserInfo userInfo = User_CRUD.read(username, password);

        if (userInfo == null) {
            request.setAttribute("error", "Invalid username or password.");
            RequestDispatcher rd = request.getRequestDispatcher("/frontend/login.html");
            rd.forward(request, response);
        } else {
            request.getSession().setAttribute("username", username);
            request.getSession().setAttribute("userInfo", userInfo);
            response.sendRedirect("/frontend/account.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("/frontend/login.html");
    }
}
