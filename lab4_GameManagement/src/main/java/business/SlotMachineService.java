package business;

import helper.UserInfo;
import java.util.Random;

public class SlotMachineService {

    private static final String[] SYMBOLS = {"Cherry", "Lemon", "Orange", "Plum", "Bell", "Bar", "Seven"};
    private static final Random random = new Random();
    private static final int WIN_MULTIPLIER = 81;

    public SlotResult playSlotMachine(UserInfo user, double bet) {
        if (bet <= 0 || bet > user.getBalance()) {
            throw new IllegalArgumentException("Invalid bet amount");
        }

        double balance = user.getBalance();
        balance -= bet;

        String[] result = spinReels();
        double winnings = calculateWinnings(result, bet);
        balance += winnings;

        user.setBalance(balance);

        return new SlotResult(result, winnings, balance);
    }

    private String[] spinReels() {
        return new String[]{
            SYMBOLS[random.nextInt(SYMBOLS.length)],
            SYMBOLS[random.nextInt(SYMBOLS.length)],
            SYMBOLS[random.nextInt(SYMBOLS.length)]
        };
    }

    private double calculateWinnings(String[] result, double bet) {
        return (result[0].equals(result[1]) && result[1].equals(result[2])) ? bet * WIN_MULTIPLIER : 0;
    }
}