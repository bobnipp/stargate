
package com.saic.prism.ws.coredataws.prismcoredataws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prismMissionStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="prismMissionStatus"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="SCHEDULED"/&gt;
 *     &lt;enumeration value="COMPLETED_AS_SCHEDULED"/&gt;
 *     &lt;enumeration value="COMPLETED_DEVIATION"/&gt;
 *     &lt;enumeration value="ABORTED_COMPLETE"/&gt;
 *     &lt;enumeration value="ABORTED_INCOMPLETE"/&gt;
 *     &lt;enumeration value="CANCELED"/&gt;
 *     &lt;enumeration value="DELAYED"/&gt;
 *     &lt;enumeration value="DELAYED_INDEFINITELY"/&gt;
 *     &lt;enumeration value="RESCHEDULED"/&gt;
 *     &lt;enumeration value="ON_LINK"/&gt;
 *     &lt;enumeration value="OFF_LINK"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "prismMissionStatus")
@XmlEnum
public enum PrismMissionStatus {

    SCHEDULED,
    COMPLETED_AS_SCHEDULED,
    COMPLETED_DEVIATION,
    ABORTED_COMPLETE,
    ABORTED_INCOMPLETE,
    CANCELED,
    DELAYED,
    DELAYED_INDEFINITELY,
    RESCHEDULED,
    ON_LINK,
    OFF_LINK;

    public String value() {
        return name();
    }

    public static PrismMissionStatus fromValue(String v) {
        return valueOf(v);
    }

}
