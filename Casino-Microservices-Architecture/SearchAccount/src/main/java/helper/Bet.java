/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helper;

/**
 *
 * @author Ethan
 */
public class Bet {
    private String game;         // e.g., "Blackjack", "Slots"
    private double amount;       // how much was bet
    private String outcome;      // e.g., "win", "loss", "draw"

    public Bet(String game, double amount, String outcome) {
        this.game = game;
        this.amount = amount;
        this.outcome = outcome;
    }

    public String getGame() {
        return game;
    }

    public double getAmount() {
        return amount;
    }

    public String getOutcome() {
        return outcome;
    }
    
    
}
