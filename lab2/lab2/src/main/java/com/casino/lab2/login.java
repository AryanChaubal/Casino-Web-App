
package com.casino.lab2;

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
        
        String username=(String) request.getParameter("username");
        String password=(String) request.getParameter("password");
        
        AccountInfo accinfo =getAccountInfo(username, password);
        
        if (accinfo==null){
            RequestDispatcher rd= request.getRequestDispatcher("loginfailed.jsp");
            rd.forward(request, response);
        }       
        else{
            request.getSession().setAttribute("username", username);
            request.setAttribute("userInfo", accinfo);
            response.sendRedirect("account.jsp");
            
        }     
    }

    private AccountInfo getAccountInfo(String username, String password) {
        /**
         * to be completed. For now returns example user information
         */
        AccountInfo user= new AccountInfo(username, "Jhon Doe", password, "example@domain.com");
   
        return user;
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

          doPost(request, response);

        
    }

}
