package pl.agh.kis.soa.client;

import pl.agh.kis.soa.webservices.generated.CurrencyExchangeService;
import pl.agh.kis.soa.webservices.generated.CurrencyExchangeServiceImplService;

import java.net.URL;

public class CurrencyExchangeServiceClient {
  public static void main(String[] args) throws Exception {
    URL url = new URL("http://localhost:8080/CurrencyExchangeService?wsdl");
    CurrencyExchangeServiceImplService currencyExchangeServiceImpl = new CurrencyExchangeServiceImplService(url);
    CurrencyExchangeService currencyExchangeService = currencyExchangeServiceImpl.getCurrencyExchangeServiceImplPort();
    double plnAmount = currencyExchangeService.foreignCurrencyToPLN("USD", 200);
    System.out.println("PLN amount after exchange: " + plnAmount);
  }
}