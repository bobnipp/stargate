
package com.saic.prism.ws.exnomws.prismexnomws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prismStandingAdhocFlag.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="prismStandingAdhocFlag"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="AD_HOC"/&gt;
 *     &lt;enumeration value="STANDING"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "prismStandingAdhocFlag")
@XmlEnum
public enum PrismStandingAdhocFlag {

    AD_HOC,
    STANDING;

    public String value() {
        return name();
    }

    public static PrismStandingAdhocFlag fromValue(String v) {
        return valueOf(v);
    }

}
