
package com.saic.prism.ws.researchws.prismresearchws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prismMissionShortResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prismMissionShortResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://PRISMResearchWS.researchws.ws.prism.saic.com}prismResponse"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="missionList" type="{http://PRISMResearchWS.researchws.ws.prism.saic.com}prismMissionShort" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prismMissionShortResponse", propOrder = {
    "missionList"
})
public class PrismMissionShortResponse
    extends PrismResponse
{

    @XmlElement(required = true)
    protected List<PrismMissionShort> missionList;

    /**
     * Gets the value of the missionList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the missionList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMissionList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PrismMissionShort }
     * 
     * 
     */
    public List<PrismMissionShort> getMissionList() {
        if (missionList == null) {
            missionList = new ArrayList<PrismMissionShort>();
        }
        return this.missionList;
    }

}
