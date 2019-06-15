package pl.agh.kis.soa.crud.services;

import io.swagger.annotations.Api;
import pl.agh.kis.soa.crud.model.Database;
import pl.agh.kis.soa.crud.model.Movie;
import pl.agh.kis.soa.crud.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/users/{userId}/movies")
@Api(value = "movies")
@Produces("application/json")
public class MoviesService {
    @GET
    public Response getUserMovies(@PathParam("userId")Long userId) {
        Optional<User> dbUser = getUserById(userId);

        if(!dbUser.isPresent())
            return Response.status(404).entity("Użytkownik o podanym ID nie istnieje.").build();
        return Response.status(200).entity(dbUser.get().getMovies()).build();
    }

    @GET
    @Produces("text/uri-list")
    public Response getUriUserMovies(@PathParam("userId")Long userId) {
        Optional<User> dbUser = getUserById(userId);

        if(!dbUser.isPresent())
            return Response.status(404).entity("Użytkownik o podanym ID nie istnieje.").build();

        StringBuilder moviesUri = new StringBuilder();
        for(Movie movie : dbUser.get().getMovies())
            moviesUri.append(movie.getUrl()).append("\n");

        return Response.status(200).entity(moviesUri.toString()).build();
    }

    @GET
    @Produces("plain/text")
    public Response getUserMoviesAsPlainText(@PathParam("userId")Long userId) {
        Optional<User> dbUser = getUserById(userId);

        if(!dbUser.isPresent())
            return Response.status(404).entity("Użytkownik o podanym ID nie istnieje.").build();

        StringBuilder movies = new StringBuilder();
        for(Movie movie : dbUser.get().getMovies())
            movies.append(movie.getTitle()).append("\n");
        return Response.status(200).entity(movies.toString()).build();
    }

    @GET
    @Produces("application/json")
    @Path("title/{title : [a-zA-Z0-9]*}")
    public Response getUserMovieByTitle(@PathParam("userId")Long userId, @PathParam("title")String title) {
        Optional<User> dbUser = getUserById(userId);

        if(!dbUser.isPresent())
            return Response.status(404).entity("Użytkownik o podanym ID nie istnieje.").build();

        Optional<Movie> userDbMovie = dbUser.get().getMovies().stream()
                .filter(m -> m.getTitle().equals(title))
                .findFirst();

        if(!userDbMovie.isPresent())
            return Response.status(404).entity("Użytkownik nie posiada filmu o podanym tytule.").build();
        return Response.status(200).entity(userDbMovie.get()).build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response addUserMovie(@PathParam("userId")Long userId, Movie movie) {
        Optional<User> dbUser = getUserById(userId);

        if(!dbUser.isPresent())
            return Response.status(404).entity("Użytkownik o podanym ID nie istnieje.").build();

        if(dbUser.get().getMovies().stream().anyMatch(m -> m.getId().equals(movie.getId())))
            return Response.status(409).entity("Film o podanym ID istnieje już w bibliotece tego użytkownika.").build();

        dbUser.get().getMovies().add(movie);

        return Response.status(201).entity(dbUser.get()).build();
    }

    @PUT
    @Consumes("application/json")
    public Response updateUserMovie(@PathParam("userId")Long userId, Movie movie) {
        Optional<User> dbUser = getUserById(userId);

        if(!dbUser.isPresent())
            return Response.status(404).entity("Użytkownik o podanym ID nie istnieje.").build();

        Optional<Movie> userDbMovie = dbUser.get().getMovies().stream()
                .filter(m -> m.getId().equals(movie.getId()))
                .findFirst();

        if(!userDbMovie.isPresent())
            return Response.status(404).entity("Użytkownik nie zawiera filmu o podanym ID").build();

        userDbMovie.get().setTitle(movie.getTitle());
        userDbMovie.get().setUrl(movie.getUrl());

        return Response.status(200).entity(dbUser.get()).build();
    }

    @PATCH
    @Consumes("application/json")
    public Response updateUserMovieWithSelectedData(@PathParam("userId")Long userId, Movie movie) {
        Optional<User> dbUser = getUserById(userId);

        if(!dbUser.isPresent())
            return Response.status(404).entity("Użytkownik o podanym ID nie istnieje.").build();

        Optional<Movie> userDbMovie = dbUser.get().getMovies().stream()
                .filter(m -> m.getId() == movie.getId())
                .findFirst();

        if(!userDbMovie.isPresent())
            return Response.status(404).entity("Użytkownik nie zawiera filmu o podanym ID").build();

        if(movie.getTitle() != null)
            userDbMovie.get().setTitle(movie.getTitle());
        if(movie.getUrl() != null)
            userDbMovie.get().setUrl(movie.getUrl());

        return Response.status(200).entity(dbUser.get()).build();
    }

    @DELETE
    @Path("{movieId}")
    public Response deleteUserMovie(@PathParam("userId")Long userId, @PathParam("movieId")Long movieId) {
        Optional<User> dbUser = getUserById(userId);

        if(!dbUser.isPresent())
            return Response.status(404).entity("Użytkownik o podanym ID nie istnieje.").build();

        Optional<Movie> userDbMovie = dbUser.get().getMovies().stream()
                .filter(m -> m.getId().equals(movieId))
                .findFirst();

        if(!userDbMovie.isPresent())
            return Response.status(404).entity("Użytkownik nie zawiera filmu o podanym ID").build();

        dbUser.get().getMovies().remove(userDbMovie.get());
        return Response.status(202).entity(dbUser.get()).build();
    }

    private Optional<User> getUserById(Long userId) {
        return Database.getUsers().stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst();
    }
}
