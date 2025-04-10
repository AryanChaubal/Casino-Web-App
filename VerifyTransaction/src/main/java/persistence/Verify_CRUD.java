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
import helper.*;
/**
 *
 * @author student
 */
public class Verify_CRUD {
    
    
    public static Connection getCon() throws ClassNotFoundException, SQLException{
       Connection con=null;
     try{
         Class.forName("com.mysql.jdbc.Driver");
        String connection=System.getenv("DB_URL");
        //String connection ="localhost:3306";
         con=DriverManager.getConnection("jdbc:mysql://"+connection+"/verify_CARS?allowPublicKeyRetrieval=true&useSSL=false", "root", "student" );
        
         
         System.out.println("Connection established.");
     }
     catch(Exception e){ System.out.println(e);}
     return con;
     
    }
    
    public static Set<TransactionVerify> getVerifiedTransactions(String accountId){
        Set<TransactionVerify> trans= new HashSet<TransactionVerify>();
        try{
            Connection con= getCon();
            String q;
            if(accountId.isEmpty()){
               q="select * from Verify "
                 +";";
            }
            else
             q = "select * from Verify "
                    + " WHERE accountId LIKE '"+accountId+"'"+";";

			PreparedStatement ps=con.prepareStatement(q);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){

				String transactionId=rs.getString("transactionId");
			
                                String date=rs.getDate("verifyDate").toString();
                                
                                
                                TransactionVerify tran = new TransactionVerify(transactionId,date, accountId);
                                trans.add(tran);
                                
                                }
			
			con.close();

		}catch(Exception e){System.out.println(e);}
           
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>"+trans.size());
        return trans;
        
    }
    
    public static TransactionVerify getVerifiedTransactions(String accountId, String transactionId){
        TransactionVerify tran=null;
        try{
            Connection con= getCon();
            
            String q = "select * from Verify "
                    + " WHERE accountId LIKE '"+accountId+"'"+" and "
                    + "transactionId LIKE '"+transactionId+"';";

			PreparedStatement ps=con.prepareStatement(q);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){

				
				
                                String date=rs.getDate("verifyDate").toString();
                                
                                
                                 tran = new TransactionVerify(transactionId,date,accountId);
                                
                                
                                }
			
			con.close();

		}catch(Exception e){System.out.println(e);}
            
    
        return tran;
        
    }
    
    public static boolean verify(String accountId, String transactionId){
      
        try{
            Connection con= getCon();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
             LocalDate date = LocalDate.now();
            System.out.println(date.format(formatter));
            String q = "insert into Verify "
                    + "(transactionId, accountId, verifyDate) values "
                    + "('"+transactionId+"', '"+accountId+"', '"+date.format(formatter)+"');";
            Statement stmt = con.createStatement(); 
            stmt.execute(q);
            
			con.close();
                        return true;

		}catch(Exception e){System.out.println(e);
                return false;
                }
 
        
    }
}
