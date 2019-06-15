package pl.agh.kis.soa.client;

import pl.agh.kis.soa.api.ISeatManagerBean;
import pl.agh.kis.soa.api.ISeatPurchaseBean;
import pl.agh.kis.soa.api.model.Seat;
import pl.agh.kis.soa.api.model.User;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ManagedBean(name = "TheaterManager")
@RequestScoped
public class TheaterManager {
    private String selectedSeat;
    private String username;
    private String password;
    private String messages;
    private int balance = -1;
    private List<Seat> availableSeats;
    private List<Seat> userSeats;
    private static List<User> users = new ArrayList<User>()
    {{
        add(new User("rkrzys", "qweasd", 80));
        add(new User("zboniek", "polska", 200));
        add(new User("rlewandowski", "gol123", 300));
    }};

    @EJB(lookup = "java:global/ejb/SeatManagerBean")
    ISeatManagerBean seatManagerBean;
    @EJB(lookup = "java:global/ejb/SeatPurchaseBean")
    ISeatPurchaseBean seatPurchaseBean;

    public List<Seat> getAvailableSeats() {
        availableSeats = seatManagerBean.getSeatList();
        return availableSeats;
    }
    public String getMessages() { return messages; }
    public String getSelectedSeat() { return selectedSeat; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public int getBalance() { return balance; }
    public List<Seat> getUserSeats() { return userSeats; }

    public void setSelectedSeat(String selectedSeat) { this.selectedSeat = selectedSeat; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setMessages(String messages) { this.messages = messages; }
    public void setAvailableSeats(List<Seat> availableSeats) { this.availableSeats = availableSeats; }

    public void userBalance() {
        User user = validateUser();
        if(user != null)
            balance = user.getMoney();
    }

    public void book() {
        User user = validateUser();
        if(selectedSeat == null)
            messages = "This seat is already reserved.";
        else if(user != null) {
            User returnedUser = seatPurchaseBean.makePayment(Integer.parseInt(selectedSeat), user);
            if(returnedUser == null)
                messages = "You don't have enough money";
            else {
                user.setMoney(returnedUser.getMoney());
                List<Seat> boughtSeats = returnedUser.getBoughtSeats();
                for(Seat seat : boughtSeats)
                        user.addBoughtSeat(seat);
                messages = "Success. You have bought ticket";
            }
        }
    }

    public void getBoughtSeats() {
        User user = validateUser();
        if(user != null) {
            userSeats = user.getBoughtSeats();
        }
    }
    private User validateUser() {
        Optional<User> user = users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();
        if(!user.isPresent() || !user.get().getPassword().equals(password)) {
            messages = "Incorrect username or password";
            return null;
        }
        else {
            return user.get();
        }
    }
}

