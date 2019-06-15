package pl.agh.kis.soa.api.impl;

import pl.agh.kis.soa.api.ISeatManagerBean;
import pl.agh.kis.soa.api.model.Seat;

import javax.ejb.Lock;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Singleton
@Remote(ISeatManagerBean.class)
public class SeatManagerBean implements ISeatManagerBean {
    private List<Seat> seats;

    public SeatManagerBean() {
        Random random = new Random();
        seats = new ArrayList<>();

        for(int i = 0; i < 30; ++i) {
            seats.add(new Seat(i, random.nextInt(30) + 15));
        }
    }

    @Override
    public List<Seat> getSeatList() {
        return seats;
    }

    @Override
    @Lock
    public void buyTicket(int seatNumber) {
        seats.stream()
                .filter(s -> s.getSeatNumber() == seatNumber)
                .findFirst()
                .get()
                .setAvailable(false);
    }

    @Override
    public int getSeatPrice(int seatNumber) {
        return getSeatList()
                .stream()
                .filter(seat -> seat.getSeatNumber() == seatNumber)
                .findFirst()
                .get()
                .getPrice();
    }
}
