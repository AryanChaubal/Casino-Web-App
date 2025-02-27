package casino.helper;

/**
 * Class to represent user information.
 */
public class UserInfo {
    
    private String username;
    private String password;
    private String email;
    private float balance;

    // Getter methods
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public float getBalance() {
        return this.balance;
    }

    // Setter methods
    public void setBalance(float amount) {
        this.balance = amount;
    }

    public void incrementBalance(float amount) {
        this.balance += amount;
    }

    // Constructor to initialize the user info
    public UserInfo(String username, String password, String email, float balance) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.balance = balance;
    }
}
