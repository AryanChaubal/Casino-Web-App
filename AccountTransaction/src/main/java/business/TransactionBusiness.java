package business;


import io.kubemq.sdk.basic.ServerAddressNotSuppliedException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import helper.*;
import persistence.*;
/**
 *
 * @author student
 */
public class    TransactionBusiness {
    
    public TransactionBusiness(){
        
    }
    
    public Transaction getTransaction(String TransactionId){
        
        Transaction ts = Transaction_CRUD.getTransaction(TransactionId);
        return ts;
    }
    
    public boolean Transaction(String TransactionId, String accountId, String ammount) throws ClassNotFoundException, SQLException, 
        ServerAddressNotSuppliedException, IOException, InterruptedException{
        boolean success = false;
        
        success = Transaction_CRUD.addTransaction(TransactionId, accountId, ammount);
        if(success){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.now();
            
            LocalDate exDate = date.plusDays(3);
            
            Messaging.sendmessage("Transaction: "+TransactionId+":"+accountId+":"+ammount+":"+exDate.format(formatter));
        }
        return success;
    }
}
