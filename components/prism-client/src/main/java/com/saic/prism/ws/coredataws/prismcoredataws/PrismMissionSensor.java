
package com.saic.prism.ws.coredataws.prismcoredataws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prismMissionSensor complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prismMissionSensor"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="sensor" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismSensor"/&gt;
 *         &lt;element name="ped" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="maxSlots" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="adHoc" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="taskingAuthorityList" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismTaskingAuthority" maxOccurs="unbounded"/&gt;
 *         &lt;element name="minRange" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="maxRange" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="producerList" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismProducer" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="dgsList" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismdgs" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="dataInfo" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismDataInfo" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prismMissionSensor", propOrder = {
    "key",
    "sensor",
    "ped",
    "maxSlots",
    "adHoc",
    "taskingAuthorityList",
    "minRange",
    "maxRange",
    "producerList",
    "dgsList",
    "dataInfo"
})
public class PrismMissionSensor {

    @XmlElement(required = true)
    protected String key;
    @XmlElement(required = true)
    protected PrismSensor sensor;
    protected int ped;
    protected int maxSlots;
    protected int adHoc;
    @XmlElement(required = true)
    protected List<PrismTaskingAuthority> taskingAuthorityList;
    protected Double minRange;
    protected Double maxRange;
    protected List<PrismProducer> producerList;
    protected List<Prismdgs> dgsList;
    protected PrismDataInfo dataInfo;

    /**
     * Gets the value of the key property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the value of the key property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKey(String value) {
        this.key = value;
    }

    /**
     * Gets the value of the sensor property.
     * 
     * @return
     *     possible object is
     *     {@link PrismSensor }
     *     
     */
    public PrismSensor getSensor() {
        return sensor;
    }

    /**
     * Sets the value of the sensor property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismSensor }
     *     
     */
    public void setSensor(PrismSensor value) {
        this.sensor = value;
    }

    /**
     * Gets the value of the ped property.
     * 
     */
    public int getPed() {
        return ped;
    }

    /**
     * Sets the value of the ped property.
     * 
     */
    public void setPed(int value) {
        this.ped = value;
    }

    /**
     * Gets the value of the maxSlots property.
     * 
     */
    public int getMaxSlots() {
        return maxSlots;
    }

    /**
     * Sets the value of the maxSlots property.
     * 
     */
    public void setMaxSlots(int value) {
        this.maxSlots = value;
    }

    /**
     * Gets the value of the adHoc property.
     * 
     */
    public int getAdHoc() {
        return adHoc;
    }

    /**
     * Sets the value of the adHoc property.
     * 
     */
    public void setAdHoc(int value) {
        this.adHoc = value;
    }

    /**
     * Gets the value of the taskingAuthorityList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the taskingAuthorityList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTaskingAuthorityList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PrismTaskingAuthority }
     * 
     * 
     */
    public List<PrismTaskingAuthority> getTaskingAuthorityList() {
        if (taskingAuthorityList == null) {
            taskingAuthorityList = new ArrayList<PrismTaskingAuthority>();
        }
        return this.taskingAuthorityList;
    }

    /**
     * Gets the value of the minRange property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMinRange() {
        return minRange;
    }

    /**
     * Sets the value of the minRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMinRange(Double value) {
        this.minRange = value;
    }

    /**
     * Gets the value of the maxRange property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMaxRange() {
        return maxRange;
    }

    /**
     * Sets the value of the maxRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMaxRange(Double value) {
        this.maxRange = value;
    }

    /**
     * Gets the value of the producerList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the producerList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProducerList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PrismProducer }
     * 
     * 
     */
    public List<PrismProducer> getProducerList() {
        if (producerList == null) {
            producerList = new ArrayList<PrismProducer>();
        }
        return this.producerList;
    }

    /**
     * Gets the value of the dgsList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dgsList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDgsList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Prismdgs }
     * 
     * 
     */
    public List<Prismdgs> getDgsList() {
        if (dgsList == null) {
            dgsList = new ArrayList<Prismdgs>();
        }
        return this.dgsList;
    }

    /**
     * Gets the value of the dataInfo property.
     * 
     * @return
     *     possible object is
     *     {@link PrismDataInfo }
     *     
     */
    public PrismDataInfo getDataInfo() {
        return dataInfo;
    }

    /**
     * Sets the value of the dataInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismDataInfo }
     *     
     */
    public void setDataInfo(PrismDataInfo value) {
        this.dataInfo = value;
    }

}
