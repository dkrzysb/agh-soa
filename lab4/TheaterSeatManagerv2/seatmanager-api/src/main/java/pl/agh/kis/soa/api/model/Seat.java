package pl.agh.kis.soa.api.model;

import java.io.Serializable;

public class Seat implements Serializable {
    private int seatNumber;
    private int price;
    private boolean available = true;

    public Seat(int seatNumber, int price) {
        this.seatNumber = seatNumber;
        this.price = price;
    }

    public int getSeatNumber() { return seatNumber; }
    public boolean isAvailable() { return available; }
    public int getPrice() { return price; }

    public void setAvailable(boolean available) { this.available = available; }
}