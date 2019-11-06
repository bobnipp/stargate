
package com.saic.prism.ws.exnomws.prismexnomws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prismcrRMSSpecific complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prismcrRMSSpecific"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="minAge" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="maxAge" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="cFlag" type="{http://PRISMExNomWS.exnomws.ws.prism.saic.com}prismYesNoFlag" minOccurs="0"/&gt;
 *         &lt;element name="weatherThreshold" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="decompImage" type="{http://PRISMExNomWS.exnomws.ws.prism.saic.com}prismYesNoFlag" minOccurs="0"/&gt;
 *         &lt;element name="collOverrideFlag" type="{http://PRISMExNomWS.exnomws.ws.prism.saic.com}prismYesNoFlag" minOccurs="0"/&gt;
 *         &lt;element name="radiometricFlag" type="{http://PRISMExNomWS.exnomws.ws.prism.saic.com}prismYesNoFlag" minOccurs="0"/&gt;
 *         &lt;element name="numReqrdCollections" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prismcrRMSSpecific", propOrder = {
    "minAge",
    "maxAge",
    "cFlag",
    "weatherThreshold",
    "decompImage",
    "collOverrideFlag",
    "radiometricFlag",
    "numReqrdCollections"
})
public class PrismcrRMSSpecific {

    protected Integer minAge;
    protected Integer maxAge;
    @XmlSchemaType(name = "string")
    protected PrismYesNoFlag cFlag;
    protected Integer weatherThreshold;
    @XmlSchemaType(name = "string")
    protected PrismYesNoFlag decompImage;
    @XmlSchemaType(name = "string")
    protected PrismYesNoFlag collOverrideFlag;
    @XmlSchemaType(name = "string")
    protected PrismYesNoFlag radiometricFlag;
    protected Integer numReqrdCollections;

    /**
     * Gets the value of the minAge property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMinAge() {
        return minAge;
    }

    /**
     * Sets the value of the minAge property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMinAge(Integer value) {
        this.minAge = value;
    }

    /**
     * Gets the value of the maxAge property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMaxAge() {
        return maxAge;
    }

    /**
     * Sets the value of the maxAge property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMaxAge(Integer value) {
        this.maxAge = value;
    }

    /**
     * Gets the value of the cFlag property.
     * 
     * @return
     *     possible object is
     *     {@link PrismYesNoFlag }
     *     
     */
    public PrismYesNoFlag getCFlag() {
        return cFlag;
    }

    /**
     * Sets the value of the cFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismYesNoFlag }
     *     
     */
    public void setCFlag(PrismYesNoFlag value) {
        this.cFlag = value;
    }

    /**
     * Gets the value of the weatherThreshold property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getWeatherThreshold() {
        return weatherThreshold;
    }

    /**
     * Sets the value of the weatherThreshold property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setWeatherThreshold(Integer value) {
        this.weatherThreshold = value;
    }

    /**
     * Gets the value of the decompImage property.
     * 
     * @return
     *     possible object is
     *     {@link PrismYesNoFlag }
     *     
     */
    public PrismYesNoFlag getDecompImage() {
        return decompImage;
    }

    /**
     * Sets the value of the decompImage property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismYesNoFlag }
     *     
     */
    public void setDecompImage(PrismYesNoFlag value) {
        this.decompImage = value;
    }

    /**
     * Gets the value of the collOverrideFlag property.
     * 
     * @return
     *     possible object is
     *     {@link PrismYesNoFlag }
     *     
     */
    public PrismYesNoFlag getCollOverrideFlag() {
        return collOverrideFlag;
    }

    /**
     * Sets the value of the collOverrideFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismYesNoFlag }
     *     
     */
    public void setCollOverrideFlag(PrismYesNoFlag value) {
        this.collOverrideFlag = value;
    }

    /**
     * Gets the value of the radiometricFlag property.
     * 
     * @return
     *     possible object is
     *     {@link PrismYesNoFlag }
     *     
     */
    public PrismYesNoFlag getRadiometricFlag() {
        return radiometricFlag;
    }

    /**
     * Sets the value of the radiometricFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismYesNoFlag }
     *     
     */
    public void setRadiometricFlag(PrismYesNoFlag value) {
        this.radiometricFlag = value;
    }

    /**
     * Gets the value of the numReqrdCollections property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumReqrdCollections() {
        return numReqrdCollections;
    }

    /**
     * Sets the value of the numReqrdCollections property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumReqrdCollections(Integer value) {
        this.numReqrdCollections = value;
    }

}
