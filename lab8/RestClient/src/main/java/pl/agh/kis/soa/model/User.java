package pl.agh.kis.soa.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private Long id;
    private String name;
    private int age;
    private String avatarUrl;
    private List<Movie> movies;

    public User() {
        movies = new ArrayList<>();
    }

    public User(Long id, String name, int age, String avatarUrl) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.avatarUrl = avatarUrl;
        movies = new ArrayList<>();
    }

    public User(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.avatarUrl = "";
        movies = new ArrayList<>();
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getAvatarUrl() { return avatarUrl; }
    public List<Movie> getMovies() { return movies; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setAvatarUrl(String avatarUrl) { this.avatarUrl = avatarUrl; }
    public void setMovies(List<Movie> movies) { this.movies = movies; }
}