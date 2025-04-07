/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

import helper.*;
import persistence.*; 

/**
 *
 * @author Ethan
 */
public class TransactionBusiness {
    public Transaction getTransaction(String transactionId) {
        Transaction ts = Transaction_CRUD.getTransaction(transactionId);

        return (ts);
    }

    public boolean Transaction(String transactionId, String accountId, String ammount) {
       
        return (Transaction_CRUD.addTransaction(transactionId, accountId, ammount));
    }
}
