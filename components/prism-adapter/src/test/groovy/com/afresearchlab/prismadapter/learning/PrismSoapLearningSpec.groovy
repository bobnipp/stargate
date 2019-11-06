package com.afresearchlab.prismadapter.learning

import com.afresearchlab.prismadapter.IntegrationSpec
import com.afresearchlab.prismadapter.service.PrismUiService
import com.saic.prism.ws.coredataws.prismcoredataws.PRISMCoreDataWSEndPoint
import com.saic.prism.ws.coredataws.prismcoredataws.PrismMultiDataRequest
import com.saic.prism.ws.coredataws.prismcoredataws.Prismcr
import com.saic.prism.ws.coredataws.prismcoredataws.PrismcrResponse
import com.saic.prism.ws.exnomws.prismexnomws.PRISMExNomWSEndPoint
import com.saic.prism.ws.exnomws.prismexnomws.PrismCoord
import com.saic.prism.ws.exnomws.prismexnomws.PrismExNomRequest
import com.saic.prism.ws.exnomws.prismexnomws.PrismNomIMINTSubmit
import com.saic.prism.ws.exnomws.prismexnomws.PrismNomSubmit
import com.saic.prism.ws.exnomws.prismexnomws.PrismRequirementType
import com.saic.prism.ws.exnomws.prismexnomws.PrismStandingAdhocFlag
import com.saic.prism.ws.exnomws.prismexnomws.PrismTargetSubmit
import com.saic.prism.ws.exnomws.prismexnomws.PrismTargetType
import com.saic.prism.ws.exnomws.prismexnomws.PrismcrPeriodType
import com.saic.prism.ws.exnomws.prismexnomws.PrismcrSubmit
import com.saic.prism.ws.exnomws.prismexnomws.PrismcrTargetSubmit
import com.saic.prism.ws.exnomws.prismexnomws.Prismdsa
import com.saic.prism.ws.researchws.prismresearchws.PRISMResearchWSEndPoint
import com.saic.prism.ws.researchws.prismresearchws.PrismNomShortRequest
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Ignore
import spock.lang.Shared

@Ignore('Hitting real PRISM')
class PrismSoapLearningSpec extends IntegrationSpec {

    @Autowired
    PRISMResearchWSEndPoint researchEndpoint

    @Autowired
    PRISMCoreDataWSEndPoint coreDataEndpoint

    @Autowired
    PRISMExNomWSEndPoint exNomEndpoint

    @Autowired
    PrismUiService uiService

    @Shared
    def username

    @Shared
    def password

    def setup() {
        username = System.getenv("PRISM_SOAP_USERNAME")
        password = System.getenv("PRISM_SOAP_PASSWORD")
    }

    def 'get list of approved NOM summaries'() {
        given:
        PrismNomShortRequest nomShortRequest = new PrismNomShortRequest()
        nomShortRequest.setUserID(username)
        nomShortRequest.setPassword(password)

        def research = researchEndpoint.getNomResearch(nomShortRequest)

        expect:
        println research
    }

    //NOTE: use this API when retrieving a NOM and associated CR, Target, etc.
    def 'get list of NOM call fails when adding more than 20 keys'() {
        given:
        def request = new PrismMultiDataRequest()
        request.setUserID(username)
        request.setPassword(password)
        request.setResultSetSize(21)//
        request.keyList = [
            '000000000002002PR7AWS',
            '100000000002002PR7AWS',
            '200000000002002PR7AWS',
            '300000000002002PR7AWS',
            '400000000002002PR7AWS',
            '500000000002002PR7AWS',
            '600000000002002PR7AWS',
            '700000000002002PR7AWS',
            '800000000002002PR7AWS',
            '0900000000002002PR7AWS',
            '010000000002002PR7AWS',
            '020000000002002PR7AWS',
            '030000000002002PR7AWS',
            '040000000002002PR7AWS',
            '050000000002002PR7AWS',
            '0060000000002002PR7AWS',
            '007000000002002PR7AWS',
            '008000000002002PR7AWS',
            '009000000002002PR7AWS',
            '000100000002002PR7AWS',
            '000000000001511PR7AWS'
        ]

        def response = coreDataEndpoint.getNOM(request)

        expect:
        println response
    }

