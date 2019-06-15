package pl.agh.kis.soa.client;

import pl.agh.kis.soa.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/users")
public interface UsersServiceInterface {
    @GET
    @Produces("application/json")
    List<User> getAllUsers();

    @GET
    @Path("{userId}")
    @Produces("application/json")
    Response getUserById(@PathParam("userId")Long userId);

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    Response addUser(User user);

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    Response updateUser(User user);

    @DELETE
    @Path("{userId}")
    @Consumes("application/json")
    @Produces("application/json")
    Response deleteUser(@PathParam("userId")Long userId);
}
