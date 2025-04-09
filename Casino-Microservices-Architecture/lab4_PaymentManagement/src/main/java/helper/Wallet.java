package helper;

public class Wallet {
    private int walletId;
    private String username;
    private double balance;

    public Wallet() {}

    public Wallet(int walletId, String username, double balance) {
        this.walletId = walletId;
        this.username = username;
        this.balance = balance;
    }

    // Getters and setters
    public int getWalletId() {
        return walletId;
    }

    public void setWalletId(int walletId) {
        this.walletId = walletId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}