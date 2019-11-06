
package com.saic.prism.ws.researchws.prismresearchws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prismSpatialSearchType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="prismSpatialSearchType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="CIRCLE"/&gt;
 *     &lt;enumeration value="POLYGON"/&gt;
 *     &lt;enumeration value="RECTANGLE"/&gt;
 *     &lt;enumeration value="OP_AREA"/&gt;
 *     &lt;enumeration value="AOI"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "prismSpatialSearchType")
@XmlEnum
public enum PrismSpatialSearchType {

    CIRCLE,
    POLYGON,
    RECTANGLE,
    OP_AREA,
    AOI;

    public String value() {
        return name();
    }

    public static PrismSpatialSearchType fromValue(String v) {
        return valueOf(v);
    }

}
