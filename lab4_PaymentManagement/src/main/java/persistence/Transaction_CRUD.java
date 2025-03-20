package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;
import helper.*;

public class Transaction_CRUD {

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

    public static boolean addTransaction(int walletId, String type, double amount, String status) {
        try {
            Connection con = getCon();
            String q = "INSERT INTO TRANSACTION (transactionID, wallet_id, type, amount, transactionDate, status) VALUES (?, ?, ?, ?, NOW(), ?)";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setString(1, java.util.UUID.randomUUID().toString()); // Generate a unique transaction ID
            ps.setInt(2, walletId);
            ps.setString(3, type);
            ps.setDouble(4, amount);
            ps.setString(5, status);
            ps.executeUpdate();
            con.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static Set<Transaction> getTransactionsByWallet(int walletId) {
        Set<Transaction> transactions = new HashSet<>();
        try {
            Connection con = getCon();
            String q = "SELECT * FROM TRANSACTION WHERE wallet_id = ?";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setInt(1, walletId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String transactionID = rs.getString("transactionID");
                String type = rs.getString("type");
                double amount = rs.getDouble("amount");
                String transactionDate = rs.getString("transactionDate");
                String status = rs.getString("status");
                Transaction transaction = new Transaction(transactionID, walletId, type, amount, transactionDate, status);
                transactions.add(transaction);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return transactions;
    }
}