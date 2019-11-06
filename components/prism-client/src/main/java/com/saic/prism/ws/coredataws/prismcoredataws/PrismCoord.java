
package com.saic.prism.ws.coredataws.prismcoredataws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prismCoord complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prismCoord"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="sequence" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="latDouble" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="longDouble" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="elevation" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prismCoord", propOrder = {
    "sequence",
    "latDouble",
    "longDouble",
    "elevation"
})
public class PrismCoord {

    protected int sequence;
    protected double latDouble;
    protected double longDouble;
    protected Double elevation;

    /**
     * Gets the value of the sequence property.
     * 
     */
    public int getSequence() {
        return sequence;
    }

    /**
     * Sets the value of the sequence property.
     * 
     */
    public void setSequence(int value) {
        this.sequence = value;
    }

    /**
     * Gets the value of the latDouble property.
     * 
     */
    public double getLatDouble() {
        return latDouble;
    }

    /**
     * Sets the value of the latDouble property.
     * 
     */
    public void setLatDouble(double value) {
        this.latDouble = value;
    }

    /**
     * Gets the value of the longDouble property.
     * 
     */
    public double getLongDouble() {
        return longDouble;
    }

    /**
     * Sets the value of the longDouble property.
     * 
     */
    public void setLongDouble(double value) {
        this.longDouble = value;
    }

    /**
     * Gets the value of the elevation property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getElevation() {
        return elevation;
    }

    /**
     * Sets the value of the elevation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setElevation(Double value) {
        this.elevation = value;
    }

}
