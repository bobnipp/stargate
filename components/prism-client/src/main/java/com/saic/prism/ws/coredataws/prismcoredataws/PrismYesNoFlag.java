
package com.saic.prism.ws.coredataws.prismcoredataws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prismYesNoFlag.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="prismYesNoFlag"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="YES"/&gt;
 *     &lt;enumeration value="NO"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "prismYesNoFlag")
@XmlEnum
public enum PrismYesNoFlag {

    YES,
    NO;

    public String value() {
        return name();
    }

    public static PrismYesNoFlag fromValue(String v) {
        return valueOf(v);
    }

}
