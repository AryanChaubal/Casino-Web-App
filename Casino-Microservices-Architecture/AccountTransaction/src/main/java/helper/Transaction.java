/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author Ethan
 */
@XmlRootElement(name = "transaction")
@XmlAccessorType(XmlAccessType.FIELD)
public class Transaction {
    private String transactionId, accountId;
    private String ammount, TransactionDate;

    public Transaction(String transactionId, String accountId, String ammount, String TransactionDate) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.ammount = ammount;
        this.TransactionDate = TransactionDate;
    }
   

    public Transaction() {
        this.transactionId = "";
        this.accountId = "";
        this.TransactionDate = "";
        this.ammount = "";
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getAmmount() {
        return ammount;
    }

    public String getTransactionDate() {
        return TransactionDate;
    }

    public String getAccountId() {
        return accountId;
    }
    
}
