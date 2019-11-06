package com.saic.prism.ws.researchws.prismresearchws;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.3.0-SNAPSHOT-ea2e0c5846cfab98729da7c79d8dc8cf82a770c0
 * 2018-09-28T09:07:45.862-06:00
 * Generated source version: 3.3.0-SNAPSHOT
 *
 */
@WebServiceClient(name = "PRISMResearchWS",
                  wsdlLocation = "file:components/prism-client/src/main/resources/prism-wsdl/PRISMResearchWS.wsdl",
                  targetNamespace = "http://PRISMResearchWS.researchws.ws.prism.saic.com")
public class PRISMResearchWS extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://PRISMResearchWS.researchws.ws.prism.saic.com", "PRISMResearchWS");
    public final static QName PRISMResearchWSEndPointPort = new QName("http://PRISMResearchWS.researchws.ws.prism.saic.com", "PRISMResearchWSEndPointPort");
    static {
        URL url = null;
        try {
            url = new URL("file:components/prism-client/src/main/resources/prism-wsdl/PRISMResearchWS.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(PRISMResearchWS.class.getName())
                .log(java.util.logging.Level.INFO,
                     "Can not initialize the default wsdl from {0}", "file:components/prism-client/src/main/resources/prism-wsdl/PRISMResearchWS.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public PRISMResearchWS(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public PRISMResearchWS(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public PRISMResearchWS() {
        super(WSDL_LOCATION, SERVICE);
    }

    public PRISMResearchWS(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public PRISMResearchWS(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public PRISMResearchWS(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }




    /**
     *
     * @return
     *     returns PRISMResearchWSEndPoint
     */
    @WebEndpoint(name = "PRISMResearchWSEndPointPort")
    public PRISMResearchWSEndPoint getPRISMResearchWSEndPointPort() {
        return super.getPort(PRISMResearchWSEndPointPort, PRISMResearchWSEndPoint.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PRISMResearchWSEndPoint
     */
    @WebEndpoint(name = "PRISMResearchWSEndPointPort")
    public PRISMResearchWSEndPoint getPRISMResearchWSEndPointPort(WebServiceFeature... features) {
        return super.getPort(PRISMResearchWSEndPointPort, PRISMResearchWSEndPoint.class, features);
    }

}
