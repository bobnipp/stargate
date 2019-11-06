
package com.saic.prism.ws.exnomws.prismexnomws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for prismaodLink complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prismaodLink"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="jaopGUID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="effectGUID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="aodDate" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="rank" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="subRank" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prismaodLink", propOrder = {
    "jaopGUID",
    "effectGUID",
    "aodDate",
    "rank",
    "subRank"
})
public class PrismaodLink {

    @XmlElement(required = true)
    protected String jaopGUID;
    @XmlElement(required = true)
    protected String effectGUID;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar aodDate;
    protected int rank;
    protected int subRank;

    /**
     * Gets the value of the jaopGUID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJaopGUID() {
        return jaopGUID;
    }

    /**
     * Sets the value of the jaopGUID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJaopGUID(String value) {
        this.jaopGUID = value;
    }

    /**
     * Gets the value of the effectGUID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEffectGUID() {
        return effectGUID;
    }

    /**
     * Sets the value of the effectGUID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEffectGUID(String value) {
        this.effectGUID = value;
    }

    /**
     * Gets the value of the aodDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAodDate() {
        return aodDate;
    }

    /**
     * Sets the value of the aodDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAodDate(XMLGregorianCalendar value) {
        this.aodDate = value;
    }

    /**
     * Gets the value of the rank property.
     * 
     */
    public int getRank() {
        return rank;
    }

    /**
     * Sets the value of the rank property.
     * 
     */
    public void setRank(int value) {
        this.rank = value;
    }

    /**
     * Gets the value of the subRank property.
     * 
     */
    public int getSubRank() {
        return subRank;
    }

    /**
     * Sets the value of the subRank property.
     * 
     */
    public void setSubRank(int value) {
        this.subRank = value;
    }

}
