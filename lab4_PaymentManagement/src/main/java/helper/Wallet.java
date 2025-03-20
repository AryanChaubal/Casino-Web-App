/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helper;

/**
 *
 * @author Ethan
 */
public class Wallet {
    int wallet_id;
    int user_id;
    float balance;

    public int getWallet_id() {
        return wallet_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
    
    
    public Wallet(int wallet_id, int user_id, float balance) {
        this.wallet_id = wallet_id;
        this.user_id = user_id;
        this.balance = balance;
    }
}
