package pl.agh.kis.soa.api;

import pl.agh.kis.soa.api.model.User;

public interface ISeatPurchaseBean {
    User makePayment(int seatNumber, User user);
}

