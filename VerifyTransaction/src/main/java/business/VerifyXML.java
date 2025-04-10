/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import helper.TransactionVerify;


 @XmlRootElement
       public class VerifyXML{
           private ArrayList<TransactionVerify> transactions;
           @XmlElementWrapper
           @XmlElement(name="transactionVerified")
           public List<TransactionVerify>getTransactions(){
               return transactions;
               
           }
           public VerifyXML(){
               
               
           }
           public void setTransaction(ArrayList<TransactionVerify> rs){
               transactions=rs;
               
           }
           
       }
