
package com.saic.prism.ws.coredataws.prismcoredataws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prismrwac complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prismrwac"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="suffix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="idhs" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismidhs" minOccurs="0"/&gt;
 *         &lt;element name="lowerLeftGeo" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismCoord"/&gt;
 *         &lt;element name="upperRightGeo" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismCoord"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prismrwac", propOrder = {
    "suffix",
    "idhs",
    "lowerLeftGeo",
    "upperRightGeo"
})
public class Prismrwac {

    protected String suffix;
    protected Prismidhs idhs;
    @XmlElement(required = true)
    protected PrismCoord lowerLeftGeo;
    @XmlElement(required = true)
    protected PrismCoord upperRightGeo;

    /**
     * Gets the value of the suffix property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuffix() {
        return suffix;
    }

    /**
     * Sets the value of the suffix property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuffix(String value) {
        this.suffix = value;
    }

    /**
     * Gets the value of the idhs property.
     * 
     * @return
     *     possible object is
     *     {@link Prismidhs }
     *     
     */
    public Prismidhs getIdhs() {
        return idhs;
    }

    /**
     * Sets the value of the idhs property.
     * 
     * @param value
     *     allowed object is
     *     {@link Prismidhs }
     *     
     */
    public void setIdhs(Prismidhs value) {
        this.idhs = value;
    }

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
