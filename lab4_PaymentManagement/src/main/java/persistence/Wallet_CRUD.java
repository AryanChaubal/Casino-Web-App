package persistence;

import helper.Wallet;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * CRUD Operations for Wallet Management
 * Supports Deposit, Withdraw, Bet, Retrieve Balance, and Transaction Logging
 */
public class Wallet_CRUD {

    /**
     * Establish Database Connection
     */
    private static Connection getCon() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  // MySQL 8+ driver
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/PaymentDB?useTimezone=true&serverTimezone=UTC",
                    "root", "student123");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Database connection error: " + e);
        }
        return con;
    }

    /**
     * Retrieve Wallet by User ID
     * @param user_id User ID
     * @return Wallet object or null if not found
     */
    public static Wallet read(int user_id) {
        Wallet wallet = null;
        try (Connection con = getCon()) {
            String query = "SELECT * FROM Wallet WHERE user_id = ?;";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int wallet_id = rs.getInt("wallet_id");
                float balance = rs.getFloat("balance");
                wallet = new Wallet(wallet_id, user_id, balance);
            }
        } catch (SQLException e) {
            System.out.println("Error reading wallet: " + e);
        }
        return wallet;
    }

    /**
     * Update Wallet Balance
     * @param user_id User ID
     * @param balance New balance
     * @return Success or failure message
     */
    public static String update(int user_id, float balance) {
        try (Connection con = getCon()) {
            String query = "UPDATE Wallet SET balance = ? WHERE user_id = ?;";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setFloat(1, balance);
            ps.setInt(2, user_id);
            
            int rowsUpdated = ps.executeUpdate();
            return (rowsUpdated > 0) ? "Balance updated successfully!" : "Error updating balance";
        } catch (SQLException e) {
            System.out.println("Error updating wallet balance: " + e);
        }
        return "Error updating balance";
    }

    /**
     * Deposit Funds into Wallet
     * @param user_id User ID
     * @param amount Amount to deposit
     * @return Success or failure message
     */
    public static String deposit(int user_id, float amount) {
        Wallet wallet = read(user_id);

        if (wallet == null) {
            // If wallet doesn't exist, create it
            createWallet(new Wallet(0, user_id, amount));
        } else {
            // Add deposit amount to the balance
            float newBalance = wallet.getBalance() + amount;
            update(user_id, newBalance);
        }

        // Log the transaction
        logTransaction(user_id, "DEPOSIT", amount);

        return "Deposit successful!";
    }

    /**
     * Withdraw Funds from Wallet
     * @param user_id User ID
     * @param amount Amount to withdraw
     * @return Success or failure message
     */
    public static String withdraw(int user_id, float amount) {
        Wallet wallet = read(user_id);

        if (wallet == null) {
            return "Wallet not found";
        }

        if (wallet.getBalance() < amount) {
            return "Insufficient funds";
        }

        float newBalance = wallet.getBalance() - amount;
        update(user_id, newBalance);

        // Log the transaction
        logTransaction(user_id, "WITHDRAW", amount);

        return "Withdrawal successful!";
    }

    /**
     * Place a Bet
     * @param user_id User ID
     * @param betAmount Bet amount
     * @return Success or failure message
     */
    public static String bet(int user_id, float betAmount) {
        Wallet wallet = read(user_id);

        if (wallet == null) {
            return "Wallet not found";
        }

        if (wallet.getBalance() < betAmount) {
            return "Insufficient funds";
        }

        float newBalance = wallet.getBalance() - betAmount;
        update(user_id, newBalance);

        // Log the transaction
        logTransaction(user_id, "BET", betAmount);

        return "Bet placed successfully!";
    }

    /**
     * Create a new Wallet for the user
     * @param wallet Wallet object
     */
    public static void createWallet(Wallet wallet) {
        try (Connection con = getCon()) {
            String query = "INSERT INTO Wallet (user_id, balance) VALUES (?, ?);";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, wallet.getUser_id());
            ps.setFloat(2, wallet.getBalance());
            
            ps.executeUpdate();
            System.out.println("Wallet created successfully!");
        } catch (SQLException e) {
            System.out.println("Error creating wallet: " + e);
        }
    }

    /**
     * Log Transactions
     * @param user_id User ID
     * @param type Transaction type (DEPOSIT, WITHDRAW, BET)
     * @param amount Transaction amount
     */
    public static void logTransaction(int user_id, String type, float amount) {
        try (Connection con = getCon()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.now();

            String query = "INSERT INTO Transaction (user_id, transaction_type, amount, transaction_date) " +
                           "VALUES (?, ?, ?, ?);";
            
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, user_id);
            ps.setString(2, type);
            ps.setFloat(3, amount);
            ps.setString(4, date.format(formatter));
            
            ps.executeUpdate();
            System.out.println("Transaction logged successfully!");
        } catch (SQLException e) {
            System.out.println("Error logging transaction: " + e);
        }
    }
}
