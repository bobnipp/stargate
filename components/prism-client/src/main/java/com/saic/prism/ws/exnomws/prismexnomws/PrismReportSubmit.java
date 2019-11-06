
package com.saic.prism.ws.exnomws.prismexnomws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prismReportSubmit complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prismReportSubmit"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="periodKey" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="productKey" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="firstDueDate" type="{http://www.w3.org/2001/XMLSchema}anySimpleType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prismReportSubmit", propOrder = {
    "name",
    "periodKey",
    "productKey",
    "firstDueDate"
})
public class PrismReportSubmit {

    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String periodKey;
    @XmlElement(required = true)
    protected String productKey;
    @XmlElement(required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected Object firstDueDate;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the periodKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeriodKey() {
        return periodKey;
    }

    /**
     * Sets the value of the periodKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeriodKey(String value) {
        this.periodKey = value;
    }

    /**
     * Gets the value of the productKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductKey() {
        return productKey;
    }

    /**
     * Sets the value of the productKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductKey(String value) {
        this.productKey = value;
    }

    /**
     * Gets the value of the firstDueDate property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getFirstDueDate() {
        return firstDueDate;
    }

    /**
     * Sets the value of the firstDueDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setFirstDueDate(Object value) {
        this.firstDueDate = value;
    }

}
