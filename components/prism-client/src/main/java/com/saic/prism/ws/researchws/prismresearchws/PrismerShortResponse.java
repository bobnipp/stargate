
package com.saic.prism.ws.researchws.prismresearchws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prismerShortResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prismerShortResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://PRISMResearchWS.researchws.ws.prism.saic.com}prismResponse"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="erList" type="{http://PRISMResearchWS.researchws.ws.prism.saic.com}prismerShort" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prismerShortResponse", propOrder = {
    "erList"
})
public class PrismerShortResponse
    extends PrismResponse
{

    @XmlElement(required = true)
    protected List<PrismerShort> erList;

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

}
