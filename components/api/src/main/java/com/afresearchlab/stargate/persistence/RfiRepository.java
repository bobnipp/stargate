package com.afresearchlab.stargate.persistence;

import com.afresearchlab.stargate.rfis.model.Rfi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class RfiRepository {

    private final BaseRepository baseRepository;
    ObjectMapper mapper = new ObjectMapper();

    public RfiRepository(BaseRepository repository) {
        this.baseRepository = repository;
    }

    public Rfi save(Rfi rfi) {
        String serializedEeis = serializeEeis(rfi.getEeis());

        String sql = "INSERT INTO imm_rfis (title, country, coordinates, city, region, nai, priority, status, justification, prev_research, requirement_type_id, sub_workflow_id, classification_id, custom_classification, caveat_id, submitting_org_id, poc, nipf_code_id, pir_name_id, centcom_isr_role_id, operation, collection_start_date, collection_end_date, collection_term, collection_type, assigned_team_id, assignee, collection_guidance, eeis) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Long id = baseRepository.insert(sql, rfi.getTitle(), rfi.getCountry(), rfi.getCoordinates(), rfi.getCity(), rfi.getRegion(), rfi.getNai(), rfi.getPriority(), rfi.getStatus(), rfi.getJustification(), rfi.getPrevResearch(), rfi.getRequirementTypeId(), rfi.getSubWorkflowId(), rfi.getClassificationId(), rfi.getCustomClassification(), rfi.getCaveatId(), rfi.getSubmittingOrgId(), rfi.getPoc(), rfi.getNipfCodeId(), rfi.getPirNameId(), rfi.getCentcomIsrRoleId(), rfi.getOperation(), rfi.getCollectionStartDate(), rfi.getCollectionEndDate(), rfi.getCollectionTerm(), rfi.getCollectionType(), rfi.getAssignedTeamId(), rfi.getAssignee(), rfi.getCollectionGuidance(), serializedEeis);
        return get(id);
    }

    public Rfi get(Long id) {
        String sql = "SELECT id, title, country, coordinates, city, region, nai, priority, status, justification, prev_research, requirement_type_id, sub_workflow_id, classification_id, custom_classification, caveat_id, submitting_org_id, poc, nipf_code_id, pir_name_id, centcom_isr_role_id, operation, collection_start_date, collection_end_date, collection_term, collection_type, assigned_team_id, assignee, collection_guidance, eeis, created_on FROM imm_rfis WHERE id = ?";
        try {
            return baseRepository.select(sql, this::rowMapper, id);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;  // invalid link
        }
    }

    public List<Rfi> getAll(String ordering, boolean reverse) {
        String sql = "SELECT id, title, country, coordinates, city, region, nai, priority, status, justification, prev_research, requirement_type_id, sub_workflow_id, classification_id, custom_classification, caveat_id, submitting_org_id, poc, nipf_code_id, pir_name_id, centcom_isr_role_id, operation, collection_start_date, collection_end_date, collection_term, collection_type, assigned_team_id, assignee, collection_guidance, eeis, created_on FROM imm_rfis ORDER BY " + ordering + (reverse ? " DESC" : "");
        return baseRepository.selectAll(sql, this::rowMapper);
    }

    public List<Rfi> getAll() {
        return getAll("id", true);
    }

    public void update(Rfi rfi) {
        String serializedEeis = serializeEeis(rfi.getEeis());

        String sql = "UPDATE imm_rfis " +
            "SET title = ?, country = ?, coordinates = ?, " +
            "city = ?, region = ?, nai = ?, " +
            "priority = ?, status = ?, justification = ?, prev_research = ?, requirement_type_id = ?, " +
            "sub_workflow_id = ?, classification_id = ?, custom_classification = ?, caveat_id = ?, " +
            "submitting_org_id = ?, poc = ?, nipf_code_id = ?, pir_name_id = ?, " +
            "centcom_isr_role_id = ?, operation = ?, collection_start_date = ?, collection_end_date = ?, " +
            "collection_term = ?, collection_type = ?, assigned_team_id = ?, assignee = ?, collection_guidance = ?, " +
            "eeis = ? " +
            "WHERE id = ?";
        baseRepository.update(sql, rfi.getTitle(), rfi.getCountry(), rfi.getCoordinates(), rfi.getCity(),
                rfi.getRegion(), rfi.getNai(), rfi.getPriority(), rfi.getStatus(), rfi.getJustification(), rfi.getPrevResearch(), rfi.getRequirementTypeId(), rfi.getSubWorkflowId(), rfi.getClassificationId(), rfi.getCustomClassification(), rfi.getCaveatId(), rfi.getSubmittingOrgId(), rfi.getPoc(), rfi.getNipfCodeId(), rfi.getPirNameId(), rfi.getCentcomIsrRoleId(), rfi.getOperation(), rfi.getCollectionStartDate(), rfi.getCollectionEndDate(), rfi.getCollectionTerm(), rfi.getCollectionType(), rfi.getAssignedTeamId(), rfi.getAssignee(), rfi.getCollectionGuidance(), serializedEeis, rfi.getId());

    }

    public void delete(Long id) {
        baseRepository.delete("DELETE FROM imm_rfis WHERE id=?", id);
    }

    private String serializeEeis(List<String> eeis) {
        try {
            return eeis == null ? null : mapper.writeValueAsString(eeis);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private Rfi rowMapper(ResultSet rs, int rowNum) throws SQLException {
        List<String> deserializedEeis;
        try {
            deserializedEeis = rs.getString(30) == null ? null : mapper.readValue(rs.getString(30), new TypeReference<List<String>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new Rfi(rs.getLong(1),
            rs.getString(2),
            rs.getString(3),
            rs.getString(4),
            rs.getString(5),
            rs.getString(6),
            rs.getString(7),
            rs.getString(8),
            rs.getString(9),
            rs.getString(10),
            rs.getString(11),
            getInteger(rs.getInt(12)),
            getInteger(rs.getInt(13)),
            getInteger(rs.getInt(14)),
            rs.getString(15),
            getInteger(rs.getInt(16)),
            getInteger(rs.getInt(17)),
            rs.getString(18),
            getInteger(rs.getInt(19)),
            getInteger(rs.getInt(20)),
            getInteger(rs.getInt(21)),
            rs.getString(22),
            rs.getString(23),
            rs.getString(24),
            rs.getString(25),
            rs.getString(26),
            getInteger(rs.getInt(27)),
            rs.getString(28),
            rs.getString(29),
            deserializedEeis,
            null,
            null,
            null,
            null,
            null,
            rs.getString(31)
        );
    }

    private Double getDouble(BigDecimal value) {
        if (value == null) {
            return null;
        }
        return value.doubleValue();
    }

    private Integer getInteger(Integer value) {
        if (value == 0) {
            return null;
        }
        return value;
    }

    public void deleteAll() {
        String sql = "DELETE FROM imm_rfis";
        baseRepository.deleteAll(sql);
    }

    public boolean healthCheck() {
        return baseRepository.healthCheck();
    }
}