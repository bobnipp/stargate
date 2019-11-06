
package com.saic.prism.ws.coredataws.prismcoredataws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prismcrTarget complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prismcrTarget"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="crxtgtKey" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="target" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismTargetShort"/&gt;
 *         &lt;element name="lookAtCoord" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismCoord" minOccurs="0"/&gt;
 *         &lt;element name="lookAtMinAxis" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="lookAtMajAxis" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="lookAtMinMajAngle" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prismcrTarget", propOrder = {
    "crxtgtKey",
    "target",
    "lookAtCoord",
    "lookAtMinAxis",
    "lookAtMajAxis",
    "lookAtMinMajAngle"
})
public class PrismcrTarget {

    @XmlElement(required = true)
    protected String crxtgtKey;
    @XmlElement(required = true)
    protected PrismTargetShort target;
    protected PrismCoord lookAtCoord;
    protected Double lookAtMinAxis;
    protected Double lookAtMajAxis;
    protected Double lookAtMinMajAngle;

    /**
     * Gets the value of the crxtgtKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrxtgtKey() {
        return crxtgtKey;
    }

    /**
     * Sets the value of the crxtgtKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrxtgtKey(String value) {
        this.crxtgtKey = value;
    }

    /**
     * Gets the value of the target property.
     * 
     * @return
     *     possible object is
     *     {@link PrismTargetShort }
     *     
     */
    public PrismTargetShort getTarget() {
        return target;
    }

    /**
     * Sets the value of the target property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismTargetShort }
     *     
     */
    public void setTarget(PrismTargetShort value) {
        this.target = value;
    }

    /**
     * Gets the value of the lookAtCoord property.
     * 
     * @return
     *     possible object is
     *     {@link PrismCoord }
     *     
     */
    public PrismCoord getLookAtCoord() {
        return lookAtCoord;
    }

    /**
     * Sets the value of the lookAtCoord property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismCoord }
     *     
     */
    public void setLookAtCoord(PrismCoord value) {
        this.lookAtCoord = value;
    }

    /**
     * Gets the value of the lookAtMinAxis property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getLookAtMinAxis() {
        return lookAtMinAxis;
    }

    /**
     * Sets the value of the lookAtMinAxis property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setLookAtMinAxis(Double value) {
        this.lookAtMinAxis = value;
    }

    /**
     * Gets the value of the lookAtMajAxis property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getLookAtMajAxis() {
        return lookAtMajAxis;
    }

    /**
     * Sets the value of the lookAtMajAxis property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setLookAtMajAxis(Double value) {
        this.lookAtMajAxis = value;
    }

    /**
     * Gets the value of the lookAtMinMajAngle property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getLookAtMinMajAngle() {
        return lookAtMinMajAngle;
    }

    /**
     * Sets the value of the lookAtMinMajAngle property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setLookAtMinMajAngle(Double value) {
        this.lookAtMinMajAngle = value;
    }

}
