package com.afresearchlab.stargate.persistence;

import com.afresearchlab.stargate.rfis.model.SelectOption;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

@Component
public class SelectOptionsRepository {

    public enum SelectOptionsObjectType {
        RFI("rfi"),
        NOM("nom");

        private String key;

        public String getKey() {
            return key;
        }

        SelectOptionsObjectType(String key) {
            this.key = key;
        }
    }

    private static final List<SelectOption> REQUIREMENT_TYPE_OPTIONS;
    private static final List<SelectOption> SUB_WORKFLOW_OPTIONS;
    private static final List<SelectOption> CLASSIFICATION_OPTIONS;
    private static final List<SelectOption> CAVEAT_OPTIONS;
    private static final List<SelectOption> SUBMITTING_ORG_OPTIONS;
    private static final List<SelectOption> NIPF_CODE_OPTIONS;
    private static final List<SelectOption> PIR_NAME_OPTIONS;
    private static final List<SelectOption> CENTCOM_ISR_ROLE_OPTIONS;
    private static final List<SelectOption> ASSIGNED_TEAM_OPTIONS;
    private static final List<SelectOption> STATUS_OPTIONS;
    private static final List<SelectOption> PRIORITY_OPTIONS;
    private static final Map<String, Map<String, List<SelectOption>>> options;

    static {
        REQUIREMENT_TYPE_OPTIONS = asList(
            new SelectOption(1, "Requirement Type 1"),
            new SelectOption(2, "Requirement Type 2"),
            new SelectOption(3, "Requirement Type 3"),
            new SelectOption(4, "Requirement Type 4"),
            new SelectOption(5, "Requirement Type 5")
        );

        SUB_WORKFLOW_OPTIONS = asList(
            new SelectOption(6, "SubWorkFlow 1"),
            new SelectOption(7, "SubWorkFlow 2"),
            new SelectOption(8, "SubWorkFlow 3"),
            new SelectOption(9, "SubWorkFlow 4"),
            new SelectOption(10, "SubWorkFlow 5")
        );

        CLASSIFICATION_OPTIONS = asList(
            new SelectOption(11, "Classification 1"),
            new SelectOption(12, "Classification 2"),
            new SelectOption(13, "Classification 3"),
            new SelectOption(14, "Classification 4"),
            new SelectOption(15, "Classification 5")
        );

        CAVEAT_OPTIONS = asList(
            new SelectOption(16, "Caveat 1"),
            new SelectOption(17, "Caveat 2"),
            new SelectOption(18, "Caveat 3"),
            new SelectOption(19, "Caveat 4"),
            new SelectOption(20, "Caveat 5")
        );

        SUBMITTING_ORG_OPTIONS = asList(
            new SelectOption(21, "Submitting Organization 1"),
            new SelectOption(22, "Submitting Organization 2"),
            new SelectOption(23, "Submitting Organization 3"),
            new SelectOption(24, "Submitting Organization 4"),
            new SelectOption(25, "Submitting Organization 5")
        );

        NIPF_CODE_OPTIONS = asList(
            new SelectOption(26, "NIPF Code 1"),
            new SelectOption(27, "NIPF Code 2"),
            new SelectOption(28, "NIPF Code 3"),
            new SelectOption(29, "NIPF Code 4"),
            new SelectOption(30, "NIPF Code 5")
        );

        PIR_NAME_OPTIONS = asList(
            new SelectOption(31, "Commander PIR 1"),
            new SelectOption(32, "Commander PIR 2"),
            new SelectOption(33, "Commander PIR 3"),
            new SelectOption(34, "Commander PIR 4"),
            new SelectOption(35, "Commander PIR 5")
        );

        CENTCOM_ISR_ROLE_OPTIONS = asList(
            new SelectOption(36, "CENTCOM ISR Role 1"),
            new SelectOption(37, "CENTCOM ISR Role 2"),
            new SelectOption(38, "CENTCOM ISR Role 3"),
            new SelectOption(39, "CENTCOM ISR Role 4"),
            new SelectOption(40, "CENTCOM ISR Role 5")
        );

        ASSIGNED_TEAM_OPTIONS = asList(
            new SelectOption(41, "Assigned Team 1"),
            new SelectOption(42, "Assigned Team 2"),
            new SelectOption(43, "Assigned Team 3"),
            new SelectOption(44, "Assigned Team 4"),
            new SelectOption(45, "Assigned Team 5")
        );

        STATUS_OPTIONS = asList(
            new SelectOption(46, "To Do"),
            new SelectOption(47, "Working"),
            new SelectOption(48, "Active"),
            new SelectOption(49, "Closed")
        );

        PRIORITY_OPTIONS = asList(
            new SelectOption(50, "Low"),
            new SelectOption(51, "Routine"),
            new SelectOption(52, "Immediate")
        );

        options = new HashMap<>();
        options.put(SelectOptionsObjectType.RFI.getKey(), buildImmRecordOptions());
        options.put(SelectOptionsObjectType.NOM.getKey(), buildPrismRecordOptions());
    }

    private static Map<String, List<SelectOption>> buildImmRecordOptions() {
        Map<String, List<SelectOption>> options = new HashMap<>();
        options.put("requirementTypeOptions", REQUIREMENT_TYPE_OPTIONS);
        options.put("subWorkflowOptions", SUB_WORKFLOW_OPTIONS);
        options.put("classificationOptions", CLASSIFICATION_OPTIONS);
        options.put("caveatOptions", CAVEAT_OPTIONS);
        options.put("submittingOrgOptions", SUBMITTING_ORG_OPTIONS);
        options.put("nipfCodeOptions", NIPF_CODE_OPTIONS);
        options.put("pirNameOptions", PIR_NAME_OPTIONS);
        options.put("centcomIsrRoleOptions", CENTCOM_ISR_ROLE_OPTIONS);
        options.put("assignedTeamOptions", ASSIGNED_TEAM_OPTIONS);
        options.put("statusOptions", STATUS_OPTIONS);
        options.put("priorityOptions", PRIORITY_OPTIONS);
        return options;
    }

    private static Map<String, List<SelectOption>> buildPrismRecordOptions() {
        Map<String, List<SelectOption>> options = new HashMap<>();
        options.put("requirementTypeOptions", REQUIREMENT_TYPE_OPTIONS);
        options.put("subWorkflowOptions", SUB_WORKFLOW_OPTIONS);
        options.put("classificationOptions", CLASSIFICATION_OPTIONS);
        options.put("caveatOptions", CAVEAT_OPTIONS);
        options.put("submittingOrgOptions", SUBMITTING_ORG_OPTIONS);
        options.put("nipfCodeOptions", NIPF_CODE_OPTIONS);
        options.put("pirNameOptions", PIR_NAME_OPTIONS);
        options.put("centcomIsrRoleOptions", CENTCOM_ISR_ROLE_OPTIONS);
        options.put("assignedTeamOptions", ASSIGNED_TEAM_OPTIONS);
        options.put("statusOptions", asList(
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
        ));
        options.put("priorityOptions", asList(
                new SelectOption(65, "IMMEDIATE"),
                new SelectOption(66, "PRIORITY"),
                new SelectOption(67, "ROUTINE")
        ));
        return options;
    }

    public Map<String, List<SelectOption>> getOptions(SelectOptionsObjectType type) {
        return options.get(type.getKey());
    }
}
