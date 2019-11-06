package com.afresearchlab.stargate.persistence;

import com.afresearchlab.stargate.rfis.model.Target;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

@Component
public class TargetRepository {

    private final BaseRepository baseRepository;

    public TargetRepository(BaseRepository repository) {
        this.baseRepository = repository;
    }

    public Target get(Long id) {
        String sql = "SELECT id, imm_rfi_id, name, target_type, radius, radius_unit, coordinates FROM targets WHERE id = ?";
        return baseRepository.select(sql, this::rowMapper, id);
    }

    public Target save(Target target) {
        String sql = "INSERT INTO targets (imm_rfi_id, name, target_type, radius, radius_unit, coordinates) VALUES (?, ?, ?, ?, ?, ?)";
        Long id = baseRepository.insert(sql, target.getRfiId(), target.getName(), target.getType(), target.getRadius(), target.getRadiusUnit(), target.getCoordinates());
        return get(id);
    }

    public List<Target> saveAll(List<Target> targets) {
        if (targets.size() > 0) {
            Long id = targets.get(0).getRfiId();
            targets.forEach(this::save);
            return getAll(id);
        }
        return targets;
    }

    public List<Target> getAll(Long rfiId) {
        String sql = "SELECT id, imm_rfi_id, name, target_type, radius, radius_unit, coordinates FROM targets WHERE imm_rfi_id = ?";
        return baseRepository.selectAll(sql, this::rowMapper, rfiId);
    }

    public void deleteAllByRfiId(Long rfiid) {
        String sql = "DELETE FROM targets WHERE imm_rfi_id = ?";
        baseRepository.delete(sql, rfiid);
    }

    public void deleteById(Long targetId) {
        String sql = "DELETE FROM targets WHERE id = ?";
        baseRepository.delete(sql, targetId);
    }

    public void deleteAll() {
        String sql = "TRUNCATE TABLE targets";
        baseRepository.deleteAll(sql);
    }

    private Target rowMapper(ResultSet rs, int rowNum) throws SQLException {
        return new Target(
            rs.getLong(1),
            rs.getLong(2),
            rs.getString(3),
            rs.getString(4),
            rs.getFloat(5),
            rs.getString(6),
            rs.getString(7)
        );
    }
}
