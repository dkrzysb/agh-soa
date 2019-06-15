
package pl.agh.kis.soa.webservices.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for foreignCurrencyToPLNResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="foreignCurrencyToPLNResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="foreignCurrencyInPLN" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "foreignCurrencyToPLNResponse", propOrder = {
    "foreignCurrencyInPLN"
})
public class ForeignCurrencyToPLNResponse {

    protected double foreignCurrencyInPLN;

    /**
     * Gets the value of the foreignCurrencyInPLN property.
     * 
     */
    public double getForeignCurrencyInPLN() {
        return foreignCurrencyInPLN;
    }

    /**
     * Sets the value of the foreignCurrencyInPLN property.
     * 
     */
    public void setForeignCurrencyInPLN(double value) {
        this.foreignCurrencyInPLN = value;
    }

}
