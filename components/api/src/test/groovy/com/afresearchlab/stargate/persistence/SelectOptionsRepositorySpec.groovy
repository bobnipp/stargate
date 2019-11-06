package com.afresearchlab.stargate.persistence

import com.afresearchlab.stargate.rfis.model.SelectOption
import spock.lang.Specification

class SelectOptionsRepositorySpec extends Specification {

    def repository

    def setup() {
        repository = new SelectOptionsRepository()
    }

    def 'getOptions returns all of the options for RFIs'() {
        given:
        def expectedResult = [
            'requirementTypeOptions': [
                new SelectOption(1, "Requirement Type 1"),
                new SelectOption(2, "Requirement Type 2"),
                new SelectOption(3, "Requirement Type 3"),
                new SelectOption(4, "Requirement Type 4"),
                new SelectOption(5, "Requirement Type 5")
            ],
            'subWorkflowOptions'    : [
                new SelectOption(6, "SubWorkFlow 1"),
                new SelectOption(7, "SubWorkFlow 2"),
                new SelectOption(8, "SubWorkFlow 3"),
                new SelectOption(9, "SubWorkFlow 4"),
                new SelectOption(10, "SubWorkFlow 5")
            ],
            'classificationOptions' : [
                new SelectOption(11, "Classification 1"),
                new SelectOption(12, "Classification 2"),
                new SelectOption(13, "Classification 3"),
                new SelectOption(14, "Classification 4"),
                new SelectOption(15, "Classification 5")
            ],
            'caveatOptions'         : [
                new SelectOption(16, "Caveat 1"),
                new SelectOption(17, "Caveat 2"),
                new SelectOption(18, "Caveat 3"),
                new SelectOption(19, "Caveat 4"),
                new SelectOption(20, "Caveat 5")
            ],
            'submittingOrgOptions'  : [
                new SelectOption(21, "Submitting Organization 1"),
                new SelectOption(22, "Submitting Organization 2"),
                new SelectOption(23, "Submitting Organization 3"),
                new SelectOption(24, "Submitting Organization 4"),
                new SelectOption(25, "Submitting Organization 5")
            ],
            'nipfCodeOptions'       : [
                new SelectOption(26, "NIPF Code 1"),
                new SelectOption(27, "NIPF Code 2"),
                new SelectOption(28, "NIPF Code 3"),
                new SelectOption(29, "NIPF Code 4"),
                new SelectOption(30, "NIPF Code 5")
            ],
            'pirNameOptions'        : [
                new SelectOption(31, "Commander PIR 1"),
                new SelectOption(32, "Commander PIR 2"),
                new SelectOption(33, "Commander PIR 3"),
                new SelectOption(34, "Commander PIR 4"),
                new SelectOption(35, "Commander PIR 5")
            ],
            'centcomIsrRoleOptions' : [
                new SelectOption(36, "CENTCOM ISR Role 1"),
                new SelectOption(37, "CENTCOM ISR Role 2"),
                new SelectOption(38, "CENTCOM ISR Role 3"),
                new SelectOption(39, "CENTCOM ISR Role 4"),
                new SelectOption(40, "CENTCOM ISR Role 5")
            ],
            'assignedTeamOptions'   : [
                new SelectOption(41, "Assigned Team 1"),
                new SelectOption(42, "Assigned Team 2"),
                new SelectOption(43, "Assigned Team 3"),
                new SelectOption(44, "Assigned Team 4"),
                new SelectOption(45, "Assigned Team 5")
            ],
            'statusOptions'         : [
                new SelectOption(46, "To Do"),
                new SelectOption(47, "Working"),
                new SelectOption(48, "Active"),
                new SelectOption(49, "Closed")
            ],
            'priorityOptions'       : [
                new SelectOption(50, "Low"),
                new SelectOption(51, "Routine"),
                new SelectOption(52, "Immediate")
            ]
        ]

        def result = repository.getOptions(SelectOptionsRepository.SelectOptionsObjectType.RFI)

        expect:
        result == expectedResult
    }

