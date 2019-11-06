
package com.saic.prism.ws.exnomws.prismexnomws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prismExNomRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prismExNomRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://PRISMExNomWS.exnomws.ws.prism.saic.com}prismRequest"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="nom" type="{http://PRISMExNomWS.exnomws.ws.prism.saic.com}prismNomSubmit"/&gt;
 *         &lt;element name="subscriptionHost" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prismExNomRequest", propOrder = {
    "nom",
    "subscriptionHost"
})
public class PrismExNomRequest
    extends PrismRequest
{

    @XmlElement(required = true)
    protected PrismNomSubmit nom;
    protected String subscriptionHost;

    /**
     * Gets the value of the nom property.
     * 
     * @return
     *     possible object is
     *     {@link PrismNomSubmit }
     *     
     */
    public PrismNomSubmit getNom() {
        return nom;
    }

    /**
     * Sets the value of the nom property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrismNomSubmit }
     *     
     */
    public void setNom(PrismNomSubmit value) {
        this.nom = value;
    }

    /**
     * Gets the value of the subscriptionHost property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubscriptionHost() {
        return subscriptionHost;
    }

    /**
     * Sets the value of the subscriptionHost property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubscriptionHost(String value) {
        this.subscriptionHost = value;
    }

}
