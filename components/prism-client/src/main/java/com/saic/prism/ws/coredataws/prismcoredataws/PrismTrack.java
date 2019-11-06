
package com.saic.prism.ws.coredataws.prismcoredataws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for prismTrack complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prismTrack"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="aircraftOrbit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="area" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="availableDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="aircraftRoute" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sro" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismYesNoFlag" minOccurs="0"/&gt;
 *         &lt;element name="nato" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismYesNoFlag" minOccurs="0"/&gt;
 *         &lt;element name="dataInfo" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismDataInfo" minOccurs="0"/&gt;
 *         &lt;element name="trackCoords" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismCoord" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="aoiIDList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prismTrack", propOrder = {
    "key",
    "id",
    "aircraftOrbit",
    "area",
    "availableDate",
    "aircraftRoute",
    "sro",
    "nato",
    "dataInfo",
    "trackCoords",
    "aoiIDList"
})
public class PrismTrack {

    @XmlElement(required = true)
    protected String key;
    @XmlElement(required = true)
    protected String id;
    protected String aircraftOrbit;
    protected String area;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar availableDate;
    protected String aircraftRoute;
    @XmlSchemaType(name = "string")
    protected PrismYesNoFlag sro;
    @XmlSchemaType(name = "string")
    protected PrismYesNoFlag nato;
    protected PrismDataInfo dataInfo;
    protected List<PrismCoord> trackCoords;
    protected List<String> aoiIDList;

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
     * Gets the value of the aircraftOrbit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAircraftOrbit() {
        return aircraftOrbit;
    }

    /**
     * Sets the value of the aircraftOrbit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAircraftOrbit(String value) {
        this.aircraftOrbit = value;
    }

    /**
     * Gets the value of the area property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArea() {
        return area;
    }

    /**
     * Sets the value of the area property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArea(String value) {
        this.area = value;
    }

    /**
     * Gets the value of the availableDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAvailableDate() {
        return availableDate;
    }

    /**
     * Sets the value of the availableDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAvailableDate(XMLGregorianCalendar value) {
        this.availableDate = value;
    }

    /**
     * Gets the value of the aircraftRoute property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAircraftRoute() {
        return aircraftRoute;
    }

    /**
     * Sets the value of the aircraftRoute property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAircraftRoute(String value) {
        this.aircraftRoute = value;
    }

    /**
     * Gets the value of the sro property.
     * 
     * @return
     *     possible object is
     *     {@link PrismYesNoFlag }
     *     
     */
    public PrismYesNoFlag getSro() {
        return sro;
    }

    /**
     * Sets the value of the sro property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismYesNoFlag }
     *     
     */
    public void setSro(PrismYesNoFlag value) {
        this.sro = value;
    }

    /**
     * Gets the value of the nato property.
     * 
     * @return
     *     possible object is
     *     {@link PrismYesNoFlag }
     *     
     */
    public PrismYesNoFlag getNato() {
        return nato;
    }

    /**
     * Sets the value of the nato property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismYesNoFlag }
     *     
     */
    public void setNato(PrismYesNoFlag value) {
        this.nato = value;
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
     * Gets the value of the trackCoords property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the trackCoords property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTrackCoords().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PrismCoord }
     * 
     * 
     */
    public List<PrismCoord> getTrackCoords() {
        if (trackCoords == null) {
            trackCoords = new ArrayList<PrismCoord>();
        }
        return this.trackCoords;
    }

    /**
     * Gets the value of the aoiIDList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the aoiIDList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAoiIDList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAoiIDList() {
        if (aoiIDList == null) {
            aoiIDList = new ArrayList<String>();
        }
        return this.aoiIDList;
    }

}
