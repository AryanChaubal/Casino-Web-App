package endpoint;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import business.*;
import helper.*;

@Path("/payment")
public class PaymentResource {

    @POST
    @Path("/deposit")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String deposit(TransactionRequest request) {
        PaymentBusiness business = new PaymentBusiness();
        boolean success = business.deposit(request.getWalletId(), request.getAmount());
        return success ? "Deposit successful" : "Deposit failed";
    }

    @POST
    @Path("/withdraw")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String withdraw(TransactionRequest request) {
        PaymentBusiness business = new PaymentBusiness();
        boolean success = business.withdraw(request.getWalletId(), request.getAmount());
        return success ? "Withdrawal successful" : "Withdrawal failed";
    }

    @GET
    @Path("/balance/{walletId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getBalance(@PathParam("walletId") int walletId) {
        PaymentBusiness business = new PaymentBusiness();
        double balance = business.getBalance(walletId);
        return "Current balance: " + balance;
    }

    @GET
    @Path("/transactions/{walletId}")
    @Produces(MediaType.APPLICATION_XML)
    public TransactionsXML getTransactions(@PathParam("walletId") int walletId) {
        PaymentBusiness business = new PaymentBusiness();
        return business.getTransactionsByWallet(walletId);
    }
}