package pl.agh.kis.soa.webservices;

import pl.agh.kis.soa.webservices.implementations.CurrencyExchangeServiceImpl;

import javax.xml.ws.Endpoint;

public class Main {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/CurrencyExchangeService", new CurrencyExchangeServiceImpl());
        System.out.println("CurrencyExchangeService is working...");
    }
}
