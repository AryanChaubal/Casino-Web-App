package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import helper.UserInfo;

public class User_CRUD {

    private static Connection getCon() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/casino_game?autoReconnect=true&useSSL=false", "root", "student");
            System.out.println("Connection established.");
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }

    public static UserInfo getUserByUsername(String username) {
        UserInfo user = null;
        try {
            Connection con = getCon();
            String q = "SELECT * FROM User WHERE username = ?";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int userId = rs.getInt("user_id");
                double balance = rs.getDouble("balance");
                user = new UserInfo(userId, username, balance);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return user;
    }

    public static boolean updateBalance(int userId, double balance) {
        try {
            Connection con = getCon();
            String q = "UPDATE User SET balance = ? WHERE user_id = ?";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setDouble(1, balance);
            ps.setInt(2, userId);
            ps.executeUpdate();
            con.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}