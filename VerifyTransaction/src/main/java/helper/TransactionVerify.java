/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
/**
 *
 * @author student
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class TransactionVerify {
    
    
    private String transactionId, verifyDate, accountId;
    
    
    public TransactionVerify(){
        this.transactionId = null;
        this.verifyDate = null;
        this.accountId = null;
    }
    
    public TransactionVerify(String transactionId, String verifyDate, String accountId){
        this.transactionId = transactionId;
        this.verifyDate = verifyDate;
        this.accountId = accountId;
    }
    
    public String getTransactionId(){
        return transactionId;
    }
    
    public String getVerifyDate(){
        return verifyDate;
    }
    
    public String getAccount(){
        return accountId;
    }
}
