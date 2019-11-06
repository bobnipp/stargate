
package com.saic.prism.ws.coredataws.prismcoredataws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prismcrSurveillance complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prismcrSurveillance"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="survType" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismcrSurveillanceType"/&gt;
 *         &lt;element name="revisitFreq" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="dwellTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="cvgPeriod" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="cvgReqd" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="pursuitFlag" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismYesNoFlag" minOccurs="0"/&gt;
 *         &lt;element name="instructions" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prismcrSurveillance", propOrder = {
    "survType",
    "revisitFreq",
    "dwellTime",
    "cvgPeriod",
    "cvgReqd",
    "pursuitFlag",
    "instructions"
})
public class PrismcrSurveillance {

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected PrismcrSurveillanceType survType;
    protected Integer revisitFreq;
    protected Integer dwellTime;
    protected Integer cvgPeriod;
    protected int cvgReqd;
    @XmlSchemaType(name = "string")
    protected PrismYesNoFlag pursuitFlag;
    protected String instructions;

    /**
     * Gets the value of the survType property.
     * 
     * @return
     *     possible object is
     *     {@link PrismcrSurveillanceType }
     *     
     */
    public PrismcrSurveillanceType getSurvType() {
        return survType;
    }

    /**
     * Sets the value of the survType property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismcrSurveillanceType }
     *     
     */
    public void setSurvType(PrismcrSurveillanceType value) {
        this.survType = value;
    }

    /**
     * Gets the value of the revisitFreq property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRevisitFreq() {
        return revisitFreq;
    }

    /**
     * Sets the value of the revisitFreq property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRevisitFreq(Integer value) {
        this.revisitFreq = value;
    }

    /**
     * Gets the value of the dwellTime property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDwellTime() {
        return dwellTime;
    }

    /**
     * Sets the value of the dwellTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDwellTime(Integer value) {
        this.dwellTime = value;
    }

    /**
     * Gets the value of the cvgPeriod property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCvgPeriod() {
        return cvgPeriod;
    }

    /**
     * Sets the value of the cvgPeriod property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCvgPeriod(Integer value) {
        this.cvgPeriod = value;
    }

    /**
     * Gets the value of the cvgReqd property.
     * 
     */
    public int getCvgReqd() {
        return cvgReqd;
    }

    /**
     * Sets the value of the cvgReqd property.
     * 
     */
    public void setCvgReqd(int value) {
        this.cvgReqd = value;
    }

    /**
     * Gets the value of the pursuitFlag property.
     * 
     * @return
     *     possible object is
     *     {@link PrismYesNoFlag }
     *     
     */
    public PrismYesNoFlag getPursuitFlag() {
        return pursuitFlag;
    }

    /**
     * Sets the value of the pursuitFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismYesNoFlag }
     *     
     */
    public void setPursuitFlag(PrismYesNoFlag value) {
        this.pursuitFlag = value;
    }

    /**
     * Gets the value of the instructions property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstructions() {
        return instructions;
    }

    /**
     * Sets the value of the instructions property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstructions(String value) {
        this.instructions = value;
    }

}
