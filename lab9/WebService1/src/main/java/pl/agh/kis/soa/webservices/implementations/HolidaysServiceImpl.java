package pl.agh.kis.soa.webservices.implementations;

import pl.agh.kis.soa.webservices.interfaces.HolidaysService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@WebService(endpointInterface = "pl.agh.kis.soa.webservices.interfaces.HolidaysService")
public class HolidaysServiceImpl implements HolidaysService {
    @WebMethod
    public long daysToHolidays() {
        Date currentDate = new Date();
        Date holidaysDate = new Date(currentDate.getYear(), 6, 1);

        long diff = holidaysDate.getTime() - currentDate.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
}
