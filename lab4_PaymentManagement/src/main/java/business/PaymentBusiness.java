package business;

import java.util.Set;
import java.util.ArrayList;
import helper.*;
import persistence.*;

public class PaymentBusiness {

    // Deposit money into the user's wallet
    public boolean deposit(int walletId, double amount) {
        if (amount <= 0) {
            return false; // Invalid amount
        }
        boolean transactionAdded = Transaction_CRUD.addTransaction(walletId, "DEPOSIT", amount, "SUCCESS");
        if (transactionAdded) {
            return Wallet_CRUD.updateBalance(walletId, amount); // Increase balance
        }
        return false;
    }

    // Withdraw money from the user's wallet
    public boolean withdraw(int walletId, double amount) {
        double currentBalance = Wallet_CRUD.getBalance(walletId);
        if (amount <= 0 || amount > currentBalance) {
            return false; // Invalid amount or insufficient balance
        }
        boolean transactionAdded = Transaction_CRUD.addTransaction(walletId, "WITHDRAWAL", amount, "SUCCESS");
        if (transactionAdded) {
            return Wallet_CRUD.updateBalance(walletId, -amount); // Decrease balance
        }
        return false;
    }

    // Get the user's current wallet balance
    public double getBalance(int walletId) {
        return Wallet_CRUD.getBalance(walletId);
    }

    // Get all transactions for a wallet
    public TransactionsXML getTransactionsByWallet(int walletId) {
        Set<Transaction> transactions = Transaction_CRUD.getTransactionsByWallet(walletId);
        TransactionsXML transactionsXML = new TransactionsXML();
        transactionsXML.setTransactions(new ArrayList<>(transactions));
        return transactionsXML;
    }
}