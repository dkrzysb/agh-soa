package pl.agh.kis.soa.server;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Singleton
@Remote(IUserBean.class)
public class UserBean implements IUserBean {
    private List<User> users = new ArrayList<User>();

    public UserBean() {
        users.add(new User("rkrzys", "qweasd", 80));
        users.add(new User("zboniek", "polska", 200));
        users.add(new User("rlewandowski", "gol123", 300));
    }

    public User getUserByUsername(String username) {
        Optional<User> user = users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();
        return user.isPresent() ? user.get() : null;
    }
}
