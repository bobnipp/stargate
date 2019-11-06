
package com.saic.prism.ws.coredataws.prismcoredataws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prismNomIMINT complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prismNomIMINT"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="respOrg" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="status" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismNomStatus"/&gt;
 *         &lt;element name="crList" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismcrShort" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="erList" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismerShort" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="targetList" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismTargetShort" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="ipList" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismipShort" maxOccurs="unbounded" minOccurs="0"/&gt;
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
@XmlType(name = "prismNomIMINT", propOrder = {
    "respOrg",
    "status",
    "crList",
    "erList",
    "targetList",
    "ipList",
    "dataInfo"
})
public class PrismNomIMINT {

    @XmlElement(required = true)
    protected String respOrg;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected PrismNomStatus status;
    protected List<PrismcrShort> crList;
    protected List<PrismerShort> erList;
    protected List<PrismTargetShort> targetList;
    protected List<PrismipShort> ipList;
    protected PrismDataInfo dataInfo;

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

    /**
     * Gets the value of the crList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PrismcrShort }
     * 
     * 
     */
    public List<PrismcrShort> getCrList() {
        if (crList == null) {
            crList = new ArrayList<PrismcrShort>();
        }
        return this.crList;
    }

    /**
     * Gets the value of the erList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the erList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getErList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PrismerShort }
     * 
     * 
     */
    public List<PrismerShort> getErList() {
        if (erList == null) {
            erList = new ArrayList<PrismerShort>();
        }
        return this.erList;
    }

    /**
     * Gets the value of the targetList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the targetList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTargetList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PrismTargetShort }
     * 
     * 
     */
    public List<PrismTargetShort> getTargetList() {
        if (targetList == null) {
            targetList = new ArrayList<PrismTargetShort>();
        }
        return this.targetList;
    }

    /**
     * Gets the value of the ipList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ipList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIpList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PrismipShort }
     * 
     * 
     */
    public List<PrismipShort> getIpList() {
        if (ipList == null) {
            ipList = new ArrayList<PrismipShort>();
        }
        return this.ipList;
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
