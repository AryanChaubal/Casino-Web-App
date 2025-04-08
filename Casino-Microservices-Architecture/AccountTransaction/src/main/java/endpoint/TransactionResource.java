/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package endpoint;

import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import business.*;
import helper.*;

/**
 *
 * @author student
 */
@Path("transaction")
public class TransactionResource {
   
    @Context
    private UriInfo context;
    
    public TransactionResource(){
        
    }
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("isActive/{accountId}")
    public String getXml(@PathParam("accountId") String accountId){
        System.out.println(accountId);
        TransactionBusiness rent = new TransactionBusiness();
        Transaction trans = rent.getTransaction(accountId);
        if (trans == null) {
            return("");
        }
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(Transaction.class);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(trans, sw);

            return (sw.toString());

        } catch (JAXBException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
            return ("error happened");
        }
    }
    
    @POST
    @Produces(MediaType.TEXT_HTML)
    @Path("update")
    public String updateCarRent(@FormParam("transactionId") String transactionId, @FormParam("accountId") String accountId, @FormParam("ammount") String ammount){
        TransactionBusiness trans = new TransactionBusiness();
        boolean cs = trans.Transaction(transactionId, accountId, ammount);
        if(cs){
            return("inserted");
        }else{
            return("not inserted");
        }
    }
}
