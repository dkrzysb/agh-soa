package pl.agh.kis.soa.webservices.client;

import pl.agh.kis.soa.webservices.generated.HolidaysService;
import pl.agh.kis.soa.webservices.generated.HolidaysServiceImplService;

import java.net.URL;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class HolidaysServiceClient {
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://localhost:8080/HolidaysService?wsdl");
        HolidaysServiceImplService holidaysServiceImp = new HolidaysServiceImplService(url);
        HolidaysService holidaysService = holidaysServiceImp.getHolidaysServiceImplPort();

        System.out.println("Days to holidays: " + holidaysService.daysToHolidays());
    }
}
