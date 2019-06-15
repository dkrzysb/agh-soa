package pl.agh.kis.soa;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.json.*;

@ManagedBean(name = "CurrencyExchange")
@SessionScoped
public class CurrencyExchange {
    private List<String> currencies;
    private String selectedFromCurrency;
    private String selectedToCurrency;
    private double amount;
    private String newCurrency;

    public CurrencyExchange() {
        currencies = new ArrayList<>();
        currencies.add("PLN");
        currencies.add("USD");
    }

    public List<String> getCurrencies() {
        return currencies;
    }
    public String getNewCurrency() { return newCurrency; }
    public String getSelectedFromCurrency() { return selectedFromCurrency; }
    public String getSelectedToCurrency() { return selectedToCurrency; }
    public double getAmount() { return amount; }
    public double getConvertedAmount() throws Exception {
        DecimalFormat df = new DecimalFormat("###.##");

        URL url = new URL(String.format("https://api.exchangeratesapi.io/latest?base=%s&symbols=%s", selectedFromCurrency, selectedToCurrency));
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
            double rate = Double.parseDouble(obj.getJSONObject("rates").get(selectedToCurrency).toString());

            return Double.parseDouble(df.format(amount * rate));
        }
        else {
            httpConnection.disconnect();
            return -1;
        }
    }

    public void setSelectedFromCurrency(String selectedFromCurrency) { this.selectedFromCurrency = selectedFromCurrency; }
    public void setSelectedToCurrency(String selectedToCurrency) { this.selectedToCurrency = selectedToCurrency; }
    public void setAmount(double amount) { this.amount = amount; }
    public void setNewCurrency(String newCurrency) { this.newCurrency = newCurrency; }
    public void addNewCurrency() {
        currencies.add(newCurrency);
    }
}
