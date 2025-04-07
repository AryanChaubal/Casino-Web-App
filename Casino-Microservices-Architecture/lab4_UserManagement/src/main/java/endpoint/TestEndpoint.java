package endpoint;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import business.register;

@Path("/test")
public class TestEndpoint {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response test() {
        System.out.print("working");
        return Response.status(Response.Status.OK).entity("working").build();
    }
}
