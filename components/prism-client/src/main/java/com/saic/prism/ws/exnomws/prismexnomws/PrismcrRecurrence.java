
package com.saic.prism.ws.exnomws.prismexnomws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for prismcrRecurrence complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prismcrRecurrence"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="daysOfWeek" type="{http://PRISMExNomWS.exnomws.ws.prism.saic.com}prismDaysOfWeek" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="daysOfMonth" type="{http://www.w3.org/2001/XMLSchema}int" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="arbitraryDates" type="{http://www.w3.org/2001/XMLSchema}date" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prismcrRecurrence", propOrder = {
    "daysOfWeek",
    "daysOfMonth",
    "arbitraryDates"
})
public class PrismcrRecurrence {

    @XmlSchemaType(name = "string")
    protected List<PrismDaysOfWeek> daysOfWeek;
    @XmlElement(type = Integer.class)
    protected List<Integer> daysOfMonth;
    @XmlSchemaType(name = "date")
    protected List<XMLGregorianCalendar> arbitraryDates;

    /**
     * Gets the value of the daysOfWeek property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the daysOfWeek property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDaysOfWeek().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PrismDaysOfWeek }
     * 
     * 
     */
    public List<PrismDaysOfWeek> getDaysOfWeek() {
        if (daysOfWeek == null) {
            daysOfWeek = new ArrayList<PrismDaysOfWeek>();
        }
        return this.daysOfWeek;
    }

    /**
     * Gets the value of the daysOfMonth property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the daysOfMonth property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDaysOfMonth().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Integer }
     * 
     * 
     */
    public List<Integer> getDaysOfMonth() {
        if (daysOfMonth == null) {
            daysOfMonth = new ArrayList<Integer>();
        }
        return this.daysOfMonth;
    }

    /**
     * Gets the value of the arbitraryDates property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the arbitraryDates property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getArbitraryDates().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link XMLGregorianCalendar }
     * 
     * 
     */
    public List<XMLGregorianCalendar> getArbitraryDates() {
        if (arbitraryDates == null) {
            arbitraryDates = new ArrayList<XMLGregorianCalendar>();
        }
        return this.arbitraryDates;
    }

}
