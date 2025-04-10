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
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import helper.TransactionVerify;
/**
 *
 * @author student
 */
public class Transaction_CRUD {
    
    public static Connection getCon() throws ClassNotFoundException, SQLException{
       Connection con=null;
     try{
         Class.forName("com.mysql.jdbc.Driver");
        String connection=System.getenv("DB_URL");
        //String connection ="localhost:3306";
         con=DriverManager.getConnection("jdbc:mysql://"+connection+"/rent_CARS?allowPublicKeyRetrieval=true&useSSL=false", "root", "student" );
        
         
         System.out.println("Connection established.");
     }
     catch(Exception e){ System.out.println(e);}
     return con;
     
    }
    
    public static boolean isPending(String transactionId){
       boolean result;
        try{
            Connection con= getCon();
            
        
            
            String q = "select * from Transaction "
                    + " WHERE transactionId LIKE '"+transactionId+"';";

			PreparedStatement ps=con.prepareStatement(q);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){

			result=true;
                                
                                }
                        else
                            result=false;
			
			con.close();

		}catch(Exception e){return false;}
            return result;
    }
    
     public static Set<TransactionVerify>  getTransactions(){
        Set<TransactionVerify> trans= new HashSet<TransactionVerify>();
        

        try{
            Connection con= getCon();
            
        
            
            String q = "select * from Transaction "
                    +";";
                        System.out.println(q);
			PreparedStatement ps=con.prepareStatement(q);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){

				String transactionId=rs.getString("transactionId");
			
                                 
                                
                                TransactionVerify tran = new TransactionVerify(transactionId,null, null);
                                trans.add(tran);
                                
                                }
			
			
			con.close();

		}catch(Exception e){return trans;}
            return trans;
    }
     
     public static void addTransaction(String transactionId) throws ClassNotFoundException, SQLException{
      
        
            Connection con= getCon();
          
            String q = "insert into Transaction "
                    + "(transactionId) values "
                    + "('"+transactionId+"');";
            Statement stmt = con.createStatement(); 
           
            stmt.execute(q);
			con.close();
           
    }
     
     public static void addTransaction(String transactionId, String accountId, String ammount , String date) throws ClassNotFoundException, SQLException{
      
        
            Connection con= getCon();
          
            String q = "insert into Car_Hold "
                    + "(transactionId, accountId, ammount, date) values "
                    + "("+
                    "'" +transactionId+"'"+ ","
                    +"'"+accountId+"'" + ","
                    +"'"+ammount+"'" + ","
                    +"'"+date+"'"
                    +"');";
            Statement stmt = con.createStatement(); 
           
            stmt.execute(q);
			con.close();
    }
}
