/*
 * RESTful Web Service for Payment Management
 */
package endpoint;

import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import business.PaymentManagement;
import helper.WalletXML;

/**
 * RESTful Payment Web Service
 */
@Path("/payments")
public class PaymentResource {

    @Context
    private UriInfo context;

    public PaymentResource() {
    }

    /**
     * Endpoint to deposit funds
     * @param userId The ID of the user
     * @param amount The deposit amount
     * @return XML Response of the updated wallet
     */
    @Path("/deposit/{userId}/{amount}")
    @GET
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    public String depositFunds(@PathParam("userId") int userId, 
                               @PathParam("amount") float amount) {
        
        PaymentManagement paymentmanagement = new PaymentManagement();
        
        // Business logic for depositing funds
        WalletXML updatedWallet = paymentmanagement.deposit(userId, amount);

        if (updatedWallet == null) {
            return "<error>Transaction failed</error>";
        }

        // Convert WalletXML object to XML
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(WalletXML.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(updatedWallet, sw);

            return sw.toString();
            
        } catch (JAXBException ex) {
            Logger.getLogger(PaymentResource.class.getName()).log(Level.SEVERE, null, ex);
            return "<error>XML conversion failed</error>";
        }
    }

    /**
     * Endpoint to withdraw funds
     * @param userId The ID of the user
     * @param amount The withdrawal amount
     * @return XML Response of the updated wallet
     */
    @Path("/withdraw/{userId}/{amount}")
    @GET
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    public String withdrawFunds(@PathParam("userId") int userId, 
                                @PathParam("amount") float amount) {
        
        PaymentManagement paymentmanagement = new PaymentManagement();
        
        // Business logic for withdrawing funds
        WalletXML updatedWallet = paymentmanagement.withdraw(userId, amount);

        if (updatedWallet == null) {
            return "<error>Insufficient funds</error>";
        }

        // Convert WalletXML object to XML
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(WalletXML.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(updatedWallet, sw);

            return sw.toString();
            
        } catch (JAXBException ex) {
            Logger.getLogger(PaymentResource.class.getName()).log(Level.SEVERE, null, ex);
            return "<error>XML conversion failed</error>";
        }
    }
    
    /**
     * Endpoint to withdraw funds
     * @param userId The ID of the user
     * @param amount The withdrawal amount
     * @return XML Response of the updated wallet
     */
    @Path("/withdraw/{userId}/{amount}")
    @GET
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    public String Bet(@PathParam("userId") int userId, 
                                @PathParam("amount") float amount) {
        
        PaymentManagement paymentmanagement = new PaymentManagement();
        
        // Business logic for withdrawing funds
        WalletXML updatedWallet = paymentmanagement.bet(userId, amount);

        if (updatedWallet == null) {
            return "<error>Insufficient funds</error>";
        }

        // Convert WalletXML object to XML
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(WalletXML.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(updatedWallet, sw);

            return sw.toString();
            
        } catch (JAXBException ex) {
            Logger.getLogger(PaymentResource.class.getName()).log(Level.SEVERE, null, ex);
            return "<error>XML conversion failed</error>";
        }
    }
    /**
     * Endpoint to retrieve the current wallet balance
     * @param userId The ID of the user
     * @return XML Response with wallet balance
     */
    @Path("/wallet/{userId}")
    @GET
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    public String getWalletBalance(@PathParam("userId") int userId) {
        
        PaymentManagement paymentmanagement = new PaymentManagement();
        
        // Fetch wallet balance
        WalletXML wallet = paymentmanagement.getWallet(userId);

        if (wallet == null) {
            return "<error>Wallet not found</error>";
        }

        // Convert WalletXML object to XML
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(WalletXML.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(wallet, sw);

            return sw.toString();
            
        } catch (JAXBException ex) {
            Logger.getLogger(PaymentResource.class.getName()).log(Level.SEVERE, null, ex);
            return "<error>XML conversion failed</error>";
        }
    }
}
