package pl.agh.kis.soa.server;

import javax.ejb.*;

@Stateful
@Remote(ISeatPurchaseBean.class)
public class SeatPurchaseBean implements ISeatPurchaseBean {
    @EJB(lookup = "java:global/ejb/SeatManagerBean")
    ISeatManagerBean seatManagerBean;
    @EJB(lookup = "java:global/ejb/UserBean")
    IUserBean userBean;

    @Override
    public String makePayment(int seatNumber, String username) {
        int seatPrice = seatManagerBean.getSeatPrice(seatNumber);
        User user = userBean.getUserByUsername(username);

        if(user.getMoney() < seatPrice)
            return "You don't have enough money. You have to have " + (seatPrice - user.getMoney()) + " zł more.";

        seatManagerBean.buyTicket(seatNumber);
        Seat boughtSeat = seatManagerBean.getSeatList().stream()
                .filter(s -> s.getSeatNumber() == seatNumber)
                .findFirst()
                .get();
        user.setMoney(user.getMoney() - seatPrice);
        user.addBoughSeat(boughtSeat);
        return "Success. You have bought ticket";
    }
}
