package com.afresearchlab.stargate.persistence

import com.afresearchlab.stargate.rfis.model.Link
import com.afresearchlab.stargate.spechelpers.IntegrationSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DuplicateKeyException

class LinkRepositorySpec extends IntegrationSpec {

    @Autowired
    RfiRepository rfiRepository

    @Autowired
    LinkRepository subject


    def immLink = new Link('1', '2', 'IMM', 'PRISM', null, null)
    def prismLink = new Link('1', '4', 'PRISM', 'IMM', null, null)

    def expectedLinks = [immLink, prismLink]

    def 'save() saves a link'() {
        when:
        subject.save(immLink)
        def links = subject.getAllByRecordId('1')

        then:
        links == [immLink]
    }

    def 'cannot add duplicate links'() {
        when:
        subject.save(immLink)
        subject.save(immLink)

        then:
        DuplicateKeyException ex = thrown()
    }

    def 'saveAll() saves a list of links'() {
        when:
        subject.saveAll(expectedLinks, '1')
        def links = subject.getAllByRecordId('1')

        then:
        links == expectedLinks
    }

    def 'saveAll() works when the links array is empty'() {
        when:
        subject.saveAll([], '1')
        def links = subject.getAllByRecordId('1')

        then:
        links == []
    }

    def 'deleteAllByRecordId() deletes all links with the record id'() {
        given:
        expectedLinks.add(new Link('5', '1', 'IMM', 'IMM', null, null))
        subject.saveAll(expectedLinks, '1')

        when:
        subject.deleteAllByRecordId('1')
        def links = subject.getAllByRecordId('1')

        then:
        links == []
    }

    def 'delete() deletes by unique fields'() {
        given:
        expectedLinks.add(new Link('1', '3', 'IMM', 'PRISM', null, null))
        subject.saveAll(expectedLinks, '1')

        when:
        subject.delete('1', '3', 'IMM', 'PRISM')
        def links = subject.getAllByRecordId('1')

        then:
        links == [immLink, prismLink]
    }

    def 'getAllByRecordId() return includes inverse links'() {
        when:
        def reverseLink = new Link('3', '1', 'IMM', 'IMM', null, null)
        expectedLinks.add(reverseLink)

        subject.saveAll(expectedLinks, '1')
        def links = subject.getAllByRecordId('1')

        then:
        links == expectedLinks
    }
}
