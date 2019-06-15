package pl.agh.kis.soa.server;

public interface ISeatPurchaseBean {
    String makePayment(int seatNumber, String user);
}
