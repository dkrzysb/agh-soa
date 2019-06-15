package pl.agh.kis.soa.api.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private String username;
    private String password;
    private int money;
    private List<Seat> boughtSeats;

    public User(String username, String password, int money) {
        this.username = username;
        this.password = password;
        this.money = money;
        boughtSeats = new ArrayList<Seat>();
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public int getMoney() { return money; }
    public List<Seat> getBoughtSeats() { return boughtSeats; }

    public void setMoney(int money) { this.money = money; }
    public void addBoughtSeat(Seat boughtSeat) { boughtSeats.add(boughtSeat); }

    public void clearBoughtSeats() {
        boughtSeats.clear();
    }
}