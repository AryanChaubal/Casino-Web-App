package endpoint;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import business.Register;

@Path("/register")
public class RegisterEndpoint {

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response registerUser(
        @FormParam("username") String username, 
        @FormParam("password") String password, 
        @FormParam("email") String email) {

        Register register = new Register();

        boolean success = register.registerUser(username, password, email);

        if (success) {
            return Response.status(Response.Status.CREATED)
                    .entity("Registration successful")
                    .build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Registration failed.")
                    .build();
        }
    }
}