    def 'getOptions returns all of the options for NOMs'() {
        given:
        def expectedResult = [
            'requirementTypeOptions': [
                new SelectOption(1, "Requirement Type 1"),
                new SelectOption(2, "Requirement Type 2"),
                new SelectOption(3, "Requirement Type 3"),
                new SelectOption(4, "Requirement Type 4"),
                new SelectOption(5, "Requirement Type 5")
            ],
            'subWorkflowOptions'    : [
                new SelectOption(6, "SubWorkFlow 1"),
                new SelectOption(7, "SubWorkFlow 2"),
                new SelectOption(8, "SubWorkFlow 3"),
                new SelectOption(9, "SubWorkFlow 4"),
                new SelectOption(10, "SubWorkFlow 5")
            ],
            'classificationOptions' : [
                new SelectOption(11, "Classification 1"),
                new SelectOption(12, "Classification 2"),
                new SelectOption(13, "Classification 3"),
                new SelectOption(14, "Classification 4"),
                new SelectOption(15, "Classification 5")
            ],
            'caveatOptions'         : [
                new SelectOption(16, "Caveat 1"),
                new SelectOption(17, "Caveat 2"),
                new SelectOption(18, "Caveat 3"),
                new SelectOption(19, "Caveat 4"),
                new SelectOption(20, "Caveat 5")
            ],
            'submittingOrgOptions'  : [
                new SelectOption(21, "Submitting Organization 1"),
                new SelectOption(22, "Submitting Organization 2"),
                new SelectOption(23, "Submitting Organization 3"),
                new SelectOption(24, "Submitting Organization 4"),
                new SelectOption(25, "Submitting Organization 5")
            ],
            'nipfCodeOptions'       : [
                new SelectOption(26, "NIPF Code 1"),
                new SelectOption(27, "NIPF Code 2"),
                new SelectOption(28, "NIPF Code 3"),
                new SelectOption(29, "NIPF Code 4"),
                new SelectOption(30, "NIPF Code 5")
            ],
            'pirNameOptions'        : [
                new SelectOption(31, "Commander PIR 1"),
                new SelectOption(32, "Commander PIR 2"),
                new SelectOption(33, "Commander PIR 3"),
                new SelectOption(34, "Commander PIR 4"),
                new SelectOption(35, "Commander PIR 5")
            ],
            'centcomIsrRoleOptions' : [
                new SelectOption(36, "CENTCOM ISR Role 1"),
                new SelectOption(37, "CENTCOM ISR Role 2"),
                new SelectOption(38, "CENTCOM ISR Role 3"),
                new SelectOption(39, "CENTCOM ISR Role 4"),
                new SelectOption(40, "CENTCOM ISR Role 5")
            ],
            'assignedTeamOptions'   : [
                new SelectOption(41, "Assigned Team 1"),
                new SelectOption(42, "Assigned Team 2"),
                new SelectOption(43, "Assigned Team 3"),
                new SelectOption(44, "Assigned Team 4"),
                new SelectOption(45, "Assigned Team 5")
            ],
            'statusOptions'         : [
                new SelectOption(55, "APPROVE"),
                new SelectOption(56, "HOLD"),
                new SelectOption(57, "REWORK"),
                new SelectOption(58, "SUBMITTED"),
                new SelectOption(59, "VOTE"),
                new SelectOption(60, "WORKING"),
                new SelectOption(61, "TEMPLATE"),
                new SelectOption(62, "FORWARD"),
                new SelectOption(63, "SEND TO NSRP"),
                new SelectOption(64, "SEND TO RMS")
            ],
            'priorityOptions'       : [
                new SelectOption(65, "IMMEDIATE"),
                new SelectOption(66, "PRIORITY"),
                new SelectOption(67, "ROUTINE")
            ],
        ]
        def result = repository.getOptions(SelectOptionsRepository.SelectOptionsObjectType.NOM)

        expect:
        result == expectedResult
    }
}
