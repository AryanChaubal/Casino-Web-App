package casino.business;

import casino.helper.UserInfo;
import casino.persistence.User_CRUD;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "balance", urlPatterns = {"/Balance"})
public class Balance extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getSession().getAttribute("username");
        String action = request.getParameter("action");
        float amount = Float.parseFloat(request.getParameter("amount"));

        UserInfo account = (UserInfo) request.getSession().getAttribute("userInfo");
        float currentBalance = account != null ? account.getBalance() : 0;

        float newBalance = currentBalance;
        if (action != null && amount > 0) {
            if (action.equals("deposit")) {
                newBalance += amount;
            } else if (action.equals("withdraw") && currentBalance >= amount) {
                newBalance -= amount;
            } else if (action.equals("withdraw")) {
                // Handle insufficient balance case
                response.getWriter().write("Insufficient balance");
                return;
            }
        }

        // Update the balance in the database
        String updateStatus = User_CRUD.updateBalance(username, newBalance);

        // If successful, update the session data and redirect back to the account page
        if (updateStatus.equals("Balance updated successfully!")) {
            account.setBalance(newBalance); // Update the session user balance
            response.sendRedirect("account.jsp"); // Redirect back to account page
        } else {
            response.getWriter().write("Error updating balance");
        }
    }
}
