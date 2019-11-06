
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
 * <p>Java class for prismer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prismer"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="prismId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="origSiteId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="adHocFlag" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismStandingAdhocFlag" minOccurs="0"/&gt;
 *         &lt;element name="eei" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismeei" minOccurs="0"/&gt;
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="stopDate" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="priority" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="requirement" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismerExploitationRqmnt" minOccurs="0"/&gt;
 *         &lt;element name="phaseExploitation" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismerExploitationPhase" minOccurs="0"/&gt;
 *         &lt;element name="type" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismRequirementType"/&gt;
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="countryCodeList" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismCountryCode" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="geoRegionList" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismGeoRegion" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="ipList" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismipShort" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="producerList" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismProducer" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="reportList" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismReport" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="targetList" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismTargetShort" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="dataInfo" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismDataInfo"/&gt;
 *         &lt;element name="respOrg" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prismer", propOrder = {
    "key",
    "id",
    "prismId",
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
    "countryCodeList",
    "geoRegionList",
    "ipList",
    "producerList",
    "reportList",
    "targetList",
    "dataInfo",
    "respOrg"
})
public class Prismer {

    @XmlElement(required = true)
    protected String key;
    @XmlElement(required = true)
    protected String id;
    @XmlElement(required = true)
    protected String prismId;
    @XmlElement(required = true)
    protected String name;
    protected String origSiteId;
    @XmlSchemaType(name = "string")
    protected PrismStandingAdhocFlag adHocFlag;
    protected Prismeei eei;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar startDate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar stopDate;
    protected int priority;
    @XmlSchemaType(name = "string")
    protected PrismerExploitationRqmnt requirement;
    @XmlSchemaType(name = "string")
    protected PrismerExploitationPhase phaseExploitation;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected PrismRequirementType type;
    protected String description;
    protected List<PrismCountryCode> countryCodeList;
    protected List<PrismGeoRegion> geoRegionList;
    protected List<PrismipShort> ipList;
    protected List<PrismProducer> producerList;
    protected List<PrismReport> reportList;
    protected List<PrismTargetShort> targetList;
    @XmlElement(required = true)
    protected PrismDataInfo dataInfo;
    @XmlElement(required = true)
    protected String respOrg;

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
     *     {@link Prismeei }
     *     
     */
    public Prismeei getEei() {
        return eei;
    }

    /**
     * Sets the value of the eei property.
     * 
     * @param value
     *     allowed object is
     *     {@link Prismeei }
     *     
     */
    public void setEei(Prismeei value) {
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
     * Gets the value of the ipList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ipList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIpList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PrismipShort }
     * 
     * 
     */
    public List<PrismipShort> getIpList() {
        if (ipList == null) {
            ipList = new ArrayList<PrismipShort>();
        }
        return this.ipList;
    }

    /**
     * Gets the value of the producerList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the producerList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProducerList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PrismProducer }
     * 
     * 
     */
    public List<PrismProducer> getProducerList() {
        if (producerList == null) {
            producerList = new ArrayList<PrismProducer>();
        }
        return this.producerList;
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
     * {@link PrismReport }
     * 
     * 
     */
    public List<PrismReport> getReportList() {
        if (reportList == null) {
            reportList = new ArrayList<PrismReport>();
        }
        return this.reportList;
    }

    /**
     * Gets the value of the targetList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the targetList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTargetList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PrismTargetShort }
     * 
     * 
     */
    public List<PrismTargetShort> getTargetList() {
        if (targetList == null) {
            targetList = new ArrayList<PrismTargetShort>();
        }
        return this.targetList;
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
     * Gets the value of the respOrg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRespOrg() {
        return respOrg;
    }

    /**
     * Sets the value of the respOrg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRespOrg(String value) {
        this.respOrg = value;
    }

}
