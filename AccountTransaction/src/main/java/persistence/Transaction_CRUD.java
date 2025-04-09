/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import helper.*;
import persistence.*; 

/**
 *
 * @author Ethan
 */
public class Transaction_CRUD {
    private static Connection getCon(){
    Connection con=null;
     try{
         
         Class.forName("com.mysql.cj.jdbc.Driver");
        //con=DriverManager.getConnection("jdbc:mysql://db:3306/hold_LBS?allowPublicKeyRetrieval=true&useSSL=false", "root", "student");
       
       con=DriverManager.getConnection("jdbc:mysql://localhost:3306/carrent?autoReconnect=true&useSSL=false", "root", "student");
        System.out.println("Connection established.");
     }
     catch(Exception e){ System.out.println(e);}
     return con;
    }
    
    public static boolean addTransaction(String transactionId, String accountId, String ammount){
      
        try{
            Connection con= getCon();
           DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
             LocalDate date = LocalDate.now();
            String q = "insert into Bet "
                    + "(transactionId, accountId, ammount, TransactionDate) values "
                    + "('"+transactionId+"', "
                    +"'"+accountId+"', "
                    +"'"+ammount+"', "
                    +"'"+date.format(formatter)+"');";
            Statement stmt = con.createStatement();   
            System.out.println(q);
            stmt.execute(q);
			con.close();
                        return true;

		}catch(Exception e){System.out.println(e);
                return false;
                }
    }
    
     public static Transaction getTransaction(String transactionId){
        Transaction trans=null;
        try{
            Connection con= getCon();
            
            String q = "select * from Bet "
                    + " WHERE "
                    
                    + "transactionId = '"+transactionId+"';";
            System.out.println(q);

			PreparedStatement ps=con.prepareStatement(q);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){

				
				
                                String date=rs.getDate("startdate").toString();
                                String ammount =rs.getString("ammount").toString();
                                String accountId=rs.getString("accountId").toString();
                                
                                
                                 trans = new Transaction(transactionId,accountId, ammount, date);
                                
                                
                                }
			
			con.close();

		}catch(Exception e){System.out.println(e);}
            
    
        return trans;
        
    }
     
     public static boolean addTransaction(String transactionId, String accountId, String ammount, String TransactionDate) {
      return( addTransaction(transactionId, accountId, ammount));
    }
}
