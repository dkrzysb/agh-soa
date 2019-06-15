
package pl.agh.kis.soa.webservices.generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the pl.agh.kis.soa.webservices.generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _StaticAnalyzeString_QNAME = new QName("http://interfaces.webservices.soa.kis.agh.pl/", "staticAnalyzeString");
    private final static QName _StaticAnalyzeStringResponse_QNAME = new QName("http://interfaces.webservices.soa.kis.agh.pl/", "staticAnalyzeStringResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pl.agh.kis.soa.webservices.generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link StaticAnalyzeString }
     * 
     */
    public StaticAnalyzeString createStaticAnalyzeString() {
        return new StaticAnalyzeString();
    }

    /**
     * Create an instance of {@link StaticAnalyzeStringResponse }
     * 
     */
    public StaticAnalyzeStringResponse createStaticAnalyzeStringResponse() {
        return new StaticAnalyzeStringResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StaticAnalyzeString }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StaticAnalyzeString }{@code >}
     */
    @XmlElementDecl(namespace = "http://interfaces.webservices.soa.kis.agh.pl/", name = "staticAnalyzeString")
    public JAXBElement<StaticAnalyzeString> createStaticAnalyzeString(StaticAnalyzeString value) {
        return new JAXBElement<StaticAnalyzeString>(_StaticAnalyzeString_QNAME, StaticAnalyzeString.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StaticAnalyzeStringResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StaticAnalyzeStringResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://interfaces.webservices.soa.kis.agh.pl/", name = "staticAnalyzeStringResponse")
    public JAXBElement<StaticAnalyzeStringResponse> createStaticAnalyzeStringResponse(StaticAnalyzeStringResponse value) {
        return new JAXBElement<StaticAnalyzeStringResponse>(_StaticAnalyzeStringResponse_QNAME, StaticAnalyzeStringResponse.class, null, value);
    }

}
