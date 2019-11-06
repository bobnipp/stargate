
package com.saic.prism.ws.researchws.prismresearchws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prismNomIMINTShort complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prismNomIMINTShort"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="respOrg" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="status" type="{http://PRISMResearchWS.researchws.ws.prism.saic.com}prismNomStatus"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prismNomIMINTShort", propOrder = {
    "respOrg",
    "status"
})
public class PrismNomIMINTShort {

    @XmlElement(required = true)
    protected String respOrg;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected PrismNomStatus status;

    /**
     * Gets the value of the respOrg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRespOrg() {
        return respOrg;
    }

    /**
     * Sets the value of the respOrg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRespOrg(String value) {
        this.respOrg = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link PrismNomStatus }
     *     
     */
    public PrismNomStatus getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismNomStatus }
     *     
     */
    public void setStatus(PrismNomStatus value) {
        this.status = value;
    }

}
