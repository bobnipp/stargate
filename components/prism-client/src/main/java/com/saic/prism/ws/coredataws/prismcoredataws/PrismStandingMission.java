
package com.saic.prism.ws.coredataws.prismcoredataws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prismStandingMission complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prismStandingMission"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="respOrg" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="programCode" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismProgram"/&gt;
 *         &lt;element name="trackList" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismTrack" maxOccurs="unbounded"/&gt;
 *         &lt;element name="platformList" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismPlatform" maxOccurs="unbounded"/&gt;
 *         &lt;element name="primaryPurpose" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="secondaryPurpose" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="riskCapabilities" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismRiskCapability" minOccurs="0"/&gt;
 *         &lt;element name="collectionProbability" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismCollectionProbability" minOccurs="0"/&gt;
 *         &lt;element name="riskIntention" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismRiskIntention" minOccurs="0"/&gt;
 *         &lt;element name="politicalSensitivity" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismPoliticalSensitivity" minOccurs="0"/&gt;
 *         &lt;element name="anticipatedGain" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismAnticipatedGain" minOccurs="0"/&gt;
 *         &lt;element name="strategicInfluence" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismStrategicInfluence" minOccurs="0"/&gt;
 *         &lt;element name="dataInfo" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismDataInfo"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prismStandingMission", propOrder = {
    "key",
    "name",
    "respOrg",
    "programCode",
    "trackList",
    "platformList",
    "primaryPurpose",
    "secondaryPurpose",
    "riskCapabilities",
    "collectionProbability",
    "riskIntention",
    "politicalSensitivity",
    "anticipatedGain",
    "strategicInfluence",
    "dataInfo"
})
public class PrismStandingMission {

    @XmlElement(required = true)
    protected String key;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String respOrg;
    @XmlElement(required = true)
    protected PrismProgram programCode;
    @XmlElement(required = true)
    protected List<PrismTrack> trackList;
    @XmlElement(required = true)
    protected List<PrismPlatform> platformList;
    protected String primaryPurpose;
    protected String secondaryPurpose;
    protected PrismRiskCapability riskCapabilities;
    protected PrismCollectionProbability collectionProbability;
    protected PrismRiskIntention riskIntention;
    protected PrismPoliticalSensitivity politicalSensitivity;
    protected PrismAnticipatedGain anticipatedGain;
    protected PrismStrategicInfluence strategicInfluence;
    @XmlElement(required = true)
    protected PrismDataInfo dataInfo;

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
     * Gets the value of the programCode property.
     * 
     * @return
     *     possible object is
     *     {@link PrismProgram }
     *     
     */
    public PrismProgram getProgramCode() {
        return programCode;
    }

    /**
     * Sets the value of the programCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismProgram }
     *     
     */
    public void setProgramCode(PrismProgram value) {
        this.programCode = value;
    }

    /**
     * Gets the value of the trackList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the trackList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTrackList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PrismTrack }
     * 
     * 
     */
    public List<PrismTrack> getTrackList() {
        if (trackList == null) {
            trackList = new ArrayList<PrismTrack>();
        }
        return this.trackList;
    }

    /**
     * Gets the value of the platformList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the platformList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPlatformList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PrismPlatform }
     * 
     * 
     */
    public List<PrismPlatform> getPlatformList() {
        if (platformList == null) {
            platformList = new ArrayList<PrismPlatform>();
        }
        return this.platformList;
    }

    /**
     * Gets the value of the primaryPurpose property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimaryPurpose() {
        return primaryPurpose;
    }

    /**
     * Sets the value of the primaryPurpose property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimaryPurpose(String value) {
        this.primaryPurpose = value;
    }

    /**
     * Gets the value of the secondaryPurpose property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecondaryPurpose() {
        return secondaryPurpose;
    }

    /**
     * Sets the value of the secondaryPurpose property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecondaryPurpose(String value) {
        this.secondaryPurpose = value;
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

}
