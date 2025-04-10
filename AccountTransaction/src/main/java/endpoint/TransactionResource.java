/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package endpoint;

import io.kubemq.sdk.basic.ServerAddressNotSuppliedException;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.SQLException;

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
 * REST Web Service
 *
 * @author student
 */
@Path("Transaction")
public class TransactionResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of TransactionResource
     */
    public TransactionResource() {
    }

    /**
     * Retrieves representation of an instance of endpoint.TransactionResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml(@PathParam("transactionId") String transactionId) {
        //TODO return proper representation object
        TransactionBusiness hold = new TransactionBusiness();
        Transaction tran = hold.getTransaction(transactionId);
        if(tran == null){
            return("");
        }
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(Transaction.class);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(tran, sw);

            return (sw.toString());

        } catch (JAXBException ex) {
            Logger.getLogger(TransactionResource.class.getName()).log(Level.SEVERE, null, ex);
            return ("error happened");
        }
    }
    
    @POST
    @Produces(MediaType.TEXT_HTML)
    @Path("update")
    public String updateTransaction(@FormParam("tranid") String transactionId, @FormParam("userid") String accountId,@FormParam("ammount") String ammount) throws InterruptedException, ServerAddressNotSuppliedException{
        TransactionBusiness transaction = new TransactionBusiness();
        boolean ts;
        try{
            try {
                ts = transaction.Transaction(transactionId, accountId,ammount);
            } catch (ServerAddressNotSuppliedException ex) {
                Logger.getLogger(TransactionResource.class.getName()).log(Level.SEVERE, null, ex);
            }
            return("Inserted");
        }catch(ClassNotFoundException | SQLException | IOException ex){
            Logger.getLogger(TransactionResource.class.getName()).log(Level.SEVERE, null, ex);
            return (ex.getMessage());
        }
        
    }
}