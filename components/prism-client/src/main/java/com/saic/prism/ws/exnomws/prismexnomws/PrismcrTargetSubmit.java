
package com.saic.prism.ws.exnomws.prismexnomws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prismcrTargetSubmit complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prismcrTargetSubmit"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="targetKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="crXtargetKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="targetId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tgtType" type="{http://PRISMExNomWS.exnomws.ws.prism.saic.com}prismTargetType"/&gt;
 *         &lt;element name="lookAtCoord" type="{http://PRISMExNomWS.exnomws.ws.prism.saic.com}prismCoord" minOccurs="0"/&gt;
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
@XmlType(name = "prismcrTargetSubmit", propOrder = {
    "targetKey",
    "crXtargetKey",
    "targetId",
    "tgtType",
    "lookAtCoord",
    "lookAtMinAxis",
    "lookAtMajAxis",
    "lookAtMinMajAngle"
})
public class PrismcrTargetSubmit {

    protected String targetKey;
    protected String crXtargetKey;
    protected String targetId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected PrismTargetType tgtType;
    protected PrismCoord lookAtCoord;
    protected Double lookAtMinAxis;
    protected Double lookAtMajAxis;
    protected Double lookAtMinMajAngle;

    /**
     * Gets the value of the targetKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetKey() {
        return targetKey;
    }

    /**
     * Sets the value of the targetKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetKey(String value) {
        this.targetKey = value;
    }

    /**
     * Gets the value of the crXtargetKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrXtargetKey() {
        return crXtargetKey;
    }

    /**
     * Sets the value of the crXtargetKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrXtargetKey(String value) {
        this.crXtargetKey = value;
    }

    /**
     * Gets the value of the targetId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetId() {
        return targetId;
    }

    /**
     * Sets the value of the targetId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetId(String value) {
        this.targetId = value;
    }

    /**
     * Gets the value of the tgtType property.
     * 
     * @return
     *     possible object is
     *     {@link PrismTargetType }
     *     
     */
    public PrismTargetType getTgtType() {
        return tgtType;
    }

    /**
     * Sets the value of the tgtType property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismTargetType }
     *     
     */
    public void setTgtType(PrismTargetType value) {
        this.tgtType = value;
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
