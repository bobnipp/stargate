
package com.saic.prism.ws.coredataws.prismcoredataws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prismcrResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prismcrResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismResponse"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="prismCRList" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismcr" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prismcrResponse", propOrder = {
    "prismCRList"
})
public class PrismcrResponse
    extends PrismResponse
{

    @XmlElement(required = true)
    protected List<Prismcr> prismCRList;

    /**
     * Gets the value of the prismCRList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the prismCRList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPrismCRList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Prismcr }
     * 
     * 
     */
    public List<Prismcr> getPrismCRList() {
        if (prismCRList == null) {
            prismCRList = new ArrayList<Prismcr>();
        }
        return this.prismCRList;
    }

}
