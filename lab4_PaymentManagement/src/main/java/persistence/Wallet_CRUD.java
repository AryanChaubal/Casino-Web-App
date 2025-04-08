package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import helper.Wallet;

public class Wallet_CRUD {

    private static Connection getCon() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wallet_service?autoReconnect=true&useSSL=false", "root", "root");
            System.out.println("Connection established.");
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }

    public static Wallet getWalletByUsername(String username) {
        Wallet wallet = null;
        try {
            Connection con = getCon();
            String q = "SELECT * FROM Wallet WHERE username = ?";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int walletId = rs.getInt("wallet_id");
                double balance = rs.getDouble("balance");
                wallet = new Wallet(walletId, username, balance);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return wallet;
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