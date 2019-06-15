package pl.agh.kis.soa.server;

import javax.ejb.*;

@Stateless
@Remote(ISeatAvailabilityBean.class)
public class SeatAvailabilityBean implements ISeatAvailabilityBean {
    @EJB(lookup = "java:global/ejb/SeatManagerBean")
    ISeatManagerBean seatManagerBean;

    @Override
    public boolean checkSeatAvailability(int seatNumber) {
        return seatManagerBean
            .getSeatList()
            .stream()
            .filter(seat -> seat.getSeatNumber() == seatNumber)
            .findFirst()
            .get()
            .isAvailable();
    }
}
