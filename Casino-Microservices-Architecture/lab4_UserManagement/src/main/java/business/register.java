package business;

import helper.UserInfo;
import persistence.User_CRUD;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

public class register {

    public boolean registerUser(String username, String password, String email) {
        // Logic to register the user (save to DB, etc.)
        // Return true if successful, false otherwise
        System.out.println("Registering: " + username);
        return true;
    }
}
