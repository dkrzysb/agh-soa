package pl.agh.kis.soa.server;

import java.util.List;

public interface ISeatManagerBean {
    List<Seat> getSeatList();
    void buyTicket(int seatNumber);
    int getSeatPrice(int seatNumber);
}
