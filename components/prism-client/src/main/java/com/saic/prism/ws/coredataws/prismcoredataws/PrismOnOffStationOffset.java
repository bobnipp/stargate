
package com.saic.prism.ws.coredataws.prismcoredataws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prismOnOffStationOffset complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prismOnOffStationOffset"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="onStationOffset" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="offStationOffset" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prismOnOffStationOffset", propOrder = {
    "onStationOffset",
    "offStationOffset"
})
public class PrismOnOffStationOffset {

    protected int onStationOffset;
    protected int offStationOffset;

    /**
     * Gets the value of the onStationOffset property.
     * 
     */
    public int getOnStationOffset() {
        return onStationOffset;
    }

    /**
     * Sets the value of the onStationOffset property.
     * 
     */
    public void setOnStationOffset(int value) {
        this.onStationOffset = value;
    }

    /**
     * Gets the value of the offStationOffset property.
     * 
     */
    public int getOffStationOffset() {
        return offStationOffset;
    }

    /**
     * Sets the value of the offStationOffset property.
     * 
     */
    public void setOffStationOffset(int value) {
        this.offStationOffset = value;
    }

}
