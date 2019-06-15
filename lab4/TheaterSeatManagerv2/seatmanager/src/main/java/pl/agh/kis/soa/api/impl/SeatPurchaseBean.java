package pl.agh.kis.soa.api.impl;

import pl.agh.kis.soa.api.ISeatManagerBean;
import pl.agh.kis.soa.api.ISeatPurchaseBean;
import pl.agh.kis.soa.api.model.Seat;
import pl.agh.kis.soa.api.model.User;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateful;

@Stateful
@Remote(ISeatPurchaseBean.class)
public class SeatPurchaseBean implements ISeatPurchaseBean {
    @EJB(lookup = "java:global/ejb/SeatManagerBean")
    ISeatManagerBean seatManagerBean;

    @Override
    public User makePayment(int seatNumber, User user) {
        int seatPrice = seatManagerBean.getSeatPrice(seatNumber);

       if(user.getMoney() < seatPrice)
           return null;

        seatManagerBean.buyTicket(seatNumber);
        user.clearBoughtSeats();
        Seat boughtSeat = seatManagerBean.getSeatList().stream()
                .filter(s -> s.getSeatNumber() == seatNumber)
                .findFirst()
                .get();
        user.setMoney(user.getMoney() - seatPrice);
        user.addBoughtSeat(boughtSeat);
        return user;
    }
}
