package endpoint;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import business.SlotMachineService;
import business.SlotResult;
import helper.UserInfo;
import persistence.User_CRUD;

@Path("/slotmachine")
public class SlotMachineResource {

    @POST
    @Path("/play")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public SlotResult playSlotMachine(PlayRequest request) {
        UserInfo user = User_CRUD.getUserByUsername(request.getUsername());
        if (user == null) {
            throw new NotFoundException("User not found");
        }

        SlotMachineService slotMachineService = new SlotMachineService();
        SlotResult result = slotMachineService.playSlotMachine(user, request.getBet());

        // Update user balance in the database
        User_CRUD.updateBalance(user.getUserId(), result.getBalance());

        return result;
    }
}