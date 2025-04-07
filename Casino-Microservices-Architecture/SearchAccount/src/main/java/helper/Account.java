/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author student
 */

@XmlRootElement(name = "account")
@XmlAccessorType(XmlAccessType.FIELD)
public class Account {
    private int accountId;
    private String username;
    private double balance;
    private ArrayList<Bet> bets;

    public Account() {}

    public Account(int accountId, String username, double balance, Bet bet) {
        this.accountId = accountId;
        this.username = username;
        this.balance = balance;
        this.bets = new ArrayList<Bet>();
        this.bets.add(new Bet(bet.getGame(), bet.getAmount(),bet.getOutcome()));
        
    }

    public int getAccountId() {
        return accountId;
    }

    public String getUsername() {
        return username;
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<Bet> getBets() {
        return bets;
    }

    public void addBet(ArrayList<Bet> bets){
        for(Bet b:bets){
            this.bets.add(new Bet(b.getGame(), b.getAmount(),b.getOutcome()));
        }
    }
}