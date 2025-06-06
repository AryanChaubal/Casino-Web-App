package helper;

public class UserInfo {
    private String username;
    private String password;
    private String email;
    private float balance;

    public UserInfo(String username, String password, String email, float balance) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.balance = balance;
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}
