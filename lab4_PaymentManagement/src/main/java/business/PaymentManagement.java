/*
 * Business Layer for Payment Management
 */
package business;

import persistence.Wallet_CRUD;
import helper.Wallet;
import helper.WalletXML;

/**
 * Payment Management Class
 * Handles deposit, withdrawal, betting, and balance retrieval operations.
 */
public class PaymentManagement {

    /**
     * Method to deposit funds into the wallet.
     * @param userId The ID of the user.
     * @param amount The deposit amount.
     * @return WalletXML representation of the updated wallet.
     */
    public WalletXML deposit(int userId, float amount) {

        // Deposit funds using Wallet_CRUD
        String result = Wallet_CRUD.deposit(userId, amount);

        if (!result.equals("Deposit successful!")) {
            return null;  // Return null if the deposit fails
        }

        // Fetch updated wallet
        Wallet wallet = Wallet_CRUD.read(userId);

        if (wallet == null) {
            return null;  // Wallet not found
        }

        // Convert to WalletXML format
        return convertToXML(wallet);
    }

    /**
     * Method to withdraw funds from the wallet.
     * @param userId The ID of the user.
     * @param amount The withdrawal amount.
     * @return WalletXML representation of the updated wallet or null if insufficient funds.
     */
    public WalletXML withdraw(int userId, float amount) {

        // Withdraw funds using Wallet_CRUD
        String result = Wallet_CRUD.withdraw(userId, amount);

        if (!result.equals("Withdrawal successful!")) {
            return null;  // Return null if insufficient funds or wallet not found
        }

        // Fetch updated wallet
        Wallet wallet = Wallet_CRUD.read(userId);

        if (wallet == null) {
            return null;  // Wallet not found
        }

        // Convert to WalletXML format
        return convertToXML(wallet);
    }

    /**
     * Method to place a bet.
     * @param userId The ID of the user.
     * @param betAmount The betting amount.
     * @return WalletXML representation of the updated wallet or null if insufficient funds.
     */
    public WalletXML bet(int userId, float betAmount) {

        // Place a bet using Wallet_CRUD
        String result = Wallet_CRUD.bet(userId, betAmount);

        if (!result.equals("Bet placed successfully!")) {
            return null;  // Return null if insufficient funds or wallet not found
        }

        // Fetch updated wallet
        Wallet wallet = Wallet_CRUD.read(userId);

        if (wallet == null) {
            return null;  // Wallet not found
        }

        // Convert to WalletXML format
        return convertToXML(wallet);
    }

    /**
     * Method to retrieve the current wallet balance.
     * @param userId The ID of the user.
     * @return WalletXML representation of the current balance or null if wallet doesn't exist.
     */
    public WalletXML getWalletBalance(int userId) {

        // Fetch wallet from Wallet_CRUD
        Wallet wallet = Wallet_CRUD.read(userId);

        if (wallet == null) {
            return null;  // Wallet not found
        }

        // Convert wallet to WalletXML format
        return convertToXML(wallet);
    }
    
    /**
 * Method to retrieve the current wallet balance.
 * @param userId The ID of the user.
 * @return WalletXML representation of the current balance or null if the wallet doesn't exist.
 */
public WalletXML getWallet(int userId) {

    // Fetch the wallet using Wallet_CRUD
    Wallet wallet = Wallet_CRUD.read(userId);

    if (wallet == null) {
        return null;  // Wallet not found
    }

    // Convert to WalletXML format
    WalletXML walletXML = new WalletXML();
    walletXML.setUserId(wallet.getUser_id());
    walletXML.setBalance(wallet.getBalance());

    return walletXML;
}

    /**
     * Helper method to convert Wallet to WalletXML format.
     * @param wallet The wallet object.
     * @return WalletXML object.
     */
    private WalletXML convertToXML(Wallet wallet) {
        WalletXML walletXML = new WalletXML();
        walletXML.setUserId(wallet.getUser_id());
        walletXML.setBalance(wallet.getBalance());
        return walletXML;
    }
}
