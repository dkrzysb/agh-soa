import pl.agh.kis.soa.webservices.implementations.HolidaysServiceImpl;

import javax.xml.ws.Endpoint;

public class Main {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/HolidaysService", new HolidaysServiceImpl());
        System.out.println("HolidaysService is working...");
    }
}
