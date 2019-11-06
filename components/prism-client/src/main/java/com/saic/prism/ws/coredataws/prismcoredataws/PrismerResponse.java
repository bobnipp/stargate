
package com.saic.prism.ws.coredataws.prismcoredataws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prismerResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prismerResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismResponse"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="prismERList" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismer" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prismerResponse", propOrder = {
    "prismERList"
})
public class PrismerResponse
    extends PrismResponse
{

    @XmlElement(required = true)
    protected List<Prismer> prismERList;

    /**
     * Gets the value of the prismERList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the prismERList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPrismERList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Prismer }
     * 
     * 
     */
    public List<Prismer> getPrismERList() {
        if (prismERList == null) {
            prismERList = new ArrayList<Prismer>();
        }
        return this.prismERList;
    }

}
