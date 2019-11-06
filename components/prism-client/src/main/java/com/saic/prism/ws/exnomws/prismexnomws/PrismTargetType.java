
package com.saic.prism.ws.exnomws.prismexnomws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prismTargetType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="prismTargetType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="DSA"/&gt;
 *     &lt;enumeration value="LOC"/&gt;
 *     &lt;enumeration value="POINT"/&gt;
 *     &lt;enumeration value="RWAC"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "prismTargetType")
@XmlEnum
public enum PrismTargetType {

    DSA,
    LOC,
    POINT,
    RWAC;

    public String value() {
        return name();
    }

    public static PrismTargetType fromValue(String v) {
        return valueOf(v);
    }

}
