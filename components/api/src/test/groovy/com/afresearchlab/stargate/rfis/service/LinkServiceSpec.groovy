package com.afresearchlab.stargate.rfis.service

import com.afresearchlab.prismadaptermodels.FullNom
import com.afresearchlab.stargate.persistence.LinkRepository
import com.afresearchlab.stargate.persistence.RfiRepository
import com.afresearchlab.stargate.requirements.NomNotFoundException
import com.afresearchlab.stargate.requirements.persistence.PrismRepository
import com.afresearchlab.stargate.rfis.model.Link
import com.afresearchlab.stargate.rfis.model.Rfi
import com.afresearchlab.stargate.rfis.requests.CreateRecordLinkRequest
import spock.lang.Specification

class LinkServiceSpec extends Specification {
    def linksRepository
    def linkService
    def rfiRepository
    def prismRepository

    def createLinkRequest = new CreateRecordLinkRequest('id1', 'system1', 'id2', 'system2')
    def recordLink = new Link('id1', 'id2', 'system1', 'system2', null, null)

    def setup() {
        linksRepository = Mock(LinkRepository)
        rfiRepository = Mock(RfiRepository)
        prismRepository = Mock(PrismRepository)
        linkService = new LinkService(linksRepository, rfiRepository, prismRepository)
    }

    def 'create creates a new link'() {
        when:
        linkService.create(createLinkRequest)

        then:
        1 * linksRepository.save(recordLink)
    }

    def 'deletes a link'() {
        when:
        linkService.delete('i','2','IMM','PRISM')

        then:
        1 * linksRepository.delete('i','2','IMM','PRISM')
    }

    def 'getAllByRecordId returns all complete links for a record'() {
        given:
        def rfi = new Rfi(1, 'test', 'US', '1.0, 2.0', 'thecity', 'theprovince', 'nai', 'IMMEDIATE', 'TO_DO', "descriptive things", "objective things", 1, 1, 1, 'custom classification', 1, 1, 'signature', 1, 1, 1, 'operation', '12 Aug 2019 13:00', '15 Nov 2019 13:00', 'collection term1', 'collection type1', 1, 'assignee1', 'collection guidance1', ['eei1', 'eei2'], [], [], [], [], [], '2017-12-01T23:43:12.123Z')
        def nom = FullNom.builder().id('nom1').targets(Collections.emptyList()).status('active').title('nomTitle').createdOn('2017-12-01T23:43:12.123Z').build()
        def links = [
            new Link('1', 'id1', 'IMM', 'PRISM', null, null),
            new Link('id1', 'nomId1', 'PRISM', 'PRISM', null, null)
        ]

        when:
        def result = linkService.getAllByRecordId('id1')

        then:
        1 * linksRepository.getAllByRecordId('id1') >> links
        1 * rfiRepository.get(1) >> rfi
        1 * prismRepository.getNominationById('nomId1') >> nom

        result == [
            new Link('1', 'id1', 'IMM', 'PRISM', 'test', 'TO_DO'),
            new Link('id1', 'nomId1', 'PRISM', 'PRISM', 'nomTitle', 'active')
        ]
    }

    def 'getAllByRecordId continues if a link is not found'() {
        given:
        def links = [new Link('1', '-1', 'IMM', 'PRISM', null, null), new Link('1', '3', 'IMM', 'PRISM', 'linked NomCr', 'active')]
        def nom = FullNom.builder().id('nom1').targets(Collections.emptyList()).status('active').title('nomTitle').createdOn('2017-12-01T23:43:12.123Z').build()

        when:
        def result = linkService.getAllByRecordId('1')

        then:
        1 * linksRepository.getAllByRecordId('1') >> links
        1 * prismRepository.getNominationById('-1') >> { throw new NomNotFoundException() }
        1 * prismRepository.getNominationById('3') >> nom
        result == links
    }

    def 'saveAll saves links to the repository'() {
        given:
        def links = [
            new Link('1', 'id1', 'IMM', 'PRISM', null, null),
            new Link('id1', 'nomId1', 'PRISM', 'PRISM', null, null)
        ]

        when:
        linkService.saveAll(links, '1')

        then:
        1 * linksRepository.saveAll(links, '1')
    }

    def 'deleteAll deletes links from the repository'() {
        when:
        linkService.deleteAll()

        then:
        1 * linksRepository.deleteAll()
    }
}
