package pl.agh.kis.soa.client;

import pl.agh.kis.soa.webservices.generated.StringAnalyzerService;
import pl.agh.kis.soa.webservices.generated.StringAnalyzerServiceImplService;

import java.util.List;

public class StringAnalyzerServiceClient {
    public static void main(String[] args) {
        StringAnalyzerServiceImplService stringAnalyzerServiceImplService = new StringAnalyzerServiceImplService();
        StringAnalyzerService stringAnalyzerService = stringAnalyzerServiceImplService.getStringAnalyzerServiceImplPort();
        List<Integer> obtainedData = stringAnalyzerService.staticAnalyzeString("Hell0 W0rld!");
        for(Integer data : obtainedData)
            System.out.println(data);
    }
}
