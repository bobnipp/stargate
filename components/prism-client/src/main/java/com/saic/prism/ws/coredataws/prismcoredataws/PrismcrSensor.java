
package com.saic.prism.ws.coredataws.prismcoredataws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prismcrSensor complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prismcrSensor"&gt;
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
 *         &lt;element name="sensor" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismSensor" minOccurs="0"/&gt;
 *         &lt;element name="sensorType" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismSensorType" minOccurs="0"/&gt;
 *         &lt;element name="sensorMode" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismSensorMode" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prismcrSensor", propOrder = {
    "desiredNiirs",
    "predNiirs",
    "startAzimuth",
    "stopAzimuth",
    "startElevation",
    "stopElevation",
    "minSunAzimuth",
    "maxSunAzimuth",
    "sensor",
    "sensorType",
    "sensorMode"
})
public class PrismcrSensor {

    protected Double desiredNiirs;
    protected double predNiirs;
    protected Double startAzimuth;
    protected Double stopAzimuth;
    protected Double startElevation;
    protected Double stopElevation;
    protected Double minSunAzimuth;
    protected Double maxSunAzimuth;
    protected PrismSensor sensor;
    protected PrismSensorType sensorType;
    protected PrismSensorMode sensorMode;

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
     * Gets the value of the sensor property.
     * 
     * @return
     *     possible object is
     *     {@link PrismSensor }
     *     
     */
    public PrismSensor getSensor() {
        return sensor;
    }

    /**
     * Sets the value of the sensor property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismSensor }
     *     
     */
    public void setSensor(PrismSensor value) {
        this.sensor = value;
    }

    /**
     * Gets the value of the sensorType property.
     * 
     * @return
     *     possible object is
     *     {@link PrismSensorType }
     *     
     */
    public PrismSensorType getSensorType() {
        return sensorType;
    }

    /**
     * Sets the value of the sensorType property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismSensorType }
     *     
     */
    public void setSensorType(PrismSensorType value) {
        this.sensorType = value;
    }

    /**
     * Gets the value of the sensorMode property.
     * 
     * @return
     *     possible object is
     *     {@link PrismSensorMode }
     *     
     */
    public PrismSensorMode getSensorMode() {
        return sensorMode;
    }

    /**
     * Sets the value of the sensorMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismSensorMode }
     *     
     */
    public void setSensorMode(PrismSensorMode value) {
        this.sensorMode = value;
    }

}
