/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.casino;

/**
 *
 * @author student
 */
public class AccountInfo {
    
    String DisplayName;
    String AccountName;
    String Password;
    String Email;
    float Balance;

    public String getDisplayName() {
        return DisplayName;
    }

    public String getAccountName() {
        return AccountName;
    }

    public String getPassword() {
        return Password;
    }

    public String getEmail() {
        return Email;
    }
            
    public float getBalance(){
        return this.Balance;
    }
    
    public AccountInfo(String DisplayName, String AccountName, String Password, String Email){
        this.DisplayName = DisplayName;
        this.AccountName = AccountName;
        this.Password = Password;
        this.Email = Email;
        this.Balance = 0;
    }
}
