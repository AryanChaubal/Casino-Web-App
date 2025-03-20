package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Wallet_CRUD {

    private static Connection getCon() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/casino_payment?autoReconnect=true&useSSL=false", "root", "student");
            System.out.println("Connection established.");
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }

    public static double getBalance(int walletId) {
        double balance = 0.0;
        try {
            Connection con = getCon();
            String q = "SELECT balance FROM Wallet WHERE wallet_id = ?";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setInt(1, walletId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                balance = rs.getDouble("balance");
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return balance;
    }

    public static boolean updateBalance(int walletId, double amount) {
        try {
            Connection con = getCon();
            String q = "UPDATE Wallet SET balance = balance + ? WHERE wallet_id = ?";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setDouble(1, amount);
            ps.setInt(2, walletId);
            ps.executeUpdate();
            con.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}