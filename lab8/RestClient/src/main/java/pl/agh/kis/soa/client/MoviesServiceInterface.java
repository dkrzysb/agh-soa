package pl.agh.kis.soa.client;

import pl.agh.kis.soa.model.Movie;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/users/{userId}/movies")
public interface MoviesServiceInterface {
    @GET
    @Produces("application/json")
    public Response getUserMovies(@PathParam("userId")Long userId);

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response addUserMovie(@PathParam("userId")Long userId, Movie movie);

    @PUT
    @Produces("application/json")
    @Consumes("application/json")
    public Response updateUserMovie(@PathParam("userId")Long userId, Movie movie);

    @PATCH
    @Produces("application/json")
    @Consumes("application/json")
    public Response updateUserMovieWithSelectedData(@PathParam("userId")Long userId, Movie movie);

    @DELETE
    @Path("{movieId}")
    @Produces("application/json")
    public Response deleteUserMovie(@PathParam("userId")Long userId, @PathParam("movieId")Long movieId);
}
