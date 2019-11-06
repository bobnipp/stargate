
package com.saic.prism.ws.researchws.prismresearchws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prismSpatialCircle complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prismSpatialCircle"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="centerGeo" type="{http://PRISMResearchWS.researchws.ws.prism.saic.com}prismCoord"/&gt;
 *         &lt;element name="radius" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prismSpatialCircle", propOrder = {
    "centerGeo",
    "radius"
})
public class PrismSpatialCircle {

    @XmlElement(required = true)
    protected PrismCoord centerGeo;
    protected double radius;

    /**
     * Gets the value of the centerGeo property.
     * 
     * @return
     *     possible object is
     *     {@link PrismCoord }
     *     
     */
    public PrismCoord getCenterGeo() {
        return centerGeo;
    }

    /**
     * Sets the value of the centerGeo property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismCoord }
     *     
     */
    public void setCenterGeo(PrismCoord value) {
        this.centerGeo = value;
    }

    /**
     * Gets the value of the radius property.
     * 
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Sets the value of the radius property.
     * 
     */
    public void setRadius(double value) {
        this.radius = value;
    }

}
