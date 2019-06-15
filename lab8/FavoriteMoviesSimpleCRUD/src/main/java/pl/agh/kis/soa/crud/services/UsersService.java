package pl.agh.kis.soa.crud.services;

import io.swagger.annotations.Api;
import pl.agh.kis.soa.crud.model.Database;
import pl.agh.kis.soa.crud.model.Movie;
import pl.agh.kis.soa.crud.model.User;

import javax.imageio.ImageIO;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.List;
import java.util.Optional;

@Path("/users")
@Api(value = "users")
public class UsersService {
    @GET
    @Produces("application/json")
    public List<User> getAllUsers() {
        return Database.getUsers();
    }

    @GET
    @Path("{userId}")
    @Produces("application/json")
    public Response getUserById(@PathParam("userId")Long userId) {
        Optional<User> dbUser = getDbUserById(userId);

        if(dbUser.isPresent())
            return Response.status(200).entity(dbUser.get()).build();
        return Response.status(404).build();
    }

    @GET
    @Path("{userId}/avatar")
    @Produces("image/png")
    public Response getUserAvatar(@PathParam("userId")Long userId) {
        Optional<User> dbUser = getDbUserById(userId);

        if(!dbUser.isPresent())
            return Response.status(404).build();

        try {
            BufferedImage image = ImageIO.read(new URL(dbUser.get().getAvatarUrl()));

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            byte[] imageData = baos.toByteArray();

            return Response.status(200).entity(imageData).build();
        }
        catch(Exception ex) {
            return Response.serverError().build();
        }
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response addUser(User user) {
        if(Database.getUsers().stream().anyMatch(u -> u.getId().equals(user.getId()) || u.getName().equals(user.getName())))
            return Response.status(409).build();

        Database.getUsers().add(user);
        return Response.status(201).entity(user).build();
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateUser(User user) {
        Optional<User> dbUser = getDbUserById(user.getId());

        if(!dbUser.isPresent())
            return Response.status(404).build();

        dbUser.get().setName(user.getName());
        dbUser.get().setAge(user.getAge());
        dbUser.get().setAvatarUrl(user.getAvatarUrl());
        dbUser.get().setMovies(user.getMovies());

        return Response.status(200).entity(dbUser.get()).build();
    }

    @PATCH
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateUserWithSelectedData(User user) {
        Optional<User> dbUser = getDbUserById(user.getId());

        if(!dbUser.isPresent())
            return Response.status(404).build();

        if(user.getName() != null)
            dbUser.get().setName(user.getName());
        if(user.getAge() != 0)
            dbUser.get().setAge(user.getAge());
        if(user.getAvatarUrl() != null)
            dbUser.get().setAvatarUrl(user.getAvatarUrl());
        if(user.getMovies() != null)
            dbUser.get().setMovies(user.getMovies());

        return Response.status(200).entity(dbUser.get()).build();
    }

    @DELETE
    @Path("{userId}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response deleteUser(@PathParam("userId")Long userId) {
        Optional<User> dbUser = getDbUserById(userId);

        if(!dbUser.isPresent())
            return Response.status(404).build();

        Database.getUsers().remove(dbUser.get());
        return Response.status(202).build();
    }

    private Optional<User> getDbUserById(Long userId) {
        return Database.getUsers().stream()
            .filter(u -> u.getId().equals(userId))
            .findFirst();
    }
}
