
package com.saic.prism.ws.exnomws.prismexnomws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prismNomIMINTSubmit complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prismNomIMINTSubmit"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="respOrg" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="crList" type="{http://PRISMExNomWS.exnomws.ws.prism.saic.com}prismcrSubmit" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="erList" type="{http://PRISMExNomWS.exnomws.ws.prism.saic.com}prismerSubmit" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="targetList" type="{http://PRISMExNomWS.exnomws.ws.prism.saic.com}prismTargetSubmit" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prismNomIMINTSubmit", propOrder = {
    "respOrg",
    "crList",
    "erList",
    "targetList"
})
public class PrismNomIMINTSubmit {

    @XmlElement(required = true)
    protected String respOrg;
    protected List<PrismcrSubmit> crList;
    protected List<PrismerSubmit> erList;
    protected List<PrismTargetSubmit> targetList;

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
     * {@link PrismcrSubmit }
     * 
     * 
     */
    public List<PrismcrSubmit> getCrList() {
        if (crList == null) {
            crList = new ArrayList<PrismcrSubmit>();
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
     * {@link PrismerSubmit }
     * 
     * 
     */
    public List<PrismerSubmit> getErList() {
        if (erList == null) {
            erList = new ArrayList<PrismerSubmit>();
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
     * {@link PrismTargetSubmit }
     * 
     * 
     */
    public List<PrismTargetSubmit> getTargetList() {
        if (targetList == null) {
            targetList = new ArrayList<PrismTargetSubmit>();
        }
        return this.targetList;
    }

}
