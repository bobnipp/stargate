
package com.saic.prism.ws.coredataws.prismcoredataws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prismTargetResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prismTargetResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismResponse"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="prismTargetList" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismTarget" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prismTargetResponse", propOrder = {
    "prismTargetList"
})
public class PrismTargetResponse
    extends PrismResponse
{

    @XmlElement(required = true)
    protected List<PrismTarget> prismTargetList;

    /**
     * Gets the value of the prismTargetList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the prismTargetList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPrismTargetList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PrismTarget }
     * 
     * 
     */
    public List<PrismTarget> getPrismTargetList() {
        if (prismTargetList == null) {
            prismTargetList = new ArrayList<PrismTarget>();
        }
        return this.prismTargetList;
    }

}
