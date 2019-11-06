package com.afresearchlab.prismadapter

import com.afresearchlab.prismadapter.service.PrismSOAPService
import com.saic.prism.ws.coredataws.prismcoredataws.*
import com.saic.prism.ws.researchws.prismresearchws.PRISMResearchWSEndPoint
import com.saic.prism.ws.researchws.prismresearchws.PrismDataInfo
import com.saic.prism.ws.researchws.prismresearchws.PrismNomShort
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl
import spock.lang.Specification

class PrismSOAPServiceSpec extends Specification {
    def mockResearchEndpoint
    def mockCoreDataEndpoint
    def subject

    def nomShort1
    def nomShort2

    def setup() {
        mockResearchEndpoint = Mock(PRISMResearchWSEndPoint)
        mockCoreDataEndpoint = Mock(PRISMCoreDataWSEndPoint)
        subject = new PrismSOAPService(mockResearchEndpoint, mockCoreDataEndpoint,
            'PRISM_SOAP_USERNAME', 'PRISM_SOAP_PASSWORD')

        nomShort1 = new PrismNomShort()
        nomShort2 = new PrismNomShort()

        def dataInfo1 = new PrismDataInfo()
        def dataInfo2 = new PrismDataInfo()

        dataInfo1.setCreatedOn(XMLGregorianCalendarImpl.parse('2018-01-01'))
        dataInfo1.setPrismStatus('Working')

        dataInfo2.setCreatedOn(XMLGregorianCalendarImpl.parse('2017-01-01'))
        dataInfo2.setPrismStatus('Hold')

        nomShort1.setKey('nomKey1')
        nomShort1.setId('nomId1')
        nomShort1.setName('nom name 1')
        nomShort1.setJustification('justification to description 1')
        nomShort1.setDataInfo(dataInfo1)

        nomShort2.setKey('nomKey2')
        nomShort2.setId('nomId2')
        nomShort2.setName('nom name 2')
        nomShort2.setJustification('justification to description 2')
        nomShort2.setDataInfo(dataInfo2)
    }

    def 'retrieves a single nomination by id'() {
        given:
        PrismNom prismNom = new PrismNom()

        def dataInfo = new com.saic.prism.ws.coredataws.prismcoredataws.PrismDataInfo()
        dataInfo.setCreatedOn(XMLGregorianCalendarImpl.parse('2017-01-01'))
        dataInfo.setPrismStatus('Hold')

        prismNom.setKey('nomKey1')
        prismNom.setId('nomId1')
        prismNom.setName('nom name 1')
        prismNom.setJustification('justification to description 1')
        prismNom.setDataInfo(dataInfo)

        def nomImint = new PrismNomIMINT()
        def cr = new Prismcr()
        cr.setId('crId')
        nomImint.getCrList().add(cr)

        prismNom.setNomImint(nomImint)

        def fakeResponse = new PrismNomResponse()
        fakeResponse.prismNomList = [prismNom]
        1 * mockCoreDataEndpoint.getNOM(_) >> fakeResponse

        when:
        def nomination = subject.getNomination('nomKey1')

        then:
        nomination.key == prismNom.key
        nomination.name == prismNom.name
    }

    def 'retrieves a list of nominations by a list of ids'() {
        given:
        def dataInfo = new com.saic.prism.ws.coredataws.prismcoredataws.PrismDataInfo()
        dataInfo.setCreatedOn(XMLGregorianCalendarImpl.parse('2017-01-01'))
        dataInfo.setPrismStatus('Hold')

        def nomImint = new PrismNomIMINT()
        def cr = new Prismcr()
        cr.setId('crId')
        nomImint.getCrList().add(cr)

        def prismNom1 = new PrismNom()
        prismNom1.setKey('nomKey1')
        prismNom1.setId('nomId1')
        prismNom1.setName('nom name 1')
        prismNom1.setJustification('justification to description 1')
        prismNom1.setDataInfo(dataInfo)
        prismNom1.setNomImint(nomImint)

        def prismNom2 = new PrismNom()
        prismNom2.setKey('nomKey2')
        prismNom2.setId('nomId2')
        prismNom2.setName('nom name 2')
        prismNom2.setJustification('justification to description 2')
        prismNom2.setDataInfo(dataInfo)

        def fakeResponse = new PrismNomResponse()
        fakeResponse.prismNomList = [prismNom1, prismNom2]
        1 * mockCoreDataEndpoint.getNOM(_) >> fakeResponse

        when:
        def nominations = subject.getNominations(['nomKey1', 'nomKey2'])

        then:
        nominations[0].key == prismNom1.key
        nominations[0].name == prismNom1.name

        nominations[1].key == prismNom2.key
        nominations[1].name == prismNom2.name
    }

    def 'getNominations() breaks the request into chunks of 20 keys'() {
        given:
        def keys = []
        42.times {
            keys.add("${it}")
        }

        when:
        subject.getNominations(keys)

        then:
        3 * mockCoreDataEndpoint.getNOM(_) >> new PrismNomResponse()
    }

    def 'retrieves a single requirement by id'() {
        given:
        Prismcr prismCr = new Prismcr()
        prismCr.setKey('crKey')
        prismCr.setId('crId')

        def dataInfo = new com.saic.prism.ws.coredataws.prismcoredataws.PrismDataInfo()
        dataInfo.setCreatedOn(XMLGregorianCalendarImpl.parse('2017-01-01'))
        dataInfo.setPrismStatus('Hold')

        prismCr.setDataInfo(dataInfo)

        PrismcrTarget prismCrTarget = new PrismcrTarget()
        prismCrTarget.crxtgtKey = 'targetKey'
        prismCr.getTargetList().add(prismCrTarget)

        def fakeResponse = new PrismcrResponse()
        fakeResponse.prismCRList.add(prismCr)
        1 * mockCoreDataEndpoint.getCR(_) >> fakeResponse

        when:
        def retrievedCr = subject.getCollectionRequirement('crKey')

        then:
        retrievedCr.key == prismCr.key
        retrievedCr.targetList.size() == prismCr.targetList.size()
    }

    def 'retrieves a single target by key'() {
        given:
        def target = new PrismTarget()
        target.key = 'targetKey'
        def prismdsa = new Prismdsa()
        target.dsa = prismdsa
        def coordinates = prismdsa.coordinates
        def coord = new PrismCoord()
        coord.elevation = 1234
        coord.latDouble = 1
        coord.longDouble = 2
        coordinates.add(coord)

        def fakeResponse = new PrismTargetResponse()
        fakeResponse.getPrismTargetList().add(target)

        1 * mockCoreDataEndpoint.getTarget(_) >> fakeResponse

        when:
        def retrievedTarget = subject.getTarget('targetKey')

        then:
        retrievedTarget.key == target.key
        retrievedTarget.dsa.coordinates.get(0).elevation == target.dsa.coordinates.get(0).elevation
        retrievedTarget.dsa.coordinates.get(0).latDouble == target.dsa.coordinates.get(0).latDouble
        retrievedTarget.dsa.coordinates.get(0).longDouble == target.dsa.coordinates.get(0).longDouble
    }
}
