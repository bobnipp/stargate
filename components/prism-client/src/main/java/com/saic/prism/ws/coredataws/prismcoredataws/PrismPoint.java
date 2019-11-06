
package com.saic.prism.ws.coredataws.prismcoredataws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for prismPoint complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prismPoint"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="suffix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="benum" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="kai" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="primaryFlag" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="idhs" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismidhs" minOccurs="0"/&gt;
 *         &lt;element name="cmxCat" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismcmxcat" minOccurs="0"/&gt;
 *         &lt;element name="geoCoord" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismCoord"/&gt;
 *         &lt;element name="minAxis" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="majAxis" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="minMajAngle" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="tPointStart" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="tPointStop" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="tPointGeo" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismCoord" minOccurs="0"/&gt;
 *         &lt;element name="tPointMinAxis" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="tPointMajAxis" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="tPointMinMajAngle" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="mrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="mtf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="iPointElevation" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="iPointMinAxis" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="iPointMajAxis" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="iPointMinMajAngle" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prismPoint", propOrder = {
    "suffix",
    "benum",
    "kai",
    "primaryFlag",
    "idhs",
    "cmxCat",
    "geoCoord",
    "minAxis",
    "majAxis",
    "minMajAngle",
    "tPointStart",
    "tPointStop",
    "tPointGeo",
    "tPointMinAxis",
    "tPointMajAxis",
    "tPointMinMajAngle",
    "mrl",
    "mtf",
    "iPointElevation",
    "iPointMinAxis",
    "iPointMajAxis",
    "iPointMinMajAngle"
})
public class PrismPoint {

    protected String suffix;
    @XmlElement(required = true)
    protected String benum;
    protected String kai;
    @XmlElement(required = true)
    protected String primaryFlag;
    protected Prismidhs idhs;
    protected Prismcmxcat cmxCat;
    @XmlElement(required = true)
    protected PrismCoord geoCoord;
    protected double minAxis;
    protected double majAxis;
    protected double minMajAngle;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar tPointStart;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar tPointStop;
    protected PrismCoord tPointGeo;
    protected Double tPointMinAxis;
    protected Double tPointMajAxis;
    protected Double tPointMinMajAngle;
    protected String mrl;
    protected String mtf;
    protected Double iPointElevation;
    protected Double iPointMinAxis;
    protected Double iPointMajAxis;
    protected Double iPointMinMajAngle;

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
     * Gets the value of the benum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBenum() {
        return benum;
    }

    /**
     * Sets the value of the benum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBenum(String value) {
        this.benum = value;
    }

    /**
     * Gets the value of the kai property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKai() {
        return kai;
    }

    /**
     * Sets the value of the kai property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKai(String value) {
        this.kai = value;
    }

    /**
     * Gets the value of the primaryFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimaryFlag() {
        return primaryFlag;
    }

    /**
     * Sets the value of the primaryFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimaryFlag(String value) {
        this.primaryFlag = value;
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
     * Gets the value of the cmxCat property.
     * 
     * @return
     *     possible object is
     *     {@link Prismcmxcat }
     *     
     */
    public Prismcmxcat getCmxCat() {
        return cmxCat;
    }

    /**
     * Sets the value of the cmxCat property.
     * 
     * @param value
     *     allowed object is
     *     {@link Prismcmxcat }
     *     
     */
    public void setCmxCat(Prismcmxcat value) {
        this.cmxCat = value;
    }

    /**
     * Gets the value of the geoCoord property.
     * 
     * @return
     *     possible object is
     *     {@link PrismCoord }
     *     
     */
    public PrismCoord getGeoCoord() {
        return geoCoord;
    }

    /**
     * Sets the value of the geoCoord property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismCoord }
     *     
     */
    public void setGeoCoord(PrismCoord value) {
        this.geoCoord = value;
    }

    /**
     * Gets the value of the minAxis property.
     * 
     */
    public double getMinAxis() {
        return minAxis;
    }

