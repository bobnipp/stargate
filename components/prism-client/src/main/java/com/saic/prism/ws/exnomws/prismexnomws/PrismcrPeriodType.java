
package com.saic.prism.ws.exnomws.prismexnomws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prismcrPeriodType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="prismcrPeriodType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="FIRST_COLLECT"/&gt;
 *     &lt;enumeration value="FIXED_STR"/&gt;
 *     &lt;enumeration value="LAST_COLLECT_STR"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "prismcrPeriodType")
@XmlEnum
public enum PrismcrPeriodType {

    FIRST_COLLECT,
    FIXED_STR,
    LAST_COLLECT_STR;

    public String value() {
        return name();
    }

    public static PrismcrPeriodType fromValue(String v) {
        return valueOf(v);
    }

}
