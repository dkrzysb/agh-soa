package pl.agh.kis.soa.webservices.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface HolidaysService {
    @WebMethod
    long daysToHolidays();
}
