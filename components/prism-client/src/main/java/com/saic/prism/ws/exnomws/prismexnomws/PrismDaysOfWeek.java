
package com.saic.prism.ws.exnomws.prismexnomws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prismDaysOfWeek.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="prismDaysOfWeek"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="SUNDAY"/&gt;
 *     &lt;enumeration value="MONDAY"/&gt;
 *     &lt;enumeration value="TUESDAY"/&gt;
 *     &lt;enumeration value="WEDNESDAY"/&gt;
 *     &lt;enumeration value="THURSDAY"/&gt;
 *     &lt;enumeration value="FRIDAY"/&gt;
 *     &lt;enumeration value="SATURDAY"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "prismDaysOfWeek")
@XmlEnum
public enum PrismDaysOfWeek {

    SUNDAY,
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY;

    public String value() {
        return name();
    }

    public static PrismDaysOfWeek fromValue(String v) {
        return valueOf(v);
    }

}
