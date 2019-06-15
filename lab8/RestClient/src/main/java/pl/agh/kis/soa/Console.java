package pl.agh.kis.soa;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import pl.agh.kis.soa.client.MoviesServiceInterface;
import pl.agh.kis.soa.client.UsersServiceInterface;
import pl.agh.kis.soa.model.Movie;
import pl.agh.kis.soa.model.User;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.util.List;

public class Console {
    public static void main(String[] args) {
        //get all users tests
        getAllUsers();
        getAllUsersProxy();

        //get user by id tests
        getUserById(1l);
        getUserByIdProxy(1l);

        //add user tests
        addUser(new User(20l, "Marek Marecki", 35));
        addUserProxy(new User(21l, "Ewelina Arka", 33));
        getUserById(20l);
        getUserById(21l);

        //update user tests
        updateUser(new User(20l, "Wiesiek Konieczko", 10, "https://tinyurl.com/y69yhkmu"));
        updateUserProxy(new User(21l, "Arnold Skwarek", 50, "https://tinyurl.com/y6bn8a4b"));
        getUserById(20l);
        getUserById(21l);

        //delete user tests
        deleteUser(20l);
        deleteUserProxy(21l);
        getUserById(20l);
        getUserById(21l);

        //add user movie tests
        addUserMovie(1l, new Movie(1l, "Nemo", "http://test.pl"));
        addUserMovieProxy(1l, new Movie(2l, "Robocop", "http://onet.pl"));

        //get user movies tests
        getUserMovies(1l);
        getUserMoviesProxy(1l);

        //update user movie tests
        updateUserMovie(1l, new Movie(1l, "Testowy film", "http://test2.pl"));
        updateUserMovieProxy(1l, new Movie(2l, "Testowy film2", "http://test3.pl"));
        getUserMovies(1l);

        //delete user movie tests
        deleteUserMovie(1l, 1l);
        deleteUserMovie(1l, 2l);
        getUserMovies(1l);
    }

    private static void getAllUsers() {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/favoritemovies/api/users");
        Response response = target.request().get();
        System.out.println("\nGet all users response status: " + response.getStatus());
        System.out.println("Response:");
        printResponse(response);
    }

    private static void getAllUsersProxy() {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/favoritemovies/api");
        UsersServiceInterface proxy = target.proxy(UsersServiceInterface.class);
        List<User> response = proxy.getAllUsers();
        System.out.println("\nAll users:");
        for(User user : response)
            System.out.println(String.format("ID: %s, name: %s, age: %s", user.getId(), user.getName(), user.getAge()));
    }

