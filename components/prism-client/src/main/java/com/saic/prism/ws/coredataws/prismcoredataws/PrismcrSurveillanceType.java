
package com.saic.prism.ws.coredataws.prismcoredataws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prismcrSurveillanceType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="prismcrSurveillanceType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="EVENT_DRIVEN"/&gt;
 *     &lt;enumeration value="PERSISTENT"/&gt;
 *     &lt;enumeration value="RAPID_REVISIT"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "prismcrSurveillanceType")
@XmlEnum
public enum PrismcrSurveillanceType {

    EVENT_DRIVEN,
    PERSISTENT,
    RAPID_REVISIT;

    public String value() {
        return name();
    }

    public static PrismcrSurveillanceType fromValue(String v) {
        return valueOf(v);
    }

}
