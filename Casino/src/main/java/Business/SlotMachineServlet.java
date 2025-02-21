import java.io.IOException;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SlotMachineServlet")
public class SlotMachineServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String[] SYMBOLS = {"Cherry", "Lemon", "Orange", "Bell", "Bar", "Seven"};
    private static final Random random = new Random();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reel1 = SYMBOLS[random.nextInt(SYMBOLS.length)];
        String reel2 = SYMBOLS[random.nextInt(SYMBOLS.length)];
        String reel3 = SYMBOLS[random.nextInt(SYMBOLS.length)];
        
        request.setAttribute("reel1", reel1);
        request.setAttribute("reel2", reel2);
        request.setAttribute("reel3", reel3);
        
        if (reel1.equals(reel2) && reel2.equals(reel3)) {
            request.setAttribute("result", "Congratulations! You won with three " + reel1 + "s!");
        } else {
            request.setAttribute("result", "Better luck next time!");
        }
        
        request.getRequestDispatcher("slotmachine.jsp").forward(request, response);
    }
}
