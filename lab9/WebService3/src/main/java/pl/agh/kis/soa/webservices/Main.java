package pl.agh.kis.soa.webservices;

import pl.agh.kis.soa.webservices.implementations.StringAnalyzerServiceImpl;

import javax.xml.ws.Endpoint;

public class Main {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/StringAnalyzerService", new StringAnalyzerServiceImpl());
        System.out.println("StringAnalyzerService is working...");
    }
}
