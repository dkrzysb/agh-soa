package pl.agh.kis.soa.webservices.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface CurrencyExchangeService {
    @WebMethod(operationName = "foreignCurrencyToPLN")
    @WebResult(name = "foreignCurrencyInPLN")
    double foreignCurrencyToPLN(@WebParam(name = "currencySymbol")String currencySymbol, @WebParam(name = "amount")double amount);
}
