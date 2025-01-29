
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author student
 */
@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String username= (String) request.getParameter("username"); 
        String password= (String) request.getParameter("password");
    
        UserInfo uinfo=getUserInfo (username, password);
    
        if (uinfo==null) {
            RequestDispatcher rd= request.getRequestDispatcher ("login failed.jsp"); 
            rd. forward (request, response);
        }
        else{
            request.getSession().setAttribute("uname", username);
            request.setAttribute("booksBorrowed Info", uinfo.getBookBorrowed());
            RequestDispatcher rd= request.getRequestDispatcher ("userbooks.jsp");
            rd. forward (request, response);
        }
    
}
