
package com.saic.prism.ws.exnomws.prismexnomws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prismcrSensorSubmit complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prismcrSensorSubmit"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="desiredNiirs" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="predNiirs" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="startAzimuth" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="stopAzimuth" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="startElevation" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="stopElevation" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="minSunAzimuth" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="maxSunAzimuth" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="sensorKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sensorTypeKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sensorModeKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prismcrSensorSubmit", propOrder = {
    "desiredNiirs",
    "predNiirs",
    "startAzimuth",
    "stopAzimuth",
    "startElevation",
    "stopElevation",
    "minSunAzimuth",
    "maxSunAzimuth",
    "sensorKey",
    "sensorTypeKey",
    "sensorModeKey"
})
public class PrismcrSensorSubmit {

    protected Double desiredNiirs;
    protected double predNiirs;
    protected Double startAzimuth;
    protected Double stopAzimuth;
    protected Double startElevation;
    protected Double stopElevation;
    protected Double minSunAzimuth;
    protected Double maxSunAzimuth;
    protected String sensorKey;
    protected String sensorTypeKey;
    protected String sensorModeKey;

    /**
     * Gets the value of the desiredNiirs property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDesiredNiirs() {
        return desiredNiirs;
    }

    /**
     * Sets the value of the desiredNiirs property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDesiredNiirs(Double value) {
        this.desiredNiirs = value;
    }

    /**
     * Gets the value of the predNiirs property.
     * 
     */
    public double getPredNiirs() {
        return predNiirs;
    }

    /**
     * Sets the value of the predNiirs property.
     * 
     */
    public void setPredNiirs(double value) {
        this.predNiirs = value;
    }

    /**
     * Gets the value of the startAzimuth property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getStartAzimuth() {
        return startAzimuth;
    }

    /**
     * Sets the value of the startAzimuth property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setStartAzimuth(Double value) {
        this.startAzimuth = value;
    }

    /**
     * Gets the value of the stopAzimuth property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getStopAzimuth() {
        return stopAzimuth;
    }

    /**
     * Sets the value of the stopAzimuth property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setStopAzimuth(Double value) {
        this.stopAzimuth = value;
    }

    /**
     * Gets the value of the startElevation property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getStartElevation() {
        return startElevation;
    }

    /**
     * Sets the value of the startElevation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setStartElevation(Double value) {
        this.startElevation = value;
    }

    /**
     * Gets the value of the stopElevation property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getStopElevation() {
        return stopElevation;
    }

    /**
     * Sets the value of the stopElevation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setStopElevation(Double value) {
        this.stopElevation = value;
    }

    /**
     * Gets the value of the minSunAzimuth property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMinSunAzimuth() {
        return minSunAzimuth;
    }

    /**
     * Sets the value of the minSunAzimuth property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMinSunAzimuth(Double value) {
        this.minSunAzimuth = value;
    }

    /**
     * Gets the value of the maxSunAzimuth property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMaxSunAzimuth() {
        return maxSunAzimuth;
    }

    /**
     * Sets the value of the maxSunAzimuth property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMaxSunAzimuth(Double value) {
        this.maxSunAzimuth = value;
    }

    /**
     * Gets the value of the sensorKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSensorKey() {
        return sensorKey;
    }

    /**
     * Sets the value of the sensorKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSensorKey(String value) {
        this.sensorKey = value;
    }

    /**
     * Gets the value of the sensorTypeKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSensorTypeKey() {
        return sensorTypeKey;
    }

    /**
     * Sets the value of the sensorTypeKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSensorTypeKey(String value) {
        this.sensorTypeKey = value;
    }

    /**
     * Gets the value of the sensorModeKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSensorModeKey() {
        return sensorModeKey;
    }

    /**
     * Sets the value of the sensorModeKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSensorModeKey(String value) {
        this.sensorModeKey = value;
    }

}
