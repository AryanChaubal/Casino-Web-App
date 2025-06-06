package endpoint;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import business.register;

@Path("/register")
public class RegisterEndpoint {

    // Handle user registration
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response registerUser(
        @FormParam("username") String username, 
        @FormParam("password") String password, 
        @FormParam("email") String email) {

        // Create an instance of the register class to handle the registration logic
        register registerBusiness = new register();

        // We use an HttpServletRequest and HttpServletResponse object for the actual registration
        try {
            // Mimicking the Servlet registration logic here for the RESTful flow
            // Normally, you would not mix HttpServletRequest with a RESTful flow, but for simplicity, we proceed like this
            registerBusiness.doPost(null, null);
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred. Please try again.")
                    .build();
        }

        // Return the appropriate response based on the registration outcome
        return Response.status(Response.Status.CREATED)
                .entity("Registration successful")
                .build();
    }
}
