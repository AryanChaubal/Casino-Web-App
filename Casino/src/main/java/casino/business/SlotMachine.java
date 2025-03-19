package casino.business;

import casino.helper.UserInfo;
import java.io.IOException;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SlotMachine")
public class SlotMachine extends HttpServlet {

    private static final String[] SYMBOLS = {"Cherry", "Lemon", "Orange", "Plum", "Bell", "Bar", "Seven"};
    private static final Random random = new Random();
    private static final int WIN_MULTIPLIER = 81;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        UserInfo account = (UserInfo) session.getAttribute("userInfo");

        if (account == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        double balance = account.getBalance();
        double bet;

        try {
            bet = Double.parseDouble(request.getParameter("bet"));
            if (bet <= 0 || bet > balance) {
                throw new IllegalArgumentException("Invalid bet amount");
            }
        } catch (IllegalArgumentException e) { // This covers both NumberFormatException and IllegalArgumentException
            response.getWriter().println("<h2>Invalid bet amount. Please enter a valid number within your balance.</h2>");
            return;
        }

        balance -= bet;
        String[] result = spinReels();
        double winnings = calculateWinnings(result, bet);
        balance += winnings;

        account.setBalance((float) balance);
        session.setAttribute("userInfo", account);

        // Display results
        response.getWriter().println("<html><body>");
        response.getWriter().println("<h2>Slot Machine Result</h2>");
        response.getWriter().println("<p>" + result[0] + " | " + result[1] + " | " + result[2] + "</p>");

        if (winnings > 0) {
            response.getWriter().println("<h3>Congratulations! You won $" + winnings + "!</h3>");
        } else {
            response.getWriter().println("<h3>Try again!</h3>");
        }

        response.getWriter().println("<h3>Your balance: $" + balance + "</h3>");
        response.getWriter().println("</body></html>");
    }

    /**
     * Spins the slot reels and returns three random symbols.
     */
    private String[] spinReels() {
        return new String[]{
            SYMBOLS[random.nextInt(SYMBOLS.length)],
            SYMBOLS[random.nextInt(SYMBOLS.length)],
            SYMBOLS[random.nextInt(SYMBOLS.length)]
        };
    }


    private double calculateWinnings(String[] result, double bet) {
        return (result[0].equals(result[1]) && result[1].equals(result[2])) ? bet * WIN_MULTIPLIER : 0;
    }
}