    /**
     * Sets the value of the minAxis property.
     * 
     */
    public void setMinAxis(double value) {
        this.minAxis = value;
    }

    /**
     * Gets the value of the majAxis property.
     * 
     */
    public double getMajAxis() {
        return majAxis;
    }

    /**
     * Sets the value of the majAxis property.
     * 
     */
    public void setMajAxis(double value) {
        this.majAxis = value;
    }

    /**
     * Gets the value of the minMajAngle property.
     * 
     */
    public double getMinMajAngle() {
        return minMajAngle;
    }

    /**
     * Sets the value of the minMajAngle property.
     * 
     */
    public void setMinMajAngle(double value) {
        this.minMajAngle = value;
    }

    /**
     * Gets the value of the tPointStart property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTPointStart() {
        return tPointStart;
    }

    /**
     * Sets the value of the tPointStart property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTPointStart(XMLGregorianCalendar value) {
        this.tPointStart = value;
    }

    /**
     * Gets the value of the tPointStop property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTPointStop() {
        return tPointStop;
    }

    /**
     * Sets the value of the tPointStop property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTPointStop(XMLGregorianCalendar value) {
        this.tPointStop = value;
    }

    /**
     * Gets the value of the tPointGeo property.
     * 
     * @return
     *     possible object is
     *     {@link PrismCoord }
     *     
     */
    public PrismCoord getTPointGeo() {
        return tPointGeo;
    }

    /**
     * Sets the value of the tPointGeo property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismCoord }
     *     
     */
    public void setTPointGeo(PrismCoord value) {
        this.tPointGeo = value;
    }

    /**
     * Gets the value of the tPointMinAxis property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTPointMinAxis() {
        return tPointMinAxis;
    }

    /**
     * Sets the value of the tPointMinAxis property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTPointMinAxis(Double value) {
        this.tPointMinAxis = value;
    }

    /**
     * Gets the value of the tPointMajAxis property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTPointMajAxis() {
        return tPointMajAxis;
    }

    /**
     * Sets the value of the tPointMajAxis property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTPointMajAxis(Double value) {
        this.tPointMajAxis = value;
    }

    /**
     * Gets the value of the tPointMinMajAngle property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTPointMinMajAngle() {
        return tPointMinMajAngle;
    }

    /**
     * Sets the value of the tPointMinMajAngle property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTPointMinMajAngle(Double value) {
        this.tPointMinMajAngle = value;
    }

    /**
     * Gets the value of the mrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMrl() {
        return mrl;
    }

    /**
     * Sets the value of the mrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMrl(String value) {
        this.mrl = value;
    }

    /**
     * Gets the value of the mtf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMtf() {
        return mtf;
    }

    /**
     * Sets the value of the mtf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMtf(String value) {
        this.mtf = value;
    }

    /**
     * Gets the value of the iPointElevation property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getIPointElevation() {
        return iPointElevation;
    }

    /**
     * Sets the value of the iPointElevation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setIPointElevation(Double value) {
        this.iPointElevation = value;
    }

    /**
     * Gets the value of the iPointMinAxis property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getIPointMinAxis() {
        return iPointMinAxis;
    }

    /**
     * Sets the value of the iPointMinAxis property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setIPointMinAxis(Double value) {
        this.iPointMinAxis = value;
    }

    /**
     * Gets the value of the iPointMajAxis property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getIPointMajAxis() {
        return iPointMajAxis;
    }

    /**
     * Sets the value of the iPointMajAxis property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setIPointMajAxis(Double value) {
        this.iPointMajAxis = value;
    }

    /**
     * Gets the value of the iPointMinMajAngle property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getIPointMinMajAngle() {
        return iPointMinMajAngle;
    }

    /**
     * Sets the value of the iPointMinMajAngle property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setIPointMinMajAngle(Double value) {
        this.iPointMinMajAngle = value;
    }

}
