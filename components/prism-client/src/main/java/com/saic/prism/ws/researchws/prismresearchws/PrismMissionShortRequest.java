
package com.saic.prism.ws.researchws.prismresearchws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for prismMissionShortRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prismMissionShortRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://PRISMResearchWS.researchws.ws.prism.saic.com}prismRequest"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="keyList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="localId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="prismId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="standingMissionName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dateFrom" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="dateTo" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="exactDate" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="modifiedOnFrom" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="modifiedOnTo" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="exactModifiedOn" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="sortBy" type="{http://PRISMResearchWS.researchws.ws.prism.saic.com}prismSortBy" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prismMissionShortRequest", propOrder = {
    "keyList",
    "id",
    "localId",
    "prismId",
    "standingMissionName",
    "dateFrom",
    "dateTo",
    "exactDate",
    "modifiedOnFrom",
    "modifiedOnTo",
    "exactModifiedOn",
    "sortBy"
})
public class PrismMissionShortRequest
    extends PrismRequest
{

    protected List<String> keyList;
    protected String id;
    protected String localId;
    protected String prismId;
    protected String standingMissionName;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateFrom;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateTo;
    protected Boolean exactDate;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar modifiedOnFrom;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar modifiedOnTo;
    protected Boolean exactModifiedOn;
    @XmlSchemaType(name = "string")
    protected PrismSortBy sortBy;

    /**
     * Gets the value of the keyList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the keyList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKeyList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getKeyList() {
        if (keyList == null) {
            keyList = new ArrayList<String>();
        }
        return this.keyList;
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
     * Gets the value of the standingMissionName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStandingMissionName() {
        return standingMissionName;
    }

    /**
     * Sets the value of the standingMissionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStandingMissionName(String value) {
        this.standingMissionName = value;
    }

    /**
     * Gets the value of the dateFrom property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateFrom() {
        return dateFrom;
    }

    /**
     * Sets the value of the dateFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateFrom(XMLGregorianCalendar value) {
        this.dateFrom = value;
    }

    /**
     * Gets the value of the dateTo property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateTo() {
        return dateTo;
    }

    /**
     * Sets the value of the dateTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateTo(XMLGregorianCalendar value) {
        this.dateTo = value;
    }

    /**
     * Gets the value of the exactDate property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isExactDate() {
        return exactDate;
    }

    /**
     * Sets the value of the exactDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setExactDate(Boolean value) {
        this.exactDate = value;
    }

    /**
     * Gets the value of the modifiedOnFrom property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getModifiedOnFrom() {
        return modifiedOnFrom;
    }

    /**
     * Sets the value of the modifiedOnFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setModifiedOnFrom(XMLGregorianCalendar value) {
        this.modifiedOnFrom = value;
    }

    /**
     * Gets the value of the modifiedOnTo property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getModifiedOnTo() {
        return modifiedOnTo;
    }

    /**
     * Sets the value of the modifiedOnTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setModifiedOnTo(XMLGregorianCalendar value) {
        this.modifiedOnTo = value;
    }

    /**
     * Gets the value of the exactModifiedOn property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isExactModifiedOn() {
        return exactModifiedOn;
    }

    /**
     * Sets the value of the exactModifiedOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setExactModifiedOn(Boolean value) {
        this.exactModifiedOn = value;
    }

    /**
     * Gets the value of the sortBy property.
     * 
     * @return
     *     possible object is
     *     {@link PrismSortBy }
     *     
     */
    public PrismSortBy getSortBy() {
        return sortBy;
    }

    /**
     * Sets the value of the sortBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismSortBy }
     *     
     */
    public void setSortBy(PrismSortBy value) {
        this.sortBy = value;
    }

}
