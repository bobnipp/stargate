
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
 * <p>Java class for prismerSubmit complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prismerSubmit"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="origSiteId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="adHocFlag" type="{http://PRISMExNomWS.exnomws.ws.prism.saic.com}prismStandingAdhocFlag"/&gt;
 *         &lt;element name="eei" type="{http://PRISMExNomWS.exnomws.ws.prism.saic.com}prismeeiSubmit"/&gt;
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="stopDate" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="priority" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="requirement" type="{http://PRISMExNomWS.exnomws.ws.prism.saic.com}prismerExploitationRqmnt"/&gt;
 *         &lt;element name="phaseExploitation" type="{http://PRISMExNomWS.exnomws.ws.prism.saic.com}prismerExploitationPhase"/&gt;
 *         &lt;element name="type" type="{http://PRISMExNomWS.exnomws.ws.prism.saic.com}prismRequirementType"/&gt;
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="countryCodeKeyList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="geoRegionKeyList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="ipKeyList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="producerKeyList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="reportList" type="{http://PRISMExNomWS.exnomws.ws.prism.saic.com}prismReportSubmit" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="targetKeyList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="existingTargetIdList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="targetIdList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="rwacTargetGeoList" type="{http://PRISMExNomWS.exnomws.ws.prism.saic.com}prismCoord" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prismerSubmit", propOrder = {
    "key",
    "id",
    "name",
    "origSiteId",
    "adHocFlag",
    "eei",
    "startDate",
    "stopDate",
    "priority",
    "requirement",
    "phaseExploitation",
    "type",
    "description",
    "countryCodeKeyList",
    "geoRegionKeyList",
    "ipKeyList",
    "producerKeyList",
    "reportList",
    "targetKeyList",
    "existingTargetIdList",
    "targetIdList",
    "rwacTargetGeoList"
})
public class PrismerSubmit {

    protected String key;
    protected String id;
    @XmlElement(required = true)
    protected String name;
    protected String origSiteId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected PrismStandingAdhocFlag adHocFlag;
    @XmlElement(required = true)
    protected PrismeeiSubmit eei;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar startDate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar stopDate;
    protected int priority;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected PrismerExploitationRqmnt requirement;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected PrismerExploitationPhase phaseExploitation;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected PrismRequirementType type;
    protected String description;
    protected List<String> countryCodeKeyList;
    protected List<String> geoRegionKeyList;
    protected List<String> ipKeyList;
    protected List<String> producerKeyList;
    protected List<PrismReportSubmit> reportList;
    protected List<String> targetKeyList;
    protected List<String> existingTargetIdList;
    protected List<String> targetIdList;
    protected List<PrismCoord> rwacTargetGeoList;

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
     * Gets the value of the origSiteId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrigSiteId() {
        return origSiteId;
    }

    /**
     * Sets the value of the origSiteId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrigSiteId(String value) {
        this.origSiteId = value;
    }

    /**
     * Gets the value of the adHocFlag property.
     * 
     * @return
     *     possible object is
     *     {@link PrismStandingAdhocFlag }
     *     
     */
    public PrismStandingAdhocFlag getAdHocFlag() {
        return adHocFlag;
    }

    /**
     * Sets the value of the adHocFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismStandingAdhocFlag }
     *     
     */
    public void setAdHocFlag(PrismStandingAdhocFlag value) {
        this.adHocFlag = value;
    }

    /**
     * Gets the value of the eei property.
     * 
     * @return
     *     possible object is
     *     {@link PrismeeiSubmit }
     *     
     */
    public PrismeeiSubmit getEei() {
        return eei;
    }

    /**
     * Sets the value of the eei property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismeeiSubmit }
     *     
     */
    public void setEei(PrismeeiSubmit value) {
        this.eei = value;
    }

    /**
     * Gets the value of the startDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartDate(XMLGregorianCalendar value) {
        this.startDate = value;
    }

    /**
     * Gets the value of the stopDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStopDate() {
        return stopDate;
    }

    /**
     * Sets the value of the stopDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStopDate(XMLGregorianCalendar value) {
        this.stopDate = value;
    }

    /**
     * Gets the value of the priority property.
     * 
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Sets the value of the priority property.
     * 
     */
    public void setPriority(int value) {
        this.priority = value;
    }

    /**
     * Gets the value of the requirement property.
     * 
     * @return
     *     possible object is
     *     {@link PrismerExploitationRqmnt }
     *     
     */
    public PrismerExploitationRqmnt getRequirement() {
        return requirement;
    }

    /**
     * Sets the value of the requirement property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismerExploitationRqmnt }
     *     
     */
    public void setRequirement(PrismerExploitationRqmnt value) {
        this.requirement = value;
    }

    /**
     * Gets the value of the phaseExploitation property.
     * 
     * @return
     *     possible object is
     *     {@link PrismerExploitationPhase }
     *     
     */
    public PrismerExploitationPhase getPhaseExploitation() {
        return phaseExploitation;
    }

    /**
     * Sets the value of the phaseExploitation property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismerExploitationPhase }
     *     
     */
    public void setPhaseExploitation(PrismerExploitationPhase value) {
        this.phaseExploitation = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link PrismRequirementType }
     *     
     */
    public PrismRequirementType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismRequirementType }
     *     
     */
    public void setType(PrismRequirementType value) {
        this.type = value;
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
     * Gets the value of the ipKeyList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ipKeyList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIpKeyList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getIpKeyList() {
        if (ipKeyList == null) {
            ipKeyList = new ArrayList<String>();
        }
        return this.ipKeyList;
    }

    /**
     * Gets the value of the producerKeyList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the producerKeyList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProducerKeyList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getProducerKeyList() {
        if (producerKeyList == null) {
            producerKeyList = new ArrayList<String>();
        }
        return this.producerKeyList;
    }

    /**
     * Gets the value of the reportList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reportList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReportList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PrismReportSubmit }
     * 
     * 
     */
    public List<PrismReportSubmit> getReportList() {
        if (reportList == null) {
            reportList = new ArrayList<PrismReportSubmit>();
        }
        return this.reportList;
    }

    /**
     * Gets the value of the targetKeyList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the targetKeyList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTargetKeyList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getTargetKeyList() {
        if (targetKeyList == null) {
            targetKeyList = new ArrayList<String>();
        }
        return this.targetKeyList;
    }

    /**
     * Gets the value of the existingTargetIdList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the existingTargetIdList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExistingTargetIdList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getExistingTargetIdList() {
        if (existingTargetIdList == null) {
            existingTargetIdList = new ArrayList<String>();
        }
        return this.existingTargetIdList;
    }

    /**
     * Gets the value of the targetIdList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the targetIdList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTargetIdList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getTargetIdList() {
        if (targetIdList == null) {
            targetIdList = new ArrayList<String>();
        }
        return this.targetIdList;
    }

    /**
     * Gets the value of the rwacTargetGeoList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rwacTargetGeoList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRwacTargetGeoList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PrismCoord }
     * 
     * 
     */
    public List<PrismCoord> getRwacTargetGeoList() {
        if (rwacTargetGeoList == null) {
            rwacTargetGeoList = new ArrayList<PrismCoord>();
        }
        return this.rwacTargetGeoList;
    }

}
