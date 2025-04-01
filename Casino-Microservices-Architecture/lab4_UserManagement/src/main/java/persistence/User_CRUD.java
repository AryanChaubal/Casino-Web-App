package persistence;

import helper.UserInfo;
import java.sql.*;

public class User_CRUD {

    // Establish Database Connection
    private static Connection getCon() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Updated for MySQL 8+
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/userDB?useTimezone=true&serverTimezone=UTC",
                    "root", "root" // Update username and password as needed
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    // Create (Register) New User
    public String create(UserInfo user) {
        String query = "INSERT INTO User (username, email, password, balance) VALUES (?, ?, ?, ?)";
        try (Connection con = getCon(); PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setFloat(4, user.getBalance());

            int rowsInserted = pstmt.executeUpdate();
            return (rowsInserted > 0) ? "inserted" : "error";
        } catch (SQLException e) {
            e.printStackTrace();
            return "error";
        }
    }

    // Read (Login) User
    public static UserInfo read(String username, String password) {
        String query = "SELECT * FROM User WHERE username = ?";
        UserInfo user = null;

        try (Connection con = getCon(); PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String email = rs.getString("email");
                String dbPassword = rs.getString("password");
                float balance = rs.getFloat("balance");

                // Verify password (consider using password hashing in production)
                if (dbPassword.equals(password)) {
                    user = new UserInfo(username, password, email, balance);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // Check if Username Exists
    public static boolean userExists(String username) {
        String query = "SELECT * FROM User WHERE username = ?";
        try (Connection con = getCon(); PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // Returns true if username exists
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Check if Email Exists
    public static boolean emailExists(String email) {
        String query = "SELECT * FROM User WHERE email = ?";
        try (Connection con = getCon(); PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // Returns true if email exists
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update User Balance
    public static String updateBalance(String username, float newBalance) {
        String query = "UPDATE User SET balance = ? WHERE username = ?";
        try (Connection con = getCon(); PreparedStatement ps = con.prepareStatement(query)) {

            ps.setFloat(1, newBalance);
            ps.setString(2, username);

            int rowsUpdated = ps.executeUpdate();
            return (rowsUpdated > 0) ? "Balance updated successfully!" : "Error updating balance";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error updating balance";
        }
    }

    // Delete User
    public static String deleteUser(String username) {
        String query = "DELETE FROM User WHERE username = ?";
        try (Connection con = getCon(); PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, username);
            int rowsDeleted = ps.executeUpdate();
            return (rowsDeleted > 0) ? "User deleted successfully!" : "Error deleting user";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error deleting user";
        }
    }
}