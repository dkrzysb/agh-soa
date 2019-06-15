package pl.agh.kis.soa.webservices.implementations;

import pl.agh.kis.soa.webservices.interfaces.StringAnalyzerService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService(endpointInterface = "pl.agh.kis.soa.webservices.interfaces.StringAnalyzerService")
public class StringAnalyzerServiceImpl {
    @WebMethod
    public Integer[] staticAnalyzeString(String analyzedString) {
        List<Integer> obtainedData = new ArrayList<Integer>();

        obtainedData.add(analyzedString.length());
        obtainedData.add(analyzedString.length() - analyzedString.replace(" ", "").length());
        obtainedData.add((int)analyzedString.codePoints().filter(c -> c >= 'A' && c <= 'Z').count());
        obtainedData.add((int)analyzedString.codePoints().filter(c -> c >= 'a' && c <= 'z').count());
        obtainedData.add((int)analyzedString.codePoints().filter(c -> c >= '0' && c <= '9').count());

        return obtainedData.toArray(new Integer[0]);
    }
}
