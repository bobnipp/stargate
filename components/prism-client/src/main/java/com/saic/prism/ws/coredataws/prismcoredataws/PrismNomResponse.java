
package com.saic.prism.ws.coredataws.prismcoredataws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prismNomResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prismNomResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismResponse"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="prismNomList" type="{http://PRISMCoreDataWS.coredataws.ws.prism.saic.com}prismNom" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prismNomResponse", propOrder = {
    "prismNomList"
})
public class PrismNomResponse
    extends PrismResponse
{

    @XmlElement(required = true)
    protected List<PrismNom> prismNomList;

    /**
     * Gets the value of the prismNomList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the prismNomList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPrismNomList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PrismNom }
     * 
     * 
     */
    public List<PrismNom> getPrismNomList() {
        if (prismNomList == null) {
            prismNomList = new ArrayList<PrismNom>();
        }
        return this.prismNomList;
    }

}
