
package com.saic.prism.ws.coredataws.prismcoredataws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prismMissionResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prismMissionResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismResponse"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="prismMissionList" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismMission" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prismMissionResponse", propOrder = {
    "prismMissionList"
})
public class PrismMissionResponse
    extends PrismResponse
{

    @XmlElement(required = true)
    protected List<PrismMission> prismMissionList;

    /**
     * Gets the value of the prismMissionList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the prismMissionList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPrismMissionList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PrismMission }
     * 
     * 
     */
    public List<PrismMission> getPrismMissionList() {
        if (prismMissionList == null) {
            prismMissionList = new ArrayList<PrismMission>();
        }
        return this.prismMissionList;
    }

}
