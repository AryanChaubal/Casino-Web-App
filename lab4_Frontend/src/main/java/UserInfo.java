public class UserInfo {

    private final String username; // Immutable field
    private final String password; // Immutable field
    private final String email;    // Immutable field
    private float balance;

    // Constructor to initialize the user info
    public UserInfo(String username, String password, String email, float balance) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty.");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty.");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty.");
        }
        this.username = username;
        this.password = password;
        this.email = email;
        this.balance = balance;
    }

    // Constructor with default balance (0)
    public UserInfo(String username, String password, String email) {
        this(username, password, email, 0.0f);
    }

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
        return balance;
    }

    // Setter methods
    public void setBalance(float amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Balance cannot be negative.");
        }
        this.balance = amount;
    }

    public void incrementBalance(float amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Increment amount cannot be negative.");
        }
        this.balance += amount;
    }

    // Override toString for better debugging and logging
    @Override
    public String toString() {
        return "UserInfo{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", balance=" + balance +
                '}';
    }
}