package com.afresearchlab.stargate.persistence;

import com.afresearchlab.stargate.rfis.model.Activity;
import com.afresearchlab.stargate.rfis.model.Attachment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

@Component
public class ActivityRepository {

    private final BaseRepository baseRepository;
    private final ObjectMapper objectMapper;

    public ActivityRepository(BaseRepository repository, ObjectMapper objectMapper) {
        this.baseRepository = repository;
        this.objectMapper = objectMapper;
    }

    public Activity save(Activity activity) {
        String sql = "INSERT INTO rfi_activities (rfi_id, text, username, timestamp, attachments) VALUES (?, ?, ?, ?, ?)";
        String attachments = "[]";
        try {
            attachments = objectMapper.writeValueAsString(activity.getAttachments());
        } catch (JsonProcessingException ex) {
            throw new RuntimeException("Invalid attachment data", ex);
        }
        Long id = baseRepository.insert(sql, activity.getRfiId(), activity.getText(), activity.getUsername(), activity.getTimestamp(), attachments);
        return get(id);
    }

    public List<Activity> saveAll(List<Activity> activities) {
        if (activities.size() > 0) {
            String id = activities.get(0).getRfiId();
            activities.forEach(this::save);
            return getAllByRfiId(id);
        }
        return activities;
    }

    public Activity get(Long id) {
        String sql = "SELECT id, rfi_id, text, username, timestamp, attachments FROM rfi_activities WHERE id = ?";
        return baseRepository.select(sql, this::rowMapper, id);
    }

    public List<Activity> getAllByRfiId(String rfiId) {
        String sql = "Select id, rfi_id, text, username, timestamp, attachments FROM rfi_activities WHERE rfi_id = ?";
        List<Activity> activities = baseRepository.selectAll(sql, this::rowMapper, rfiId);
        activities.sort(Comparator.comparing(Activity::getTimestamp).reversed());
        return activities;
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM rfi_activities WHERE id = ?";
        baseRepository.delete(sql, id);
    }

    public void deleteAllByRfiId(String rfiId) {
        String sql = "DELETE FROM rfi_activities WHERE rfi_id = ?";
        baseRepository.delete(sql, rfiId);
    }

    public void deleteAll() {
        String sql = "TRUNCATE TABLE rfi_activities";
        baseRepository.deleteAll(sql);
    }

    private Activity rowMapper(ResultSet rs, int rowNom) throws SQLException {
        TypeReference<List<Attachment>> attachmentType = new TypeReference<List<Attachment>>() {
        };
        try {
            List<Attachment> attachments = this.objectMapper.readValue(rs.getString(6), attachmentType);
            return new Activity(
                rs.getLong(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                attachments
            );
        } catch (IOException e) {
            throw new RuntimeException("Invalid data in database", e);
        }

    }
}
