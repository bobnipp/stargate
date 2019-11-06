
package com.saic.prism.ws.researchws.prismresearchws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prismSortBy.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="prismSortBy"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="ID"/&gt;
 *     &lt;enumeration value="KEY"/&gt;
 *     &lt;enumeration value="LASTMODIFIEDDATE"/&gt;
 *     &lt;enumeration value="NAME"/&gt;
 *     &lt;enumeration value="PRISMID"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "prismSortBy")
@XmlEnum
public enum PrismSortBy {

    ID,
    KEY,
    LASTMODIFIEDDATE,
    NAME,
    PRISMID;

    public String value() {
        return name();
    }

    public static PrismSortBy fromValue(String v) {
        return valueOf(v);
    }

}
