
package com.saic.prism.ws.exnomws.prismexnomws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prismTargetSubmit complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prismTargetSubmit"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="type" type="{http://PRISMExNomWS.exnomws.ws.prism.saic.com}prismTargetType"/&gt;
 *         &lt;element name="countryCodeKeyList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="geoRegionKeyList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="dsa" type="{http://PRISMExNomWS.exnomws.ws.prism.saic.com}prismdsa" minOccurs="0"/&gt;
 *         &lt;element name="loc" type="{http://PRISMExNomWS.exnomws.ws.prism.saic.com}prismloc" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prismTargetSubmit", propOrder = {
    "key",
    "id",
    "name",
    "type",
    "countryCodeKeyList",
    "geoRegionKeyList",
    "dsa",
    "loc"
})
public class PrismTargetSubmit {

    protected String key;
    @XmlElement(required = true)
    protected String id;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected PrismTargetType type;
    protected List<String> countryCodeKeyList;
    protected List<String> geoRegionKeyList;
    protected Prismdsa dsa;
    protected Prismloc loc;

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
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link PrismTargetType }
     *     
     */
    public PrismTargetType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismTargetType }
     *     
     */
    public void setType(PrismTargetType value) {
        this.type = value;
    }

    /**
     * Gets the value of the countryCodeKeyList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the countryCodeKeyList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCountryCodeKeyList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getCountryCodeKeyList() {
        if (countryCodeKeyList == null) {
            countryCodeKeyList = new ArrayList<String>();
        }
        return this.countryCodeKeyList;
    }

    /**
     * Gets the value of the geoRegionKeyList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the geoRegionKeyList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGeoRegionKeyList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getGeoRegionKeyList() {
        if (geoRegionKeyList == null) {
            geoRegionKeyList = new ArrayList<String>();
        }
        return this.geoRegionKeyList;
    }

    /**
     * Gets the value of the dsa property.
     * 
     * @return
     *     possible object is
     *     {@link Prismdsa }
     *     
     */
    public Prismdsa getDsa() {
        return dsa;
    }

    /**
     * Sets the value of the dsa property.
     * 
     * @param value
     *     allowed object is
     *     {@link Prismdsa }
     *     
     */
    public void setDsa(Prismdsa value) {
        this.dsa = value;
    }

    /**
     * Gets the value of the loc property.
     * 
     * @return
     *     possible object is
     *     {@link Prismloc }
     *     
     */
    public Prismloc getLoc() {
        return loc;
    }

    /**
     * Sets the value of the loc property.
     * 
     * @param value
     *     allowed object is
     *     {@link Prismloc }
     *     
     */
    public void setLoc(Prismloc value) {
        this.loc = value;
    }

}
