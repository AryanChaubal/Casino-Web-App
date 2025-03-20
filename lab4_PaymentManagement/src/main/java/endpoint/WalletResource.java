package endpoint;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import business.WalletBusiness;
import helper.WalletRequest;

@Path("/wallet")
public class WalletResource {

    // Get account balance for a specific user
    @GET
    @Path("/{username}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getAccountBalance(@PathParam("username") String username) {
        WalletBusiness business = new WalletBusiness();
        double balance = business.getBalance(username);
        if (balance == -1) {
            return "User not found";
        }
        return "Current balance for " + username + ": " + balance;
    }

    // Deposit funds into a user's wallet
    @POST
    @Path("/deposit")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String depositFunds(WalletRequest request) {
        WalletBusiness business = new WalletBusiness();
        boolean success = business.deposit(request);
        return success ? "Deposit successful" : "Deposit failed";
    }

    // Withdraw funds from a user's wallet
    @POST
    @Path("/withdraw")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String withdrawFunds(WalletRequest request) {
        WalletBusiness business = new WalletBusiness();
        boolean success = business.withdraw(request);
        return success ? "Withdrawal successful" : "Withdrawal failed";
    }
}