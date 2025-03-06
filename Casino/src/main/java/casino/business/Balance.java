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
        // Retrieve username and action
        String username = (String) request.getSession().getAttribute("username");
        String action = request.getParameter("action");
        String amountString = request.getParameter("amount");
        
        // Validate action and amount
        if (action == null || amountString == null || amountString.isEmpty()) {
            response.getWriter().write("Error: Missing action or amount.");
            return;
        }

        // Try parsing the amount
        float amount = 0;
        try {
            amount = Float.parseFloat(amountString);
        } catch (NumberFormatException e) {
            response.getWriter().write("Error: Invalid amount format.");
            return;
        }

        // Ensure the amount is positive
        if (amount <= 0) {
            response.getWriter().write("Error: Amount must be greater than zero.");
            return;
        }

        // Retrieve user information from the session
        UserInfo account = (UserInfo) request.getSession().getAttribute("userInfo");
        if (account == null) {
            response.getWriter().write("Error: User not logged in.");
            return;
        }

        // Get the current balance
        float currentBalance = account.getBalance();
        float newBalance = currentBalance;

        // Process the action (deposit or withdraw)
        if (action.equals("deposit")) {
            newBalance += amount;
        } else if (action.equals("withdraw")) {
            if (currentBalance >= amount) {
                newBalance -= amount;
            } else {
                response.getWriter().write("Error: Insufficient balance.");
                return;
            }
        } else {
            response.getWriter().write("Error: Invalid action.");
            return;
        }

        // Update the balance in the database
        String updateStatus = User_CRUD.updateBalance(username, newBalance);

        // If successful, update the session data and redirect back to the account page
        if (updateStatus.equals("Balance updated successfully!")) {
            account.setBalance(newBalance); // Update the session user balance
            response.sendRedirect("account.jsp"); // Redirect back to account page
        } else {
            response.getWriter().write("Error: Error updating balance.");
        }
    }
}
