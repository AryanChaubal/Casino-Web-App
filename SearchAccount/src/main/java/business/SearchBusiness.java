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
import helper.*;
import persistence.*;

/**
 *
 * @author student
 */
public class SearchBusiness {
    
    public  AccountsXML getAccountsByQuery(String query){
       Set<Account> accounts = Account_CRUD.searchForAccounts(query);
       Map<String ,Account> allBetsAccounts= new HashMap ();
           System.out.println("&&&&&&&&&&&&&&&&&&&&&&"+ accounts.size());
        for(Account acc : accounts){
            if(allBetsAccounts.containsKey(String.valueOf(acc.getAccountId()))){
                allBetsAccounts.get(String.valueOf(acc.getAccountId())).addBet(acc.getBets());
            } else {
                allBetsAccounts.put(String.valueOf(acc.getAccountId()), acc);
            }
        }

        System.out.println("**********************"+ allBetsAccounts.size());
        AccountsXML as;
        as = new AccountsXML();
        as.setCar(new ArrayList(allBetsAccounts.values()));
        return (as);
    }
    
      
}