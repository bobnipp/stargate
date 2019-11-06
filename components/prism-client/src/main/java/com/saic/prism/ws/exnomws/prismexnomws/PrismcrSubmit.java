
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
 * <p>Java class for prismcrSubmit complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prismcrSubmit"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="priority" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="stopDate" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="adHocFlag" type="{http://PRISMExNomWS.exnomws.ws.prism.saic.com}prismStandingAdhocFlag"/&gt;
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="earliestImageTime" type="{http://www.w3.org/2001/XMLSchema}time" minOccurs="0"/&gt;
 *         &lt;element name="latestImageTime" type="{http://www.w3.org/2001/XMLSchema}time" minOccurs="0"/&gt;
 *         &lt;element name="period" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="nightPriority" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="percentCoverage" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="type" type="{http://PRISMExNomWS.exnomws.ws.prism.saic.com}prismRequirementType"/&gt;
 *         &lt;element name="imagesPerPeriod" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="tgtType" type="{http://PRISMExNomWS.exnomws.ws.prism.saic.com}prismTargetType" minOccurs="0"/&gt;
 *         &lt;element name="considerForAllocation" type="{http://PRISMExNomWS.exnomws.ws.prism.saic.com}prismYesNoFlag" minOccurs="0"/&gt;
 *         &lt;element name="masintFlag" type="{http://PRISMExNomWS.exnomws.ws.prism.saic.com}prismYesNoFlag" minOccurs="0"/&gt;
 *         &lt;element name="masintMinAzDev" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="masintMaxAzDev" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="masintMinElevDevAngle" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="masintMaxElevDevAngle" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="masintReqComplexData" type="{http://PRISMExNomWS.exnomws.ws.prism.saic.com}prismYesNoFlag" minOccurs="0"/&gt;
 *         &lt;element name="periodType" type="{http://PRISMExNomWS.exnomws.ws.prism.saic.com}prismcrPeriodType" minOccurs="0"/&gt;
 *         &lt;element name="minCci" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="maxCci" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="masintTypeKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="surveillance" type="{http://PRISMExNomWS.exnomws.ws.prism.saic.com}prismcrSurveillance" minOccurs="0"/&gt;
 *         &lt;element name="recurrence" type="{http://PRISMExNomWS.exnomws.ws.prism.saic.com}prismcrRecurrence" minOccurs="0"/&gt;
 *         &lt;element name="countryCodeKeyList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="geoRegionKeyList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="ipKeyList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="sensorList" type="{http://PRISMExNomWS.exnomws.ws.prism.saic.com}prismcrSensorSubmit" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="erList" type="{http://PRISMExNomWS.exnomws.ws.prism.saic.com}prismerSubmit" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="targetKeyList" type="{http://PRISMExNomWS.exnomws.ws.prism.saic.com}prismcrTargetSubmit" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="targetIdList" type="{http://PRISMExNomWS.exnomws.ws.prism.saic.com}prismcrTargetSubmit" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="rmsSpecific" type="{http://PRISMExNomWS.exnomws.ws.prism.saic.com}prismcrRMSSpecific" minOccurs="0"/&gt;
 *         &lt;element name="aodLinkList" type="{http://PRISMExNomWS.exnomws.ws.prism.saic.com}prismaodLink" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prismcrSubmit", propOrder = {
    "key",
    "id",
    "name",
    "priority",
    "startDate",
    "stopDate",
    "adHocFlag",
    "description",
    "earliestImageTime",
    "latestImageTime",
    "period",
    "nightPriority",
    "percentCoverage",
    "type",
    "imagesPerPeriod",
    "tgtType",
    "considerForAllocation",
    "masintFlag",
    "masintMinAzDev",
    "masintMaxAzDev",
    "masintMinElevDevAngle",
    "masintMaxElevDevAngle",
    "masintReqComplexData",
    "periodType",
    "minCci",
    "maxCci",
    "masintTypeKey",
    "surveillance",
    "recurrence",
    "countryCodeKeyList",
    "geoRegionKeyList",
    "ipKeyList",
    "sensorList",
    "erList",
    "targetKeyList",
    "targetIdList",
    "rmsSpecific",
    "aodLinkList"
})
public class PrismcrSubmit {

    protected String key;
    protected String id;
    @XmlElement(required = true)
    protected String name;
    protected int priority;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar startDate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar stopDate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected PrismStandingAdhocFlag adHocFlag;
    protected String description;
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar earliestImageTime;
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar latestImageTime;
    protected Integer period;
    protected Integer nightPriority;
    protected Integer percentCoverage;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected PrismRequirementType type;
    protected Integer imagesPerPeriod;
    @XmlSchemaType(name = "string")
    protected PrismTargetType tgtType;
    @XmlSchemaType(name = "string")
    protected PrismYesNoFlag considerForAllocation;
    @XmlSchemaType(name = "string")
    protected PrismYesNoFlag masintFlag;
    protected Double masintMinAzDev;
    protected Double masintMaxAzDev;
    protected Double masintMinElevDevAngle;
    protected Double masintMaxElevDevAngle;
    @XmlSchemaType(name = "string")
    protected PrismYesNoFlag masintReqComplexData;
    @XmlSchemaType(name = "string")
    protected PrismcrPeriodType periodType;
    protected Integer minCci;
    protected Integer maxCci;
    protected String masintTypeKey;
    protected PrismcrSurveillance surveillance;
    protected PrismcrRecurrence recurrence;
    protected List<String> countryCodeKeyList;
    protected List<String> geoRegionKeyList;
    protected List<String> ipKeyList;
    protected List<PrismcrSensorSubmit> sensorList;
    protected List<PrismerSubmit> erList;
    protected List<PrismcrTargetSubmit> targetKeyList;
    protected List<PrismcrTargetSubmit> targetIdList;
    protected PrismcrRMSSpecific rmsSpecific;
    protected List<PrismaodLink> aodLinkList;

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
     * Gets the value of the earliestImageTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEarliestImageTime() {
        return earliestImageTime;
    }

    /**
     * Sets the value of the earliestImageTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEarliestImageTime(XMLGregorianCalendar value) {
        this.earliestImageTime = value;
    }

    /**
     * Gets the value of the latestImageTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLatestImageTime() {
        return latestImageTime;
    }

    /**
     * Sets the value of the latestImageTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLatestImageTime(XMLGregorianCalendar value) {
        this.latestImageTime = value;
    }

    /**
     * Gets the value of the period property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPeriod() {
        return period;
    }

    /**
     * Sets the value of the period property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPeriod(Integer value) {
        this.period = value;
    }

    /**
     * Gets the value of the nightPriority property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNightPriority() {
        return nightPriority;
    }

    /**
     * Sets the value of the nightPriority property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNightPriority(Integer value) {
        this.nightPriority = value;
    }

    /**
     * Gets the value of the percentCoverage property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPercentCoverage() {
        return percentCoverage;
    }

    /**
     * Sets the value of the percentCoverage property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPercentCoverage(Integer value) {
        this.percentCoverage = value;
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
     * Gets the value of the imagesPerPeriod property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getImagesPerPeriod() {
        return imagesPerPeriod;
    }

    /**
     * Sets the value of the imagesPerPeriod property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setImagesPerPeriod(Integer value) {
        this.imagesPerPeriod = value;
    }

    /**
     * Gets the value of the tgtType property.
     * 
     * @return
     *     possible object is
     *     {@link PrismTargetType }
     *     
     */
    public PrismTargetType getTgtType() {
        return tgtType;
    }

    /**
     * Sets the value of the tgtType property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismTargetType }
     *     
     */
    public void setTgtType(PrismTargetType value) {
        this.tgtType = value;
    }

    /**
     * Gets the value of the considerForAllocation property.
     * 
     * @return
     *     possible object is
     *     {@link PrismYesNoFlag }
     *     
     */
    public PrismYesNoFlag getConsiderForAllocation() {
        return considerForAllocation;
    }

    /**
     * Sets the value of the considerForAllocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismYesNoFlag }
     *     
     */
    public void setConsiderForAllocation(PrismYesNoFlag value) {
        this.considerForAllocation = value;
    }

    /**
     * Gets the value of the masintFlag property.
     * 
     * @return
     *     possible object is
     *     {@link PrismYesNoFlag }
     *     
     */
    public PrismYesNoFlag getMasintFlag() {
        return masintFlag;
    }

    /**
     * Sets the value of the masintFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismYesNoFlag }
     *     
     */
    public void setMasintFlag(PrismYesNoFlag value) {
        this.masintFlag = value;
    }

    /**
     * Gets the value of the masintMinAzDev property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMasintMinAzDev() {
        return masintMinAzDev;
    }

    /**
     * Sets the value of the masintMinAzDev property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMasintMinAzDev(Double value) {
        this.masintMinAzDev = value;
    }

    /**
     * Gets the value of the masintMaxAzDev property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMasintMaxAzDev() {
        return masintMaxAzDev;
    }

    /**
     * Sets the value of the masintMaxAzDev property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMasintMaxAzDev(Double value) {
        this.masintMaxAzDev = value;
    }

    /**
     * Gets the value of the masintMinElevDevAngle property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMasintMinElevDevAngle() {
        return masintMinElevDevAngle;
    }

    /**
     * Sets the value of the masintMinElevDevAngle property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMasintMinElevDevAngle(Double value) {
        this.masintMinElevDevAngle = value;
    }

    /**
     * Gets the value of the masintMaxElevDevAngle property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMasintMaxElevDevAngle() {
        return masintMaxElevDevAngle;
    }

    /**
     * Sets the value of the masintMaxElevDevAngle property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMasintMaxElevDevAngle(Double value) {
        this.masintMaxElevDevAngle = value;
    }

    /**
     * Gets the value of the masintReqComplexData property.
     * 
     * @return
     *     possible object is
     *     {@link PrismYesNoFlag }
     *     
     */
    public PrismYesNoFlag getMasintReqComplexData() {
        return masintReqComplexData;
    }

    /**
     * Sets the value of the masintReqComplexData property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismYesNoFlag }
     *     
     */
    public void setMasintReqComplexData(PrismYesNoFlag value) {
        this.masintReqComplexData = value;
    }

    /**
     * Gets the value of the periodType property.
     * 
     * @return
     *     possible object is
     *     {@link PrismcrPeriodType }
     *     
     */
    public PrismcrPeriodType getPeriodType() {
        return periodType;
    }

    /**
     * Sets the value of the periodType property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismcrPeriodType }
     *     
     */
    public void setPeriodType(PrismcrPeriodType value) {
        this.periodType = value;
    }

    /**
     * Gets the value of the minCci property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMinCci() {
        return minCci;
    }

    /**
     * Sets the value of the minCci property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMinCci(Integer value) {
        this.minCci = value;
    }

    /**
     * Gets the value of the maxCci property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMaxCci() {
        return maxCci;
    }

    /**
     * Sets the value of the maxCci property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMaxCci(Integer value) {
        this.maxCci = value;
    }

    /**
     * Gets the value of the masintTypeKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMasintTypeKey() {
        return masintTypeKey;
    }

    /**
     * Sets the value of the masintTypeKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMasintTypeKey(String value) {
        this.masintTypeKey = value;
    }

    /**
     * Gets the value of the surveillance property.
     * 
     * @return
     *     possible object is
     *     {@link PrismcrSurveillance }
     *     
     */
    public PrismcrSurveillance getSurveillance() {
        return surveillance;
    }

    /**
     * Sets the value of the surveillance property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismcrSurveillance }
     *     
     */
    public void setSurveillance(PrismcrSurveillance value) {
        this.surveillance = value;
    }

    /**
     * Gets the value of the recurrence property.
     * 
     * @return
     *     possible object is
     *     {@link PrismcrRecurrence }
     *     
     */
    public PrismcrRecurrence getRecurrence() {
        return recurrence;
    }

    /**
     * Sets the value of the recurrence property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismcrRecurrence }
     *     
     */
    public void setRecurrence(PrismcrRecurrence value) {
        this.recurrence = value;
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
     * {@link PrismcrSensorSubmit }
     * 
     * 
     */
    public List<PrismcrSensorSubmit> getSensorList() {
        if (sensorList == null) {
            sensorList = new ArrayList<PrismcrSensorSubmit>();
        }
        return this.sensorList;
    }

    /**
     * Gets the value of the erList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the erList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getErList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PrismerSubmit }
     * 
     * 
     */
    public List<PrismerSubmit> getErList() {
        if (erList == null) {
            erList = new ArrayList<PrismerSubmit>();
        }
        return this.erList;
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
     * {@link PrismcrTargetSubmit }
     * 
     * 
     */
    public List<PrismcrTargetSubmit> getTargetKeyList() {
        if (targetKeyList == null) {
            targetKeyList = new ArrayList<PrismcrTargetSubmit>();
        }
        return this.targetKeyList;
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
     * {@link PrismcrTargetSubmit }
     * 
     * 
     */
    public List<PrismcrTargetSubmit> getTargetIdList() {
        if (targetIdList == null) {
            targetIdList = new ArrayList<PrismcrTargetSubmit>();
        }
        return this.targetIdList;
    }

    /**
     * Gets the value of the rmsSpecific property.
     * 
     * @return
     *     possible object is
     *     {@link PrismcrRMSSpecific }
     *     
     */
    public PrismcrRMSSpecific getRmsSpecific() {
        return rmsSpecific;
    }

    /**
     * Sets the value of the rmsSpecific property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismcrRMSSpecific }
     *     
     */
    public void setRmsSpecific(PrismcrRMSSpecific value) {
        this.rmsSpecific = value;
    }

    /**
     * Gets the value of the aodLinkList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the aodLinkList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAodLinkList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PrismaodLink }
     * 
     * 
     */
    public List<PrismaodLink> getAodLinkList() {
        if (aodLinkList == null) {
            aodLinkList = new ArrayList<PrismaodLink>();
        }
        return this.aodLinkList;
    }

}
