package pl.agh.kis.soa.crud.model;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private final static List<User> users = new ArrayList<User>()
    {
        {
            add(new User(1l, "Robert Lewandowski", 33, "https://tinyurl.com/yyb5oza4"));
            add(new User(2l, "Janina Kaczka", 20, "https://tinyurl.com/y69yhkmu"));
            add(new User(3l, "Kamil Beczka", 43, "https://tinyurl.com/y6bn8a4b"));
            add(new User(4l, "Monika Want", 26, "https://tinyurl.com/y5hh4da7"));
        }
    };

    public static List<User> getUsers() {
        return users;
    }
}
