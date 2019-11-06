
package com.saic.prism.ws.coredataws.prismcoredataws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prismPoliticalSensitivity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prismPoliticalSensitivity"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ps" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
@XmlType(name = "prismPoliticalSensitivity", propOrder = {
    "key",
    "ps",
    "description",
    "dataInfo"
})
public class PrismPoliticalSensitivity {

    @XmlElement(required = true)
    protected String key;
    @XmlElement(required = true)
    protected String ps;
    protected String description;
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
     * Gets the value of the ps property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPs() {
        return ps;
    }

    /**
     * Sets the value of the ps property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPs(String value) {
        this.ps = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
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
