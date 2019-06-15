package pl.agh.kis.soa.webservices.implementations;

import org.json.JSONObject;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DecimalFormat;

@WebService(endpointInterface = "pl.agh.kis.soa.webservices.interfaces.CurrencyExchangeService")
public class CurrencyExchangeServiceImpl {
    @WebMethod
    public double foreignCurrencyToPLN(String currencySymbol, double amount) throws IOException {
        DecimalFormat df = new DecimalFormat("###.##");

        URL url = new URL(String.format("https://api.exchangeratesapi.io/latest?base=%s&symbols=%s", currencySymbol, "PLN"));
        HttpsURLConnection httpConnection = (HttpsURLConnection)url.openConnection();
        httpConnection.setRequestMethod("GET");
        if(httpConnection.getResponseCode() == 200) {
            BufferedReader in = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            httpConnection.disconnect();
            JSONObject obj = new JSONObject(content.toString());
            double rate = Double.parseDouble(obj.getJSONObject("rates").get("PLN").toString());

            return Double.parseDouble(df.format(amount * rate));
        }
        else {
            httpConnection.disconnect();
            return -1;
        }
    }
}
