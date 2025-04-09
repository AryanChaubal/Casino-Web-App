package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import helper.Transaction;

public class Transaction_CRUD {

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

    public static boolean addTransaction(int walletId, String type, double amount) {
        try {
            Connection con = getCon();
            String q = "INSERT INTO Transaction (wallet_id, type, amount, transaction_date) VALUES (?, ?, ?, NOW())";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setInt(1, walletId);
            ps.setString(2, type);
            ps.setDouble(3, amount);
            ps.executeUpdate();
            con.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static List<Transaction> getTransactionsByWallet(int walletId) {
        List<Transaction> transactions = new ArrayList<>();
        try {
            Connection con = getCon();
            String q = "SELECT * FROM Transaction WHERE wallet_id = ?";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setInt(1, walletId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int transactionId = rs.getInt("transaction_id");
                String type = rs.getString("type");
                double amount = rs.getDouble("amount");
                String transactionDate = rs.getString("transaction_date");
                transactions.add(new Transaction(transactionId, walletId, type, amount, transactionDate));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return transactions;
    }
}