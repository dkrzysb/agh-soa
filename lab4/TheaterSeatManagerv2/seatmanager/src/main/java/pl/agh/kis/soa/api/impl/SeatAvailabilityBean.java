package pl.agh.kis.soa.api.impl;

import pl.agh.kis.soa.api.ISeatAvailabilityBean;
import pl.agh.kis.soa.api.ISeatManagerBean;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

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