    //NOTE: This works only once with the same id
    //NOTE: use this API when attaching a CR, Target, etc.
    def 'create a NOM with CR - minimum amount of data - find required fields'() {
        given:
        def crSubmit = new PrismcrSubmit()
        crSubmit.setName('crName')
        crSubmit.setPriority(1)
        crSubmit.setStartDate(XMLGregorianCalendarImpl.createDate(2000, 12, 12, 0))
        crSubmit.setStopDate(XMLGregorianCalendarImpl.createDate(2000, 12, 13, 0))
        crSubmit.setAdHocFlag(PrismStandingAdhocFlag.STANDING)
        crSubmit.setType(PrismRequirementType.ACTIVE)
        //Once the above are provided, we start getting specific errors of stuff that needs to be provided for recon types
        crSubmit.setImagesPerPeriod(1)
        crSubmit.setPeriodType(PrismcrPeriodType.FIRST_COLLECT)
        crSubmit.setPeriod(60)
        //The last 3 made it work even though they weren't listed as required in the docs

        def submit = new PrismNomIMINTSubmit()
        submit.setRespOrg('ACC TEST')
        submit.getCrList().add(crSubmit)

        def nom = new PrismNomSubmit()
        nom.setName('Submitted from Learning Spec3')
        nom.setNomImintSubmit(submit)

        def request = new PrismExNomRequest()
        request.setUserID(username)
        request.setPassword(password)
        request.setNom(nom)

        def response = exNomEndpoint.submitExNom(request)

        exNomEndpoint.resubmitExNom()

        expect:
        println response
    }

    def 'NOM with a CR that has a DSA Target'() {
        given:
        def crSubmit = new PrismcrSubmit()
        crSubmit.setName('crName')
        crSubmit.setPriority(1)
        crSubmit.setStartDate(XMLGregorianCalendarImpl.createDate(2000, 12, 12, 0))
        crSubmit.setStopDate(XMLGregorianCalendarImpl.createDate(2000, 12, 13, 0))
        crSubmit.setAdHocFlag(PrismStandingAdhocFlag.STANDING)
        crSubmit.setType(PrismRequirementType.ACTIVE)
        //Once the above are provided, we start getting specific errors of stuff that needs to be provided for recon types
        crSubmit.setImagesPerPeriod(1)
        crSubmit.setPeriodType(PrismcrPeriodType.FIRST_COLLECT)
        crSubmit.setPeriod(60)
        //The last 3 made it work even though they weren't listed as required in the docs

        def c1 = new PrismCoord()
        c1.latDouble = 1.0
        c1.longDouble = 2.0
        def c2 = new PrismCoord()
        c2.latDouble = 2.0
        c2.longDouble = 3.0
        def c3 = new PrismCoord()
        c3.latDouble = 3.0
        c3.longDouble = 1.0

        def dsa = new Prismdsa()
        dsa.getCoordinates().add(c1)
        dsa.getCoordinates().add(c2)
        dsa.getCoordinates().add(c3)

        def otherTarget = new PrismTargetSubmit()
        otherTarget.setId("PD_PLEASE_WORK_7")
        otherTarget.setName("PLEASE_WORK")
        otherTarget.setType(PrismTargetType.DSA)
        otherTarget.setDsa(dsa)

        def submit = new PrismNomIMINTSubmit()
        submit.setRespOrg('ACC TEST')
        submit.getCrList().add(crSubmit)

        submit.getTargetList().add(otherTarget)

        def target = new PrismcrTargetSubmit()
        target.setTargetId("PD_PLEASE_WORK_7")
        target.setTgtType(PrismTargetType.DSA) //Can change this to LOC

        crSubmit.getTargetIdList().add(target)

        def nom = new PrismNomSubmit()
        nom.setName('Submitted from Learning Spec11')
        nom.setNomImintSubmit(submit)

        def request = new PrismExNomRequest()
        request.setUserID(username)
        request.setPassword(password)
        request.setNom(nom)

        def response = exNomEndpoint.submitExNom(request)

        expect:
        println response
    }

    def 'repeatedly log in to PRISM via UI API'() {
        given:
        //nothing

        when:
        5.times {
            uiService.getAllNominations()
        }

        then:
        //noExceptionThrown()
        true == true
    }

    def 'get a cr that has a target'() {
        given:
        //get cr
        PrismMultiDataRequest request2 = new PrismMultiDataRequest()
        request2.setUserID(username)
        request2.setPassword(password)
        request2.getKeyList().add('000000000002102PR7AWS')

        PrismcrResponse fullCrs = coreDataEndpoint.getCR(request2)
        Prismcr fullcr = fullCrs.getPrismCRList().get(0)

        def target = fullcr.getTargetList().get(0)
        def targetKey = target.getTarget().getKey()

        PrismMultiDataRequest request3 = new PrismMultiDataRequest()
        request3.setUserID('RANDREWS');
        request3.setPassword('Educate80authority!')
        request3.getKeyList().add(targetKey)

        when:
        def stuff = coreDataEndpoint.getTarget(request3)
        //get target

        then:
        println stuff
    }
}
