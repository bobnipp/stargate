package com.afresearchlab.stargate.requirements.service

import com.afresearchlab.prismadaptermodels.FullNom
import com.afresearchlab.prismadaptermodels.IMMPrismTargetType
import com.afresearchlab.stargate.healthcheck.HealthCheck
import com.afresearchlab.stargate.requirements.model.ImmFullNom
import com.afresearchlab.stargate.requirements.persistence.PrismRepository
import com.afresearchlab.stargate.rfis.model.Activity
import com.afresearchlab.stargate.rfis.model.Link
import com.afresearchlab.stargate.rfis.service.ActivityService
import com.afresearchlab.stargate.rfis.service.LinkService
import com.saic.prism.ws.coredataws.prismcoredataws.PrismDataInfo
import com.saic.prism.ws.coredataws.prismcoredataws.PrismNom
import com.saic.prism.ws.coredataws.prismcoredataws.PrismNomIMINT
import com.saic.prism.ws.coredataws.prismcoredataws.PrismNomStatus
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl
import spock.lang.Specification

class PrismServiceSpec extends Specification {

    def linkService
    def activityService
    def prismRepository
    def subject

    def setup() {
        linkService = Mock(LinkService)
        activityService = Mock(ActivityService)
        prismRepository = Mock(PrismRepository)
        subject = new PrismService(linkService, prismRepository, activityService)
    }

    def 'calls prism repository and retrieves all nominations'() {
        given:
        def nom = new PrismNom()
        nom.id = 'nomId'
        nom.name = 'nomName1'
        def dataInfo = new PrismDataInfo()
        dataInfo.createdOn = XMLGregorianCalendarImpl.parse('2017-12-01T23:43:12.123Z')
        nom.setDataInfo(dataInfo)
        def nomIMINT = new PrismNomIMINT()
        nomIMINT.respOrg = 'ACC 01'
        nomIMINT.setStatus(PrismNomStatus.APPROVE)
        nom.setNomImint(nomIMINT)
        def targets = Collections.emptyList()
        def returnedNoms = [nom]
        def fullNom = FullNom.builder()
            .id('nomId')
            .title('nomName1')
            .status('APPROVE')
            .createdOn('2017-12-01T23:43:12.123Z')
            .targets(targets)
            .build()

        when:
        def noms = subject.getAllNominations()

        then:
        1 * prismRepository.getAllNominations() >> returnedNoms
        1 * prismRepository.getNominationById('nomId') >> fullNom
        noms == [ImmFullNom.builder().id('nomId').title('nomName1').status('APPROVE').createdOn('2017-12-01T23:43:12.123Z').targets(targets).build()]
    }

    def 'getAllNominations returns nom list items with correct plottedCoordinates'() {
        given:
        def nom = new PrismNom()
        nom.id = 'nomId'
        nom.name = 'nomName1'
        def target = new com.afresearchlab.prismadaptermodels.Target('name', IMMPrismTargetType.POINT, targetCoordinates)
        def returnedNoms = [nom]
        def fullNom = FullNom.builder()
                .id('nomId')
                .title('nomName1')
                .targets([target])
                .build()

        prismRepository.getAllNominations() >> returnedNoms
        prismRepository.getNominationById('nomId') >> fullNom

        when:
        def result = subject.getAllNominations()

        then:
        result == [
                ImmFullNom.builder().id('nomId').title('nomName1').coordinates(null).targets([target]).build()
        ]

        where:
        targetCoordinates    | resultCoordinates
        'foo bar; fizz buzz' | 'foo bar'
        'foo bar, fizz buzz' | 'foo bar, fizz buzz' //TODO this is not the behavior we want
        null                 | null
    }

    def 'calls prism repository and retrieves nomination by id'() {
        given:
        def links = [
            new Link('1', 'id1', 'IMM', 'PRISM', 'test', 'TO_DO'),
            new Link('id1', 'nomId1', 'PRISM', 'PRISM', 'nomTitle', 'active')
        ]
        def activities = [
            new Activity(1, 'nomId', 'text1', 'username1', 'date1', []),
            new Activity(2, 'nomId', 'text2', 'username2', 'date2', [])
        ]
        def targets = Collections.emptyList()
        def fullNom = FullNom.builder().id("nomId").title("nomTitle").status("nomStatus").targets(targets).build()
        def expectedNom = ImmFullNom.builder().id("nomId").title("nomTitle").status("nomStatus").targets(targets).links(links).activities(activities).build()

        when:
        def nom = subject.getNominationById('nomId')

        then:
        1 * linkService.getAllByRecordId('nomId') >> links
        1 * activityService.getAllByRfiId('nomId') >> activities
        1 * prismRepository.getNominationById('nomId') >> fullNom
        nom == expectedNom
    }

    def 'calls prism adapter health check'() {
        when:
        def response = subject.healthCheck()

        then:
        1 * prismRepository.healthCheck() >> new HealthCheck(true, 1)
        response.success
    }

    def 'handles repository failure'() {
        given:
        def mockRepository = Mock(PrismRepository)
        mockRepository.healthCheck() >> { throw new Exception() }
        def prismService = new PrismService(linkService, mockRepository, activityService)

        when:
        def response = prismService.healthCheck()

        then:
        !response.success
    }
}
