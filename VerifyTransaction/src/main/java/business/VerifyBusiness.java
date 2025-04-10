/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import static java.lang.System.in;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.commons.codec.binary.Base64;
import helper.TransactionVerify;
import persistence.*;



/**
 *
 * @author student
 */
public class VerifyBusiness {

    
    public VerifyBusiness(){
        
    }
    public  VerifyXML getTransactionsByQuery(String userId){
       Set<TransactionVerify> transactions = Verify_CRUD.getVerifiedTransactions(userId);
       
       
        VerifyXML rs;
        rs = new VerifyXML();
        rs.setTransaction(new ArrayList(transactions));
        return (rs);
    }
    
    
    
    public  VerifyXML getTransactions(){
        Set<TransactionVerify> transactions = Transaction_CRUD.getTransactions();
       
       VerifyXML rs;
        rs = new VerifyXML();
        rs.setTransaction(new ArrayList(transactions));
        return (rs);
    }
  
}