
package com.saic.prism.ws.coredataws.prismcoredataws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prismTarget complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prismTarget"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="prismId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="type" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismTargetType"/&gt;
 *         &lt;element name="countryCodeList" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismCountryCode" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="geoRegionList" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismGeoRegion" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="dataInfo" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismDataInfo"/&gt;
 *         &lt;element name="point" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismPoint" minOccurs="0"/&gt;
 *         &lt;element name="dsa" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismdsa" minOccurs="0"/&gt;
 *         &lt;element name="loc" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismloc" minOccurs="0"/&gt;
 *         &lt;element name="rwac" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismrwac" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prismTarget", propOrder = {
    "key",
    "id",
    "prismId",
    "name",
    "type",
    "countryCodeList",
    "geoRegionList",
    "dataInfo",
    "point",
    "dsa",
    "loc",
    "rwac"
})
public class PrismTarget {

    @XmlElement(required = true)
    protected String key;
    @XmlElement(required = true)
    protected String id;
    @XmlElement(required = true)
    protected String prismId;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected PrismTargetType type;
    protected List<PrismCountryCode> countryCodeList;
    protected List<PrismGeoRegion> geoRegionList;
    @XmlElement(required = true)
    protected PrismDataInfo dataInfo;
    protected PrismPoint point;
    protected Prismdsa dsa;
    protected Prismloc loc;
    protected Prismrwac rwac;

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
     * Gets the value of the countryCodeList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the countryCodeList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCountryCodeList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PrismCountryCode }
     * 
     * 
     */
    public List<PrismCountryCode> getCountryCodeList() {
        if (countryCodeList == null) {
            countryCodeList = new ArrayList<PrismCountryCode>();
        }
        return this.countryCodeList;
    }

    /**
     * Gets the value of the geoRegionList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the geoRegionList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGeoRegionList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PrismGeoRegion }
     * 
     * 
     */
    public List<PrismGeoRegion> getGeoRegionList() {
        if (geoRegionList == null) {
            geoRegionList = new ArrayList<PrismGeoRegion>();
        }
        return this.geoRegionList;
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

    /**
     * Gets the value of the point property.
     * 
     * @return
     *     possible object is
     *     {@link PrismPoint }
     *     
     */
    public PrismPoint getPoint() {
        return point;
    }

    /**
     * Sets the value of the point property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismPoint }
     *     
     */
    public void setPoint(PrismPoint value) {
        this.point = value;
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

    /**
     * Gets the value of the rwac property.
     * 
     * @return
     *     possible object is
     *     {@link Prismrwac }
     *     
     */
    public Prismrwac getRwac() {
        return rwac;
    }

    /**
     * Sets the value of the rwac property.
     * 
     * @param value
     *     allowed object is
     *     {@link Prismrwac }
     *     
     */
    public void setRwac(Prismrwac value) {
        this.rwac = value;
    }

}