    private static void getUserById(Long userId) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/favoritemovies/api/users/" + userId);
        Response response = target.request().get();
        System.out.println("\nGet user by ID response status: " + response.getStatus());
        System.out.println("Response:");
        printResponse(response);
    }

    private static void getUserByIdProxy(Long userId) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/favoritemovies/api");
        UsersServiceInterface proxy = target.proxy(UsersServiceInterface.class);
        Response response = proxy.getUserById(userId);
        System.out.println("\nGet user by ID response status: " + response.getStatus());
        System.out.println("Response:");
        printResponse(response);
    }

    private static void addUser(User user) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/favoritemovies/api/users");
        Response response = target.request().post(Entity.entity(user, "application/json"));
        System.out.println("\nAdd user response status: " + response.getStatus());
        System.out.println("Response:");
        printResponse(response);
    }

    private static void addUserProxy(User user) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/favoritemovies/api");
        UsersServiceInterface proxy = target.proxy(UsersServiceInterface.class);
        Response response = proxy.addUser(user);
        System.out.println("\nAdd user response status: " + response.getStatus());
        System.out.println("Response:");
        printResponse(response);
    }

    private static void deleteUser(Long userId) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/favoritemovies/api/users/" + userId);
        Response response = target.request().delete();
        System.out.println("\nDelete user response status: " + response.getStatus());
        System.out.println("Response:");
        printResponse(response);
    }

    private static void deleteUserProxy(Long userId) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/favoritemovies/api");
        UsersServiceInterface usersServiceInterface = target.proxy(UsersServiceInterface.class);
        Response response = usersServiceInterface.deleteUser(userId);
        System.out.println("\nDelete user response status: " + response.getStatus());
        System.out.println("Response:");
        printResponse(response);
    }

    private static void updateUser(User user) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/favoritemovies/api/users");
        Response response = target.request().put(Entity.entity(user, "application/json"));
        System.out.println("\nUpdate user response status: " + response.getStatus());
        System.out.println("Response:");
        printResponse(response);
    }

    private static void updateUserProxy(User user) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/favoritemovies/api");
        UsersServiceInterface usersServiceInterface = target.proxy(UsersServiceInterface.class);
        Response response = usersServiceInterface.updateUser(user);
        System.out.println("\nUpdate user response status: " + response.getStatus());
        System.out.println("Response:");
        printResponse(response);
    }

    private static void getUserMovies(Long userId) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/favoritemovies/api/users/" + userId + "/movies");
        Response response = target.request().accept("application/json").get();
        System.out.println("\nGet users movies response status: " + response.getStatus());
        System.out.println("Response:");
        printResponse(response);
    }

    private static void getUserMoviesProxy(Long userId) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/favoritemovies/api");
        MoviesServiceInterface proxy = target.proxy(MoviesServiceInterface.class);
        Response response = proxy.getUserMovies(userId);
        System.out.println("\nGet users movies response status: " + response.getStatus());
        System.out.println("Response:");
        printResponse(response);
    }

    private static void addUserMovie(Long userId, Movie movie) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/favoritemovies/api/users/" + userId + "/movies");
        Response response = target.request().post(Entity.entity(movie, "application/json"));
        System.out.println("\nAdd user movie response status: " + response.getStatus());
        System.out.println("Response:");
        printResponse(response);
    }

    private static void addUserMovieProxy(Long userId, Movie movie) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/favoritemovies/api");
        MoviesServiceInterface proxy = target.proxy(MoviesServiceInterface.class);
        Response response = proxy.addUserMovie(userId, movie);
        System.out.println("\nAdd user movie response status: " + response.getStatus());
        System.out.println("Response:");
        printResponse(response);
    }

    private static void updateUserMovie(Long userId, Movie movie) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/favoritemovies/api/users/" + userId + "/movies");
        Response response = target.request().put(Entity.entity(movie, "application/json"));
        System.out.println("\nUpdate users movie response status: " + response.getStatus());
        System.out.println("Response:");
        printResponse(response);
    }

    private static void updateUserMovieProxy(Long userId, Movie movie) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/favoritemovies/api");
        MoviesServiceInterface proxy = target.proxy(MoviesServiceInterface.class);
        Response response = proxy.updateUserMovie(userId, movie);
        System.out.println("\nUpdate users movie response status: " + response.getStatus());
        System.out.println("Response:");
        printResponse(response);
    }

    private static void updateUserMovieWithSelectedDataProxy(Long userId, Movie movie) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/favoritemovies/api");
        MoviesServiceInterface proxy = target.proxy(MoviesServiceInterface.class);
        Response response = proxy.updateUserMovieWithSelectedData(userId, movie);
        System.out.println("\nUpdate users movie with selected data response status: " + response.getStatus());
        System.out.println("Response:");
        printResponse(response);
    }

    private static void deleteUserMovie(Long userId, Long movieId) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/favoritemovies/api/users/" + userId + "/movies/" + movieId);
        Response response = target.request().delete();
        System.out.println("\nDelete users movie response status: " + response.getStatus());
        System.out.println("Response:");
        printResponse(response);
    }

    private static void deleteUserMovieProxy(Long userId, Long movieId) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/favoritemovies/api");
        MoviesServiceInterface proxy = target.proxy(MoviesServiceInterface.class);
        Response response = proxy.deleteUserMovie(userId, movieId);
        System.out.println("\nDelete users movie response status: " + response.getStatus());
        System.out.println("Reponse:");
        printResponse(response);
    }

    private static void printResponse(Response response) {
        String value = response.readEntity(String.class);
        System.out.println(value);
        response.close();
    }
}
