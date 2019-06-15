package pl.agh.kis.soa.client;


import pl.agh.kis.soa.server.*;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

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

    @EJB(lookup = "java:global/ejb/SeatManagerBean")
    ISeatManagerBean seatManagerBean;
    @EJB(lookup = "java:global/ejb/UserBean")
    IUserBean userBean;
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
        else if(user != null)
            messages = seatPurchaseBean.makePayment(Integer.parseInt(selectedSeat), username);
    }

    public void getBoughtSeats() {
        User user = validateUser();
        if(user != null) {
            userSeats = user.getBoughtSeats();
        }
    }
    private User validateUser() {
        User user = userBean.getUserByUsername(username);
        if(user == null || !user.getPassword().equals(password)) {
            messages = "Incorrect username or password";
            return null;
        }
        else {
            return user;
        }
    }
}
