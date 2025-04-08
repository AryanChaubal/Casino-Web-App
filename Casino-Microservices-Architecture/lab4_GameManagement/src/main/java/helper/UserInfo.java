package helper;

public class UserInfo {
    private int userId;
    private String username;
    private double balance;

    public UserInfo() {}

    public UserInfo(int userId, String username, double balance) {
        this.userId = userId;
        this.username = username;
        this.balance = balance;
    }

    // Getters and setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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