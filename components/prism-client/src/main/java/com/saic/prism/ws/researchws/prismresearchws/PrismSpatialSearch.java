
package com.saic.prism.ws.researchws.prismresearchws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prismSpatialSearch complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prismSpatialSearch"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="spatialType" type="{http://PRISMResearchWS.researchws.ws.prism.saic.com}prismSpatialSearchType"/&gt;
 *         &lt;element name="circle" type="{http://PRISMResearchWS.researchws.ws.prism.saic.com}prismSpatialCircle" minOccurs="0"/&gt;
 *         &lt;element name="polygon" type="{http://PRISMResearchWS.researchws.ws.prism.saic.com}prismSpatialPolygon" minOccurs="0"/&gt;
 *         &lt;element name="rectangle" type="{http://PRISMResearchWS.researchws.ws.prism.saic.com}prismSpatialRectangle" minOccurs="0"/&gt;
 *         &lt;element name="opAreaKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="aoiKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prismSpatialSearch", propOrder = {
    "spatialType",
    "circle",
    "polygon",
    "rectangle",
    "opAreaKey",
    "aoiKey"
})
public class PrismSpatialSearch {

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected PrismSpatialSearchType spatialType;
    protected PrismSpatialCircle circle;
    protected PrismSpatialPolygon polygon;
    protected PrismSpatialRectangle rectangle;
    protected String opAreaKey;
    protected String aoiKey;

    /**
     * Gets the value of the spatialType property.
     * 
     * @return
     *     possible object is
     *     {@link PrismSpatialSearchType }
     *     
     */
    public PrismSpatialSearchType getSpatialType() {
        return spatialType;
    }

    /**
     * Sets the value of the spatialType property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismSpatialSearchType }
     *     
     */
    public void setSpatialType(PrismSpatialSearchType value) {
        this.spatialType = value;
    }

    /**
     * Gets the value of the circle property.
     * 
     * @return
     *     possible object is
     *     {@link PrismSpatialCircle }
     *     
     */
    public PrismSpatialCircle getCircle() {
        return circle;
    }

    /**
     * Sets the value of the circle property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismSpatialCircle }
     *     
     */
    public void setCircle(PrismSpatialCircle value) {
        this.circle = value;
    }

    /**
     * Gets the value of the polygon property.
     * 
     * @return
     *     possible object is
     *     {@link PrismSpatialPolygon }
     *     
     */
    public PrismSpatialPolygon getPolygon() {
        return polygon;
    }

    /**
     * Sets the value of the polygon property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismSpatialPolygon }
     *     
     */
    public void setPolygon(PrismSpatialPolygon value) {
        this.polygon = value;
    }

    /**
     * Gets the value of the rectangle property.
     * 
     * @return
     *     possible object is
     *     {@link PrismSpatialRectangle }
     *     
     */
    public PrismSpatialRectangle getRectangle() {
        return rectangle;
    }

    /**
     * Sets the value of the rectangle property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismSpatialRectangle }
     *     
     */
    public void setRectangle(PrismSpatialRectangle value) {
        this.rectangle = value;
    }

    /**
     * Gets the value of the opAreaKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpAreaKey() {
        return opAreaKey;
    }

    /**
     * Sets the value of the opAreaKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpAreaKey(String value) {
        this.opAreaKey = value;
    }

    /**
     * Gets the value of the aoiKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAoiKey() {
        return aoiKey;
    }

    /**
     * Sets the value of the aoiKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAoiKey(String value) {
        this.aoiKey = value;
    }

}
