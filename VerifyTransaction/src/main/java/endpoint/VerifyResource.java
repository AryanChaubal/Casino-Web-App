/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package endpoint;

import io.kubemq.sdk.basic.ServerAddressNotSuppliedException;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import business.*;
import helper.*;
import persistence.*;

/**
 * REST Web Service
 *
 * @author student
 */
@Path("verify")
public class VerifyResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of VerifyResource
     */
    public VerifyResource() {
    }

    /**
     * Retrieves representation of an instance of endpoint.VerifyResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("getTransaction")
    public String getXml() {
        
        VerifyBusiness verify = new VerifyBusiness();
        VerifyXML trans = verify.getTransactions();
       
        JAXBContext jaxbContext;
        
        try{
            jaxbContext = JAXBContext.newInstance(VerifyXML.class);
            
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(trans, sw);
            
            return sw.toString();
        }catch(JAXBException ex){
            Logger.getLogger(VerifyResource.class.getName()).log(Level.SEVERE, null, ex);
            return ("error happened");
        }
    }
    
    /**
     * 
     * Retrieves representation of an instance of ryerson.ca.searchtran.endpoint.SearchResource
     * 
     */
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("getTransByUser")
    public String getborrowedXml() {
        VerifyBusiness verify = new VerifyBusiness();
        VerifyXML trans = verify.getTransactionsByQuery("");
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(VerifyXML.class);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(trans, sw);

            return (sw.toString());

        } catch (JAXBException ex) {
            Logger.getLogger(VerifyResource.class.getName()).log(Level.SEVERE, null, ex);
            return ("error happened");
        }

    }
    
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("update")
    public String updateCarHold() {

        try {
            Transaction_CRUD.addTransaction("123414");
            return ("Inserted");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VerifyResource.class.getName()).log(Level.SEVERE, null, ex);
            return (ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(VerifyResource.class.getName()).log(Level.SEVERE, null, ex);
            return (ex.getMessage());
        }

    }
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("isVerifyed")
    public String isVerifyed() {
        VerifyBusiness verify = new VerifyBusiness();
        VerifyXML tran = verify.getTransactionsByQuery("John123");

        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(TransactionVerify.class);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(tran, sw);

            return (sw.toString());

        } catch (JAXBException ex) {
            Logger.getLogger(VerifyResource.class.getName()).log(Level.SEVERE, null, ex);
            return ("error happened");
        }
    }

    /**
     * PUT method for updating or creating an instance of VerifyResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
