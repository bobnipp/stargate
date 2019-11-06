package com.afresearchlab.stargate.persistence;

import com.afresearchlab.stargate.rfis.model.Link;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class LinkRepository {

    private final BaseRepository baseRepository;

    public LinkRepository(BaseRepository repository) {
        this.baseRepository = repository;
    }

    public void save(Link link) {
        String sql = "INSERT INTO imm_links (record_1_id, record_2_id, record_1_system, record_2_system) VALUES (?, ?, ?, ?)";
        baseRepository.insertNoId(sql, link.getRecord1Id(), link.getRecord2Id(), link.getRecord1System(), link.getRecord2System());
    }

    public List<Link> getAllByRecordId(String recordId) {
        String sql = "SELECT record_1_id, record_2_id, record_1_system, record_2_system FROM imm_links WHERE record_1_id = ? OR record_2_id = ? ORDER BY id";
        return baseRepository.selectAll(sql, this::rowMapper, recordId, recordId);
    }

    public List<Link> saveAll(List<Link> links, String recordId) {
        if (links.size() > 0) {
            links.forEach(this::save);
            return getAllByRecordId(recordId);
        }
        return links;
    }

    public void deleteAllByRecordId(String recordId) {
        String sql = "DELETE FROM imm_links WHERE record_1_id = ? OR record_2_id = ?";
        baseRepository.update(sql, recordId, recordId);
    }

    public void deleteAll() {
        String sql = "TRUNCATE TABLE imm_links";
        baseRepository.deleteAll(sql);
    }

    private Link rowMapper(ResultSet rs, int rowNum) throws SQLException {
        return new Link(
            rs.getString(1),
            rs.getString(2),
            rs.getString(3),
            rs.getString(4),
            null,
            null
        );
    }

    public void delete(String record1Id, String record2Id, String record1System, String record2System) {
        String sql = "DELETE FROM imm_links WHERE record_1_id = ? AND record_2_id = ? AND record_1_system = ? AND record_2_system = ?";
        baseRepository.update(sql, record1Id, record2Id, record1System, record2System);
    }
}