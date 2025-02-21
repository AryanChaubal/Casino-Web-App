
package Business;

import Helper.UserInfo;
import Persistence.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author student
 */
@WebServlet(name = "Login", urlPatterns = {"/login"})
public class login extends HttpServlet {

    
   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username=(String) request.getParameter("username");
        String password=(String) request.getParameter("password");
        
        UserInfo userInfo = User_CRUD.read(username, password);
        
        if (userInfo==null){
            RequestDispatcher rd= request.getRequestDispatcher("index.html");
            rd.forward(request, response);
        }       
        else{
            request.getSession().setAttribute("username", username);
            request.getSession().setAttribute("userInfo", userInfo);
            RequestDispatcher rd= request.getRequestDispatcher("account.jsp");
            rd.forward(request, response);
        }     
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

          doPost(request, response);

        
    }

}
