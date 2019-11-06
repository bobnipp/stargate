package com.afresearchlab.stargate.persistence

import com.afresearchlab.stargate.rfis.model.Rfi
import com.afresearchlab.stargate.spechelpers.IntegrationSpec
import org.springframework.beans.factory.annotation.Autowired

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class RfiRepositorySpec extends IntegrationSpec {

    @Autowired
    RfiRepository subject

    def testRfi = new Rfi(null, 'test', 'US', '1.0 2.0', 'thecity', 'theprovince', 'nai', 'IMMEDIATE', 'TO_DO', "descriptive things", "objective things", 1, 1, 1, 'custom class', 1, 1, 'signature', 1, 1, 1, 'operation', '12 Aug 2019 13:00', '15 Nov 2019 13:00', 'collection term1', 'collection type1', 1, 'assignee1', 'collection guidance1', ['eei1', 'eei2'], null, null, null, null, null, null)

    def 'it can save a new RFI'() {
        when:
        def createdRfi = subject.save(testRfi)
        def result = subject.get(createdRfi.id)

        then:
        createdRfi.id != null
        result.title == 'test'
        result.country == 'US'
        result.coordinates == '1.0 2.0'
        result.city == 'thecity'
        result.region == 'theprovince'
        result.nai == 'nai'
        result.priority == 'IMMEDIATE'
        result.status == 'TO_DO'
        result.justification == 'descriptive things'
        result.prevResearch == 'objective things'
        result.requirementTypeId == 1
        result.subWorkflowId == 1
        result.classificationId == 1
        result.customClassification == 'custom class'
        result.caveatId == 1
        result.submittingOrgId == 1
        result.poc == 'signature'
        result.nipfCodeId == 1
        result.pirNameId == 1
        result.centcomIsrRoleId == 1
        result.operation == 'operation'
        result.collectionStartDate == '12 Aug 2019 13:00'
        result.collectionEndDate == '15 Nov 2019 13:00'
        result.collectionTerm == 'collection term1'
        result.collectionType == 'collection type1'
        result.assignedTeamId == 1
        result.assignee == 'assignee1'
        result.collectionGuidance == 'collection guidance1'
        result.eeis == ['eei1', 'eei2']
        LocalDateTime.parse(result.createdOn, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toLocalDate() == LocalDateTime.now().toLocalDate()
    }

    def 'it can update an existing RFI'() {
        given:
        def createdRfi = subject.save(testRfi)

        when:
        createdRfi.title = 'things'
        createdRfi.country = 'Canadia'
        createdRfi.coordinates = '2.0 3.0'
        createdRfi.city = 'newcity'
        createdRfi.priority = 'LOW'
        createdRfi.status = 'TO_DO'
        createdRfi.justification = "less descriptive things"
        createdRfi.prevResearch = "subjective things"
        createdRfi.requirementTypeId = 2
        createdRfi.subWorkflowId = 2
        createdRfi.classificationId = 2
        createdRfi.caveatId = 2
        createdRfi.submittingOrgId = 2
        createdRfi.nipfCodeId = 2
        createdRfi.pirNameId = 2
        createdRfi.centcomIsrRoleId = 2
        createdRfi.customClassification = 'other custom class'
        createdRfi.poc = 'other signature'
        createdRfi.operation = 'other operation'
        createdRfi.collectionStartDate = '13 Aug 2019 13:00'
        createdRfi.collectionEndDate = '16 Nov 2019 13:00'
        createdRfi.collectionTerm = 'collection term2'
        createdRfi.collectionType = 'collection type2'
        createdRfi.assignedTeamId = 2
        createdRfi.assignee = 'assignee2'
        createdRfi.collectionGuidance = 'collection guidance 2'
        createdRfi.eeis = ['eei1', 'eei2', 'eei3']

        subject.update(createdRfi)
        def result = subject.get(createdRfi.id)

        then:
        result.title == 'things'
        result.country == 'Canadia'
        result.coordinates == '2.0 3.0'
        result.city == 'newcity'
        result.priority == 'LOW'
        result.status == 'TO_DO'
        result.justification == "less descriptive things"
        result.prevResearch == "subjective things"
        result.requirementTypeId == 2
        result.subWorkflowId == 2
        result.classificationId == 2
        result.caveatId == 2
        result.submittingOrgId == 2
        result.nipfCodeId == 2
        result.pirNameId == 2
        result.centcomIsrRoleId == 2
        result.customClassification == 'other custom class'
        result.poc == 'other signature'
        result.operation == 'other operation'
        result.collectionStartDate == '13 Aug 2019 13:00'
        result.collectionEndDate == '16 Nov 2019 13:00'
        result.collectionTerm == 'collection term2'
        result.collectionType == 'collection type2'
        result.assignedTeamId == 2
        result.assignee == 'assignee2'
        result.collectionGuidance == 'collection guidance 2'
        result.eeis == ['eei1', 'eei2', 'eei3']
        LocalDateTime.parse(result.createdOn, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toLocalDate() == LocalDateTime.now().toLocalDate()
    }

    def 'getAll returns all rfis'() {
        given:
        def rfis = [
            new Rfi(null, 'jello', 'UK', null, null, null, null, null, null, null, null, 3, 3, 3, null, 3, 3, null, 3, 3, 3, null, null, null, null, null, 1, null, null, null, null, null, null, null, null, null),
            new Rfi(null, 'ello', 'UK', '4.5 6.7', 'theothercity', 'theotherprovince', 'theothernai', 'ROUTINE', 'ACTIVE', "super descriptive things", "subjective things", 2, 2, 2, 'something 2', 2, 2, 'signature 2', 2, 2, 2, 'operation 2', '12 Aug 2019 13:00', '15 Nov 2019 13:00', 'collection term1', 'collection type1', 1, 'assignee1', 'collection guidance1', ['eei1', 'eei2'], null, null, null, null, null, null),
            new Rfi(null, 'title', 'CA', '1.0 2.0', 'thecity', 'theprovince', 'nai', 'LOW', 'CLOSED', "descriptive things", "objective things", 1, 1, 1, 'something', 1, 1, 'signature', 1, 1, 1, 'operation', '12 Aug 2019 13:00', '15 Nov 2019 13:00', 'collection term1', 'collection type1', 1, 'assignee1', 'collection guidance1', ['eei1', 'eei2'], null, null, null, null, null, null)
        ]

        rfis.each {
            subject.save(it)
        }

        when:
        def result = subject.getAll()

        then:
        result.id == [3L, 2L, 1L]
        result.title == ['title', 'ello', 'jello']
        result.country == ['CA', 'UK', 'UK']
        result.coordinates == ['1.0 2.0', '4.5 6.7', null]
        result.city == ['thecity', 'theothercity', null]
        result.region == ['theprovince', 'theotherprovince', null]
        result.nai == ['nai', 'theothernai', null]
        result.priority == ['LOW', 'ROUTINE', null]
        result.status == ['CLOSED', 'ACTIVE', null]
        result.justification == ["descriptive things", "super descriptive things", null]
        result.prevResearch == ["objective things", "subjective things", null]
        result.requirementTypeId == [1, 2, 3]
        result.subWorkflowId == [1, 2, 3]
        result.classificationId == [1, 2, 3]
        result.caveatId == [1, 2, 3]
        result.submittingOrgId == [1, 2, 3]
        result.nipfCodeId == [1, 2, 3]
        result.pirNameId == [1, 2, 3]
        result.centcomIsrRoleId == [1, 2, 3]
        result.customClassification == ['something', 'something 2', null]
        result.poc == ['signature', 'signature 2', null]
        result.operation == ['operation', 'operation 2', null]
        result.collectionStartDate == ['12 Aug 2019 13:00', '12 Aug 2019 13:00', null]
        result.collectionEndDate == ['15 Nov 2019 13:00', '15 Nov 2019 13:00', null]
        result.collectionTerm == ['collection term1', 'collection term1', null]
        result.collectionType == ['collection type1', 'collection type1', null]
        result.assignedTeamId == [1, 1, 1]
        result.assignee == ['assignee1', 'assignee1', null]
        result.collectionGuidance == ['collection guidance1', 'collection guidance1', null]
        result.eeis == [['eei1', 'eei2'], ['eei1', 'eei2'], null]
        LocalDateTime.parse(result[0].createdOn, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toLocalDate() == LocalDateTime.now().toLocalDate()
        LocalDateTime.parse(result[1].createdOn, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toLocalDate() == LocalDateTime.now().toLocalDate()
        LocalDateTime.parse(result[2].createdOn, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toLocalDate() == LocalDateTime.now().toLocalDate()
    }

    def 'get returns rfi by id'() {
        given:
        def rfi = subject.save(testRfi)

        when:
        def result = subject.get(rfi.id)

        then:
        result.title == 'test'
        result.country == 'US'
        result.coordinates == '1.0 2.0'
        result.city == 'thecity'
        result.region == 'theprovince'
        result.nai == 'nai'
        result.priority == 'IMMEDIATE'
        result.status == 'TO_DO'
        result.justification == 'descriptive things'
        result.prevResearch == 'objective things'
        result.requirementTypeId == 1
        result.subWorkflowId == 1
        result.classificationId == 1
        result.caveatId == 1
        result.submittingOrgId == 1
        result.nipfCodeId == 1
        result.pirNameId == 1
        result.centcomIsrRoleId == 1
        result.customClassification == 'custom class'
        result.poc == 'signature'
        result.operation == 'operation'
        result.collectionStartDate == '12 Aug 2019 13:00'
        result.collectionEndDate == '15 Nov 2019 13:00'
        result.collectionTerm == 'collection term1'
        result.collectionType == 'collection type1'
        result.assignedTeamId == 1
        result.assignee == 'assignee1'
        result.collectionGuidance == 'collection guidance1'
        result.eeis == ['eei1', 'eei2']
        LocalDateTime.parse(result.createdOn, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toLocalDate() == LocalDateTime.now().toLocalDate()
    }

    def 'get handles invalid links'() {
        when:
        def result = subject.get(9999)   // bogus RFI ID

        then:
        result == null
    }

    def 'delete deletes the rfi'() {
        given:
        def rfi = subject.save(testRfi)

        when:
        subject.delete(rfi.id)
        def deletedRfi = subject.get(rfi.id)

        then:
        deletedRfi == null
    }

    def 'calls healthcheck on db'() {
        when:
        def result = subject.healthCheck()

        then:
        result
    }
}
