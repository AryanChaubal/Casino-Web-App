package business;

import helper.UserInfo;
import persistence.User_CRUD;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

public class Register {

    public boolean registerUser(String username, String password, String email) {
        
        UserInfo user = new UserInfo(username, password, email, 0);
        User_CRUD userCRUD = new User_CRUD();
        userCRUD.create(user);
        
        return true;
    }
}
