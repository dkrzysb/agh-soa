package pl.agh.kis.soa.api;


import pl.agh.kis.soa.api.model.Seat;

import java.util.List;

public interface ISeatManagerBean {
    List<Seat> getSeatList();
    void buyTicket(int seatNumber);
    int getSeatPrice(int seatNumber);
}
