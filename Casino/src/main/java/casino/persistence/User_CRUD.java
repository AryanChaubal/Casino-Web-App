package casino.persistence;

/**
 *
 * @author student
 */

import casino.helper.UserInfo;
import java.sql.*;


public class User_CRUD {

    private static Connection getCon(){
        Connection con = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/CWA?autoReconnect=true&useSSL=false","root","student");
            System.out.println("Connection established.");
	}catch(Exception e){System.out.println(e);
        }
        return con;
    }
    
    public String create(UserInfo u){
        
        String s1 = "";
        
        try{
            Connection con = getCon();
            Statement stmt = con.createStatement();
            String query = "INSERT INTO USERS (username, email, password) VALUES ('"+u.getUsername()+"', '"+u.getEmail()+"', '"+u.getPassword()+"')";
            stmt.executeUpdate(query);
            s1 = "inserted";
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return s1;
    }
    
    public static UserInfo read(String username, String password){
        UserInfo bean = null;
        try{
            Connection con = getCon();
            
            String q = "select * from User WHERE username LIKE \'" + username+"\'";
            
            PreparedStatement ps = con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String email = rs.getString("email");
                String uname = rs.getString("username");
                String pw = rs.getString("password");
                float bal = (rs.getFloat("balance"));
                
                if(pw.equals(password)){
                    bean = new UserInfo(uname,pw,email, bal);
                }  
            }
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }
        return bean;
    }
    
    
}