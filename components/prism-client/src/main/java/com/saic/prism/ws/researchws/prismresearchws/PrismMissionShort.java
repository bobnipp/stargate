
package com.saic.prism.ws.researchws.prismresearchws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prismMissionShort complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prismMissionShort"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="prismId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="localId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="dataInfo" type="{http://PRISMResearchWS.researchws.ws.prism.saic.com}prismDataInfo" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prismMissionShort", propOrder = {
    "key",
    "id",
    "prismId",
    "localId",
    "dataInfo"
})
public class PrismMissionShort {

    @XmlElement(required = true)
    protected String key;
    @XmlElement(required = true)
    protected String id;
    @XmlElement(required = true)
    protected String prismId;
    @XmlElement(required = true)
    protected String localId;
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
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the prismId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrismId() {
        return prismId;
    }

    /**
     * Sets the value of the prismId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrismId(String value) {
        this.prismId = value;
    }

    /**
     * Gets the value of the localId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalId() {
        return localId;
    }

    /**
     * Sets the value of the localId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalId(String value) {
        this.localId = value;
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
