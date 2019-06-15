
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

    private final static QName _DaysToHolidays_QNAME = new QName("http://interfaces.webservices.soa.kis.agh.pl/", "daysToHolidays");
    private final static QName _DaysToHolidaysResponse_QNAME = new QName("http://interfaces.webservices.soa.kis.agh.pl/", "daysToHolidaysResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pl.agh.kis.soa.webservices.generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DaysToHolidays }
     * 
     */
    public DaysToHolidays createDaysToHolidays() {
        return new DaysToHolidays();
    }

    /**
     * Create an instance of {@link DaysToHolidaysResponse }
     * 
     */
    public DaysToHolidaysResponse createDaysToHolidaysResponse() {
        return new DaysToHolidaysResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DaysToHolidays }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DaysToHolidays }{@code >}
     */
    @XmlElementDecl(namespace = "http://interfaces.webservices.soa.kis.agh.pl/", name = "daysToHolidays")
    public JAXBElement<DaysToHolidays> createDaysToHolidays(DaysToHolidays value) {
        return new JAXBElement<DaysToHolidays>(_DaysToHolidays_QNAME, DaysToHolidays.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DaysToHolidaysResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DaysToHolidaysResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://interfaces.webservices.soa.kis.agh.pl/", name = "daysToHolidaysResponse")
    public JAXBElement<DaysToHolidaysResponse> createDaysToHolidaysResponse(DaysToHolidaysResponse value) {
        return new JAXBElement<DaysToHolidaysResponse>(_DaysToHolidaysResponse_QNAME, DaysToHolidaysResponse.class, null, value);
    }

}
