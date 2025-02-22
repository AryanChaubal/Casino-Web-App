/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casino.business;

import casino.persistence.User_CRUD;
import casino.helper.UserInfo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jdk.vm.ci.code.Register;

/**
 *
 * @author student
 */
@WebServlet(name = "register", urlPatterns = {"/register"})
public class register extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        
        UserInfo user = new UserInfo(username, password, email);
        User_CRUD ucrud = new User_CRUD();
        
         try{
            String s1 = ucrud.create(user);
            if(s1.equalsIgnoreCase("inserted")){
                request.getRequestDispatcher("login.html").forward(request, response);
            }else{
                System.out.println("error");
            }
        }catch (IOException ex){
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            }
        request.getRequestDispatcher("index.html").forward(request, response);
        }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Takes in registration information and adds to database(Currently no database, instead prints information)";
    }// </editor-fold>

}
