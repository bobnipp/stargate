
package com.saic.prism.ws.exnomws.prismexnomws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prismNomStatusResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prismNomStatusResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://PRISMExNomWS.exnomws.ws.prism.saic.com}prismResponse"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="nom" type="{http://PRISMExNomWS.exnomws.ws.prism.saic.com}prismNomShort"/&gt;
 *         &lt;element name="subscribeResponse" type="{http://PRISMExNomWS.exnomws.ws.prism.saic.com}prismSubscribeResponse" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prismNomStatusResponse", propOrder = {
    "nom",
    "subscribeResponse"
})
public class PrismNomStatusResponse
    extends PrismResponse
{

    @XmlElement(required = true)
    protected PrismNomShort nom;
    protected PrismSubscribeResponse subscribeResponse;

    /**
     * Gets the value of the nom property.
     * 
     * @return
     *     possible object is
     *     {@link PrismNomShort }
     *     
     */
    public PrismNomShort getNom() {
        return nom;
    }

    /**
     * Sets the value of the nom property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismNomShort }
     *     
     */
    public void setNom(PrismNomShort value) {
        this.nom = value;
    }

    /**
     * Gets the value of the subscribeResponse property.
     * 
     * @return
     *     possible object is
     *     {@link PrismSubscribeResponse }
     *     
     */
    public PrismSubscribeResponse getSubscribeResponse() {
        return subscribeResponse;
    }

    /**
     * Sets the value of the subscribeResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismSubscribeResponse }
     *     
     */
    public void setSubscribeResponse(PrismSubscribeResponse value) {
        this.subscribeResponse = value;
    }

}
