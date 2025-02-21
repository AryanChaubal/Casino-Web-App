/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

/**
 *
 * @author student
 */
public class UserInfo {
    
    String Username;
    String Password;
    String Email;
    float Balance;

    public String getUsername() {
        return Username;
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
    
    public UserInfo(String Username, String Password, String Email){
        this.Username = Username;
        this.Password = Password;
        this.Email = Email;
        this.Balance = 0;
    }
}
