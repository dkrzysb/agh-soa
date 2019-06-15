
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

    private final static QName _ForeignCurrencyToPLN_QNAME = new QName("http://interfaces.webservices.soa.kis.agh.pl/", "foreignCurrencyToPLN");
    private final static QName _ForeignCurrencyToPLNResponse_QNAME = new QName("http://interfaces.webservices.soa.kis.agh.pl/", "foreignCurrencyToPLNResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pl.agh.kis.soa.webservices.generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ForeignCurrencyToPLN }
     * 
     */
    public ForeignCurrencyToPLN createForeignCurrencyToPLN() {
        return new ForeignCurrencyToPLN();
    }

    /**
     * Create an instance of {@link ForeignCurrencyToPLNResponse }
     * 
     */
    public ForeignCurrencyToPLNResponse createForeignCurrencyToPLNResponse() {
        return new ForeignCurrencyToPLNResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ForeignCurrencyToPLN }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ForeignCurrencyToPLN }{@code >}
     */
    @XmlElementDecl(namespace = "http://interfaces.webservices.soa.kis.agh.pl/", name = "foreignCurrencyToPLN")
    public JAXBElement<ForeignCurrencyToPLN> createForeignCurrencyToPLN(ForeignCurrencyToPLN value) {
        return new JAXBElement<ForeignCurrencyToPLN>(_ForeignCurrencyToPLN_QNAME, ForeignCurrencyToPLN.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ForeignCurrencyToPLNResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ForeignCurrencyToPLNResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://interfaces.webservices.soa.kis.agh.pl/", name = "foreignCurrencyToPLNResponse")
    public JAXBElement<ForeignCurrencyToPLNResponse> createForeignCurrencyToPLNResponse(ForeignCurrencyToPLNResponse value) {
        return new JAXBElement<ForeignCurrencyToPLNResponse>(_ForeignCurrencyToPLNResponse_QNAME, ForeignCurrencyToPLNResponse.class, null, value);
    }

}
