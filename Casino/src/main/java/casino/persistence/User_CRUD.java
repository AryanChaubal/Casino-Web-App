package casino.persistence;

import casino.helper.UserInfo;
import java.sql.*;

public class User_CRUD {

    // Establish Database Connection
    private static Connection getCon() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Updated for MySQL 8+
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/CWA?useTimezone=true&serverTimezone=UTC",
                    "root", "root"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    // Create (Register) New User
    public String create(UserInfo u) {
        try{
            try (Connection con = getCon()) {
                String query = "INSERT INTO User (username, email, password, balance) VALUES ("
                        + "('"+u.getUsername()+"', "
                        + "'"+u.getEmail()+"', "
                        + "'"+u.getPassword()+"', "
                        +"'"+u.getBalance()+"');";
                Statement stmt = con.createStatement();
                System.out.println(query);
                stmt.execute(query);
                con.close();
                return "true";
            }
            }catch(SQLException e){
                System.out.println(e);
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

    // Update User Balance
    public static String updateBalance(String username, float newBalance) {
        try{
            try (Connection con = getCon()) {
                String query = "UPDATE User "
                        + "SET balance = " + newBalance 
                        + "WHERE username = '" +username+ "';";
                Statement stmt = con.createStatement();
                System.out.println(query);
                stmt.execute(query);
                con.close();
                return "Balance updated successfully!";
            }
            }catch(SQLException e){
                System.out.println(e);
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
