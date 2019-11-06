
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
 * <p>Java class for prismcr complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prismcr"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="prismId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="priority" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="stopDate" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="adHocFlag" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismStandingAdhocFlag" minOccurs="0"/&gt;
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="earliestImageTime" type="{http://www.w3.org/2001/XMLSchema}time" minOccurs="0"/&gt;
 *         &lt;element name="latestImageTime" type="{http://www.w3.org/2001/XMLSchema}time" minOccurs="0"/&gt;
 *         &lt;element name="period" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="nightPriority" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="percentCoverage" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="strategy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="type" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismRequirementType" minOccurs="0"/&gt;
 *         &lt;element name="imagesPerPeriod" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="tgtType" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismTargetType" minOccurs="0"/&gt;
 *         &lt;element name="considerForAllocation" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismYesNoFlag" minOccurs="0"/&gt;
 *         &lt;element name="masintFlag" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismYesNoFlag" minOccurs="0"/&gt;
 *         &lt;element name="masintMinAzDev" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="masintMaxAzDev" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="masintMinElevDevAngle" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="masintMaxElevDevAngle" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="masintReqComplexData" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismYesNoFlag" minOccurs="0"/&gt;
 *         &lt;element name="periodType" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismcrPeriodType" minOccurs="0"/&gt;
 *         &lt;element name="minCci" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="maxCci" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="masintType" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismMasintType" minOccurs="0"/&gt;
 *         &lt;element name="surveillance" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismcrSurveillance" minOccurs="0"/&gt;
 *         &lt;element name="recurrence" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismcrRecurrence" minOccurs="0"/&gt;
 *         &lt;element name="countryCodeList" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismCountryCode" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="geoRegionList" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismGeoRegion" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="ipList" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismipShort" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="sensorList" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismcrSensor" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="erList" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismerShort" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="targetList" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismcrTarget" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="dataInfo" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismDataInfo"/&gt;
 *         &lt;element name="respOrg" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="rmsSpecific" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismcrRMSSpecific" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prismcr", propOrder = {
    "key",
    "id",
    "prismId",
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
    "strategy",
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
    "masintType",
    "surveillance",
    "recurrence",
    "countryCodeList",
    "geoRegionList",
    "ipList",
    "sensorList",
    "erList",
    "targetList",
    "dataInfo",
    "respOrg",
    "rmsSpecific"
})
public class Prismcr {

    @XmlElement(required = true)
    protected String key;
    @XmlElement(required = true)
    protected String id;
    @XmlElement(required = true)
    protected String prismId;
    @XmlElement(required = true)
    protected String name;
    protected int priority;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar startDate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar stopDate;
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
    protected String strategy;
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
    protected PrismMasintType masintType;
    protected PrismcrSurveillance surveillance;
    protected PrismcrRecurrence recurrence;
    protected List<PrismCountryCode> countryCodeList;
    protected List<PrismGeoRegion> geoRegionList;
    protected List<PrismipShort> ipList;
    protected List<PrismcrSensor> sensorList;
    protected List<PrismerShort> erList;
    protected List<PrismcrTarget> targetList;
    @XmlElement(required = true)
    protected PrismDataInfo dataInfo;
    @XmlElement(required = true)
    protected String respOrg;
    protected PrismcrRMSSpecific rmsSpecific;

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
     * Gets the value of the strategy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrategy() {
        return strategy;
    }

    /**
     * Sets the value of the strategy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrategy(String value) {
        this.strategy = value;
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
     * Gets the value of the masintType property.
     * 
     * @return
     *     possible object is
     *     {@link PrismMasintType }
     *     
     */
    public PrismMasintType getMasintType() {
        return masintType;
    }

    /**
     * Sets the value of the masintType property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismMasintType }
     *     
     */
    public void setMasintType(PrismMasintType value) {
        this.masintType = value;
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
     * {@link PrismcrSensor }
     * 
     * 
     */
    public List<PrismcrSensor> getSensorList() {
        if (sensorList == null) {
            sensorList = new ArrayList<PrismcrSensor>();
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
     * {@link PrismerShort }
     * 
     * 
     */
    public List<PrismerShort> getErList() {
        if (erList == null) {
            erList = new ArrayList<PrismerShort>();
        }
        return this.erList;
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
     * {@link PrismcrTarget }
     * 
     * 
     */
    public List<PrismcrTarget> getTargetList() {
        if (targetList == null) {
            targetList = new ArrayList<PrismcrTarget>();
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

}
