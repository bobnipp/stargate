
package com.saic.prism.ws.researchws.prismresearchws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prismSpatialRectangle complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prismSpatialRectangle"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="lowerLeftGeo" type="{http://PRISMResearchWS.researchws.ws.prism.saic.com}prismCoord"/&gt;
 *         &lt;element name="upperRightGeo" type="{http://PRISMResearchWS.researchws.ws.prism.saic.com}prismCoord"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prismSpatialRectangle", propOrder = {
    "lowerLeftGeo",
    "upperRightGeo"
})
public class PrismSpatialRectangle {

    @XmlElement(required = true)
    protected PrismCoord lowerLeftGeo;
    @XmlElement(required = true)
    protected PrismCoord upperRightGeo;

    /**
     * Gets the value of the lowerLeftGeo property.
     * 
     * @return
     *     possible object is
     *     {@link PrismCoord }
     *     
     */
    public PrismCoord getLowerLeftGeo() {
        return lowerLeftGeo;
    }

    /**
     * Sets the value of the lowerLeftGeo property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismCoord }
     *     
     */
    public void setLowerLeftGeo(PrismCoord value) {
        this.lowerLeftGeo = value;
    }

    /**
     * Gets the value of the upperRightGeo property.
     * 
     * @return
     *     possible object is
     *     {@link PrismCoord }
     *     
     */
    public PrismCoord getUpperRightGeo() {
        return upperRightGeo;
    }

    /**
     * Sets the value of the upperRightGeo property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismCoord }
     *     
     */
    public void setUpperRightGeo(PrismCoord value) {
        this.upperRightGeo = value;
    }

}
