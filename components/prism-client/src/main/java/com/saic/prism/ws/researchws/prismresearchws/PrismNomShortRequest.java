
package com.saic.prism.ws.researchws.prismresearchws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for prismNomShortRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prismNomShortRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://PRISMResearchWS.researchws.ws.prism.saic.com}prismRequest"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="keyList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="spatialSearch" type="{http://PRISMResearchWS.researchws.ws.prism.saic.com}prismSpatialSearch" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="prismId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="orgList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="owner" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
@XmlType(name = "prismNomShortRequest", propOrder = {
    "keyList",
    "spatialSearch",
    "id",
    "prismId",
    "name",
    "orgList",
    "owner",
    "modifiedOnFrom",
    "modifiedOnTo",
    "exactModifiedOn",
    "sortBy"
})
public class PrismNomShortRequest
    extends PrismRequest
{

    protected List<String> keyList;
    protected PrismSpatialSearch spatialSearch;
    protected String id;
    protected String prismId;
    protected String name;
    protected List<String> orgList;
    protected String owner;
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
     * Gets the value of the spatialSearch property.
     * 
     * @return
     *     possible object is
     *     {@link PrismSpatialSearch }
     *     
     */
    public PrismSpatialSearch getSpatialSearch() {
        return spatialSearch;
    }

    /**
     * Sets the value of the spatialSearch property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismSpatialSearch }
     *     
     */
    public void setSpatialSearch(PrismSpatialSearch value) {
        this.spatialSearch = value;
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
     * Gets the value of the orgList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the orgList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOrgList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getOrgList() {
        if (orgList == null) {
            orgList = new ArrayList<String>();
        }
        return this.orgList;
    }

    /**
     * Gets the value of the owner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets the value of the owner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwner(String value) {
        this.owner = value;
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
