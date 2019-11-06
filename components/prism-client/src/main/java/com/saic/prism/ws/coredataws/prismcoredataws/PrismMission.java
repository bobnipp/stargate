
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
 * <p>Java class for prismMission complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prismMission"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="prismId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="localId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="track" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismTrack"/&gt;
 *         &lt;element name="respOrg" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="platform" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismPlatform"/&gt;
 *         &lt;element name="status" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismMissionStatus"/&gt;
 *         &lt;element name="statusDate" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="sensorList" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismMissionSensor" maxOccurs="unbounded"/&gt;
 *         &lt;element name="riskCapabilities" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismRiskCapability" minOccurs="0"/&gt;
 *         &lt;element name="collectionProbability" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismCollectionProbability" minOccurs="0"/&gt;
 *         &lt;element name="riskIntention" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismRiskIntention" minOccurs="0"/&gt;
 *         &lt;element name="politicalSensitivity" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismPoliticalSensitivity" minOccurs="0"/&gt;
 *         &lt;element name="anticipatedGain" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismAnticipatedGain" minOccurs="0"/&gt;
 *         &lt;element name="strategicInfluence" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismStrategicInfluence" minOccurs="0"/&gt;
 *         &lt;element name="plannedTakeOffBase" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismBase" minOccurs="0"/&gt;
 *         &lt;element name="plannedTakeOffDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="plannedTakeOffTime" type="{http://www.w3.org/2001/XMLSchema}time" minOccurs="0"/&gt;
 *         &lt;element name="actualTakeOffBase" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismBase" minOccurs="0"/&gt;
 *         &lt;element name="actualTakeOffDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="actualTakeOffTime" type="{http://www.w3.org/2001/XMLSchema}time" minOccurs="0"/&gt;
 *         &lt;element name="plannedLandingBase" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismBase" minOccurs="0"/&gt;
 *         &lt;element name="plannedLandingDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="plannedLandingTime" type="{http://www.w3.org/2001/XMLSchema}time" minOccurs="0"/&gt;
 *         &lt;element name="actualLandingBase" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismBase" minOccurs="0"/&gt;
 *         &lt;element name="actualLandingDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="actualLandingTime" type="{http://www.w3.org/2001/XMLSchema}time" minOccurs="0"/&gt;
 *         &lt;element name="dataInfo" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismDataInfo"/&gt;
 *         &lt;element name="standingMission" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismStandingMission" minOccurs="0"/&gt;
 *         &lt;element name="onOffStationOffsetList" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismOnOffStationOffset" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prismMission", propOrder = {
    "key",
    "id",
    "prismId",
    "localId",
    "track",
    "respOrg",
    "platform",
    "status",
    "statusDate",
    "sensorList",
    "riskCapabilities",
    "collectionProbability",
    "riskIntention",
    "politicalSensitivity",
    "anticipatedGain",
    "strategicInfluence",
    "plannedTakeOffBase",
    "plannedTakeOffDate",
    "plannedTakeOffTime",
    "actualTakeOffBase",
    "actualTakeOffDate",
    "actualTakeOffTime",
    "plannedLandingBase",
    "plannedLandingDate",
    "plannedLandingTime",
    "actualLandingBase",
    "actualLandingDate",
    "actualLandingTime",
    "dataInfo",
    "standingMission",
    "onOffStationOffsetList"
})
public class PrismMission {

    @XmlElement(required = true)
    protected String key;
    @XmlElement(required = true)
    protected String id;
    @XmlElement(required = true)
    protected String prismId;
    @XmlElement(required = true)
    protected String localId;
    @XmlElement(required = true)
    protected PrismTrack track;
    @XmlElement(required = true)
    protected String respOrg;
    @XmlElement(required = true)
    protected PrismPlatform platform;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected PrismMissionStatus status;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar statusDate;
    @XmlElement(required = true)
    protected List<PrismMissionSensor> sensorList;
    protected PrismRiskCapability riskCapabilities;
    protected PrismCollectionProbability collectionProbability;
    protected PrismRiskIntention riskIntention;
    protected PrismPoliticalSensitivity politicalSensitivity;
    protected PrismAnticipatedGain anticipatedGain;
    protected PrismStrategicInfluence strategicInfluence;
    protected PrismBase plannedTakeOffBase;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar plannedTakeOffDate;
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar plannedTakeOffTime;
    protected PrismBase actualTakeOffBase;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar actualTakeOffDate;
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar actualTakeOffTime;
    protected PrismBase plannedLandingBase;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar plannedLandingDate;
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar plannedLandingTime;
    protected PrismBase actualLandingBase;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar actualLandingDate;
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar actualLandingTime;
    @XmlElement(required = true)
    protected PrismDataInfo dataInfo;
    protected PrismStandingMission standingMission;
    protected List<PrismOnOffStationOffset> onOffStationOffsetList;

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
     * Gets the value of the track property.
     * 
     * @return
     *     possible object is
     *     {@link PrismTrack }
     *     
     */
    public PrismTrack getTrack() {
        return track;
    }

    /**
     * Sets the value of the track property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismTrack }
     *     
     */
    public void setTrack(PrismTrack value) {
        this.track = value;
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

    /**
     * Gets the value of the platform property.
     * 
     * @return
     *     possible object is
     *     {@link PrismPlatform }
     *     
     */
    public PrismPlatform getPlatform() {
        return platform;
    }

    /**
     * Sets the value of the platform property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismPlatform }
     *     
     */
    public void setPlatform(PrismPlatform value) {
        this.platform = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link PrismMissionStatus }
     *     
     */
    public PrismMissionStatus getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismMissionStatus }
     *     
     */
    public void setStatus(PrismMissionStatus value) {
        this.status = value;
    }

    /**
     * Gets the value of the statusDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStatusDate() {
        return statusDate;
    }

    /**
     * Sets the value of the statusDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStatusDate(XMLGregorianCalendar value) {
        this.statusDate = value;
    }

    /**
     * Gets the value of the sensorList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sensorList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSensorList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PrismMissionSensor }
     * 
     * 
     */
    public List<PrismMissionSensor> getSensorList() {
        if (sensorList == null) {
            sensorList = new ArrayList<PrismMissionSensor>();
        }
        return this.sensorList;
    }

    /**
     * Gets the value of the riskCapabilities property.
     * 
     * @return
     *     possible object is
     *     {@link PrismRiskCapability }
     *     
     */
    public PrismRiskCapability getRiskCapabilities() {
        return riskCapabilities;
    }

    /**
     * Sets the value of the riskCapabilities property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismRiskCapability }
     *     
     */
    public void setRiskCapabilities(PrismRiskCapability value) {
        this.riskCapabilities = value;
    }

    /**
     * Gets the value of the collectionProbability property.
     * 
     * @return
     *     possible object is
     *     {@link PrismCollectionProbability }
     *     
     */
    public PrismCollectionProbability getCollectionProbability() {
        return collectionProbability;
    }

    /**
     * Sets the value of the collectionProbability property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismCollectionProbability }
     *     
     */
    public void setCollectionProbability(PrismCollectionProbability value) {
        this.collectionProbability = value;
    }

    /**
     * Gets the value of the riskIntention property.
     * 
     * @return
     *     possible object is
     *     {@link PrismRiskIntention }
     *     
     */
    public PrismRiskIntention getRiskIntention() {
        return riskIntention;
    }

    /**
     * Sets the value of the riskIntention property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismRiskIntention }
     *     
     */
    public void setRiskIntention(PrismRiskIntention value) {
        this.riskIntention = value;
    }

    /**
     * Gets the value of the politicalSensitivity property.
     * 
     * @return
     *     possible object is
     *     {@link PrismPoliticalSensitivity }
     *     
     */
    public PrismPoliticalSensitivity getPoliticalSensitivity() {
        return politicalSensitivity;
    }

    /**
     * Sets the value of the politicalSensitivity property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismPoliticalSensitivity }
     *     
     */
    public void setPoliticalSensitivity(PrismPoliticalSensitivity value) {
        this.politicalSensitivity = value;
    }

    /**
     * Gets the value of the anticipatedGain property.
     * 
     * @return
     *     possible object is
     *     {@link PrismAnticipatedGain }
     *     
     */
    public PrismAnticipatedGain getAnticipatedGain() {
        return anticipatedGain;
    }

    /**
     * Sets the value of the anticipatedGain property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismAnticipatedGain }
     *     
     */
    public void setAnticipatedGain(PrismAnticipatedGain value) {
        this.anticipatedGain = value;
    }

    /**
     * Gets the value of the strategicInfluence property.
     * 
     * @return
     *     possible object is
     *     {@link PrismStrategicInfluence }
     *     
     */
    public PrismStrategicInfluence getStrategicInfluence() {
        return strategicInfluence;
    }

    /**
     * Sets the value of the strategicInfluence property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismStrategicInfluence }
     *     
     */
    public void setStrategicInfluence(PrismStrategicInfluence value) {
        this.strategicInfluence = value;
    }

    /**
     * Gets the value of the plannedTakeOffBase property.
     * 
     * @return
     *     possible object is
     *     {@link PrismBase }
     *     
     */
    public PrismBase getPlannedTakeOffBase() {
        return plannedTakeOffBase;
    }

    /**
     * Sets the value of the plannedTakeOffBase property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismBase }
     *     
     */
    public void setPlannedTakeOffBase(PrismBase value) {
        this.plannedTakeOffBase = value;
    }

    /**
     * Gets the value of the plannedTakeOffDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPlannedTakeOffDate() {
        return plannedTakeOffDate;
    }

    /**
     * Sets the value of the plannedTakeOffDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPlannedTakeOffDate(XMLGregorianCalendar value) {
        this.plannedTakeOffDate = value;
    }

    /**
     * Gets the value of the plannedTakeOffTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPlannedTakeOffTime() {
        return plannedTakeOffTime;
    }

    /**
     * Sets the value of the plannedTakeOffTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPlannedTakeOffTime(XMLGregorianCalendar value) {
        this.plannedTakeOffTime = value;
    }

    /**
     * Gets the value of the actualTakeOffBase property.
     * 
     * @return
     *     possible object is
     *     {@link PrismBase }
     *     
     */
    public PrismBase getActualTakeOffBase() {
        return actualTakeOffBase;
    }

    /**
     * Sets the value of the actualTakeOffBase property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismBase }
     *     
     */
    public void setActualTakeOffBase(PrismBase value) {
        this.actualTakeOffBase = value;
    }

    /**
     * Gets the value of the actualTakeOffDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getActualTakeOffDate() {
        return actualTakeOffDate;
    }

    /**
     * Sets the value of the actualTakeOffDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setActualTakeOffDate(XMLGregorianCalendar value) {
        this.actualTakeOffDate = value;
    }

    /**
     * Gets the value of the actualTakeOffTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getActualTakeOffTime() {
        return actualTakeOffTime;
    }

    /**
     * Sets the value of the actualTakeOffTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setActualTakeOffTime(XMLGregorianCalendar value) {
        this.actualTakeOffTime = value;
    }

    /**
     * Gets the value of the plannedLandingBase property.
     * 
     * @return
     *     possible object is
     *     {@link PrismBase }
     *     
     */
    public PrismBase getPlannedLandingBase() {
        return plannedLandingBase;
    }

    /**
     * Sets the value of the plannedLandingBase property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismBase }
     *     
     */
    public void setPlannedLandingBase(PrismBase value) {
        this.plannedLandingBase = value;
    }

    /**
     * Gets the value of the plannedLandingDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPlannedLandingDate() {
        return plannedLandingDate;
    }

    /**
     * Sets the value of the plannedLandingDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPlannedLandingDate(XMLGregorianCalendar value) {
        this.plannedLandingDate = value;
    }

    /**
     * Gets the value of the plannedLandingTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPlannedLandingTime() {
        return plannedLandingTime;
    }

    /**
     * Sets the value of the plannedLandingTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPlannedLandingTime(XMLGregorianCalendar value) {
        this.plannedLandingTime = value;
    }

    /**
     * Gets the value of the actualLandingBase property.
     * 
     * @return
     *     possible object is
     *     {@link PrismBase }
     *     
     */
    public PrismBase getActualLandingBase() {
        return actualLandingBase;
    }

    /**
     * Sets the value of the actualLandingBase property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismBase }
     *     
     */
    public void setActualLandingBase(PrismBase value) {
        this.actualLandingBase = value;
    }

    /**
     * Gets the value of the actualLandingDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getActualLandingDate() {
        return actualLandingDate;
    }

    /**
     * Sets the value of the actualLandingDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setActualLandingDate(XMLGregorianCalendar value) {
        this.actualLandingDate = value;
    }

    /**
     * Gets the value of the actualLandingTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getActualLandingTime() {
        return actualLandingTime;
    }

    /**
     * Sets the value of the actualLandingTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setActualLandingTime(XMLGregorianCalendar value) {
        this.actualLandingTime = value;
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
     * Gets the value of the standingMission property.
     * 
     * @return
     *     possible object is
     *     {@link PrismStandingMission }
     *     
     */
    public PrismStandingMission getStandingMission() {
        return standingMission;
    }

    /**
     * Sets the value of the standingMission property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismStandingMission }
     *     
     */
    public void setStandingMission(PrismStandingMission value) {
        this.standingMission = value;
    }

    /**
     * Gets the value of the onOffStationOffsetList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the onOffStationOffsetList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOnOffStationOffsetList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PrismOnOffStationOffset }
     * 
     * 
     */
    public List<PrismOnOffStationOffset> getOnOffStationOffsetList() {
        if (onOffStationOffsetList == null) {
            onOffStationOffsetList = new ArrayList<PrismOnOffStationOffset>();
        }
        return this.onOffStationOffsetList;
    }

}
