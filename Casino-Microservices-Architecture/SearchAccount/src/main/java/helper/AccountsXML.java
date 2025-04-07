/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "accounts")
@XmlAccessorType (XmlAccessType.FIELD)
       public class AccountsXML{
     @XmlElement(name="car")
           private ArrayList<Account> accounts;
           
           
           public List<Account>getAccounts(){
               return accounts;
               
           }
          public  AccountsXML(){
               
               
           }
           public void setCar(ArrayList<Account> cs){
               accounts=cs;
               
           }
           
       }
