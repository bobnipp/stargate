
package com.saic.prism.ws.researchws.prismresearchws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prismcrShortResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prismcrShortResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://PRISMResearchWS.researchws.ws.prism.saic.com}prismResponse"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="crList" type="{http://PRISMResearchWS.researchws.ws.prism.saic.com}prismcrShort" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prismcrShortResponse", propOrder = {
    "crList"
})
public class PrismcrShortResponse
    extends PrismResponse
{

    @XmlElement(required = true)
    protected List<PrismcrShort> crList;

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

}
