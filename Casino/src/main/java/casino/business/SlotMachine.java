package casino.business;

import casino.helper.UserInfo;
import java.io.IOException;
import java.io.PrintWriter;
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
    private static final double MULTIPLIER = 5.0;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        UserInfo account = (UserInfo) session.getAttribute("userInfo");
        if (account == null) {
            out.println("<html><body>");
            out.println("<h2>Error: User not logged in.</h2>");
            out.println("<a href='login.jsp'>Login</a>");
            out.println("</body></html>");
            return;
        }

        double balance = account.getBalance();
        String betParam = request.getParameter("bet");
        double bet = betParam != null ? Double.parseDouble(betParam) : 0;

        if (bet <= 0 || bet > balance) {
            out.println("<html><body>");
            out.println("<h2>Invalid bet amount! Your balance: $" + balance + "</h2>");
            out.println("<a href='index.jsp'>Go Back</a>");
            out.println("</body></html>");
            return;
        }

        balance -= bet;
        String[] result = spin();

        out.println("<html><body>");
        out.println("<h2>Slot Machine Result: " + result[0] + " | " + result[1] + " | " + result[2] + "</h2>");
        
        if (checkWin(result)) {
            double winnings = bet * MULTIPLIER;
            balance += winnings;
            out.println("<h3>Congratulations! You won $" + winnings + "!</h3>");
        } else {
            out.println("<h3>Try again!</h3>");
        }
        
        account.setBalance((float) balance);
        session.setAttribute("userInfo", account);
        out.println("<h3>Your balance: $" + balance + "</h3>");
        out.println("<a href='index.jsp'>Back to Slots</a>");
        out.println("</body></html>");
    }

    private String[] spin() {
        String[] result = new String[3];
        for (int i = 0; i < 3; i++) {
            result[i] = SYMBOLS[random.nextInt(SYMBOLS.length)];
        }
        return result;
    }

    private boolean checkWin(String[] result) {
        return result[0].equals(result[1]) && result[1].equals(result[2]);
    }
}
