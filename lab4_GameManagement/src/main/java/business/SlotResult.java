package business;

public class SlotResult {
    private String[] result;
    private double winnings;
    private double balance;

    public SlotResult(String[] result, double winnings, double balance) {
        this.result = result;
        this.winnings = winnings;
        this.balance = balance;
    }

    // Getters
    public String[] getResult() {
        return result;
    }

    public double getWinnings() {
        return winnings;
    }

    public double getBalance() {
        return balance;
    }
}