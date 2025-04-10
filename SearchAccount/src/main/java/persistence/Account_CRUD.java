/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;
import helper.*;

/**
 *
 * @author student
 */
public class Account_CRUD {
    private static Connection getCon() {
        Connection con = null;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            String connection = System.getenv("DB_URL");
            //  String connection ="localhost:3306";
            con = DriverManager.getConnection("jdbc:mysql://" + connection
                    + "/CasinoSearchService?allowPublicKeyRetrieval=true&useSSL=false", "root", "student");

            System.out.println("Connection established.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
    
    public static Set<Account> searchForAccounts(String query){
        Set<Account> accounts= new HashSet<Account>();
        try{
            Connection con= getCon();
            
            String q = "SELECT * FROM ACCOUNT NATURAL JOIN BET "
                    + "WHERE username LIKE '%" + query + "%' "
                    + "OR game LIKE '%" + query + "%';";
            
System.out.println(q);
			PreparedStatement ps=con.prepareStatement(q);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				//been= new UserInfo();
				int accountID = rs.getInt("accountID");
                                String username = rs.getString("username");
                                double balance = rs.getDouble("balance");
                                String game = rs.getString("game");
                                double amount = rs.getDouble("amount");
                                String outcome = rs.getString("outcome");
                                Bet b= new Bet(game, amount, outcome);
                           
                                Account account = new Account(accountID,username, balance, b);
                                accounts.add(account);
                                
                                }
			
			con.close();

		}catch(Exception e){System.out.println(e);}
            
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>"+accounts.size());
        return accounts;
        
    }
}
