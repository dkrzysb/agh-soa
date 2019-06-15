package pl.agh.kis.soa.webservices.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface StringAnalyzerService {
    @WebMethod
    @WebResult(name = "analyzeReturnData")
    public Integer[] staticAnalyzeString(@WebParam(name = "analyzedString")String analyzedString);
}
