package business;

import helper.Wallet;
import helper.WalletRequest;
import persistence.Wallet_CRUD;
import persistence.Transaction_CRUD;

public class WalletBusiness {

    // Get account balance for a specific user
    public double getBalance(String username) {
        Wallet wallet = Wallet_CRUD.getWalletByUsername(username);
        if (wallet != null) {
            return wallet.getBalance();
        }
        return -1; // User not found
    }

    // Deposit funds into a user's wallet
    public boolean deposit(WalletRequest request) {
        Wallet wallet = Wallet_CRUD.getWalletByUsername(request.getUsername());
        if (wallet == null) {
            return false; // User not found
        }
        boolean balanceUpdated = Wallet_CRUD.updateBalance(wallet.getWalletId(), request.getAmount());
        if (balanceUpdated) {
            return Transaction_CRUD.addTransaction(wallet.getWalletId(), "DEPOSIT", request.getAmount());
        }
        return false;
    }

    // Withdraw funds from a user's wallet
    public boolean withdraw(WalletRequest request) {
        Wallet wallet = Wallet_CRUD.getWalletByUsername(request.getUsername());
        if (wallet == null || wallet.getBalance() < request.getAmount()) {
            return false; // User not found or insufficient balance
        }
        boolean balanceUpdated = Wallet_CRUD.updateBalance(wallet.getWalletId(), -request.getAmount());
        if (balanceUpdated) {
            return Transaction_CRUD.addTransaction(wallet.getWalletId(), "WITHDRAWAL", request.getAmount());
        }
        return false;
    }
    
}