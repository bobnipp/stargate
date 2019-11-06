
package com.saic.prism.ws.coredataws.prismcoredataws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for prismReport complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prismReport"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="period" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismReportPeriod"/&gt;
 *         &lt;element name="product" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismReportProduct"/&gt;
 *         &lt;element name="firstDueDate" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="dataInfo" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismDataInfo" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prismReport", propOrder = {
    "key",
    "name",
    "period",
    "product",
    "firstDueDate",
    "dataInfo"
})
public class PrismReport {

    @XmlElement(required = true)
    protected String key;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected PrismReportPeriod period;
    @XmlElement(required = true)
    protected PrismReportProduct product;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar firstDueDate;
    protected PrismDataInfo dataInfo;

    /**
     * Gets the value of the key property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the value of the key property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKey(String value) {
        this.key = value;
    }

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
     * Gets the value of the period property.
     * 
     * @return
     *     possible object is
     *     {@link PrismReportPeriod }
     *     
     */
    public PrismReportPeriod getPeriod() {
        return period;
    }

    /**
     * Sets the value of the period property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismReportPeriod }
     *     
     */
    public void setPeriod(PrismReportPeriod value) {
        this.period = value;
    }

    /**
     * Gets the value of the product property.
     * 
     * @return
     *     possible object is
     *     {@link PrismReportProduct }
     *     
     */
    public PrismReportProduct getProduct() {
        return product;
    }

    /**
     * Sets the value of the product property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismReportProduct }
     *     
     */
    public void setProduct(PrismReportProduct value) {
        this.product = value;
    }

    /**
     * Gets the value of the firstDueDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFirstDueDate() {
        return firstDueDate;
    }

    /**
     * Sets the value of the firstDueDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFirstDueDate(XMLGregorianCalendar value) {
        this.firstDueDate = value;
    }

    /**
     * Gets the value of the dataInfo property.
     * 
     * @return
     *     possible object is
     *     {@link PrismDataInfo }
     *     
     */
    public PrismDataInfo getDataInfo() {
        return dataInfo;
    }

    /**
     * Sets the value of the dataInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismDataInfo }
     *     
     */
    public void setDataInfo(PrismDataInfo value) {
        this.dataInfo = value;
    }

}
