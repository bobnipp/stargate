package com.afresearchlab.stargate.persistence;

import com.afresearchlab.stargate.rfis.model.Sensor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class SensorRepository {

    private final BaseRepository baseRepository;

    public SensorRepository(BaseRepository repository) {
        this.baseRepository = repository;
    }

    public Sensor get(Long id) {
        String sql = "SELECT id, imm_rfi_id, sensor, sensor_type, mode, desired_quality, required_quality FROM sensors WHERE id = ?";
        return baseRepository.select(sql, this::rowMapper, id);
    }

    public Sensor save(Sensor sensor) {
        String sql = "INSERT INTO sensors (imm_rfi_id, sensor, sensor_type, mode, desired_quality, required_quality) VALUES (?, ?, ?, ?, ?, ?)";
        Long id = baseRepository.insert(sql, sensor.getRfiId(), sensor.getSensor(), sensor.getSensorType(), sensor.getMode(), sensor.getDesiredQuality(), sensor.getRequiredQuality());
        return get(id);
    }

    public List<Sensor> saveAll(List<Sensor> sensors) {
        if (sensors.size() > 0) {
            Long id = sensors.get(0).getRfiId();
            sensors.forEach(this::save);
            return getAll(id);
        }
        return sensors;
    }

    public List<Sensor> getAll(Long rfiId) {
        String sql = "SELECT id, imm_rfi_id, sensor, sensor_type, mode, desired_quality, required_quality FROM sensors WHERE imm_rfi_id = ?";
        return baseRepository.selectAll(sql, this::rowMapper, rfiId);
    }

    public void deleteAllByRfiId(Long rfiid) {
        String sql = "DELETE FROM sensors WHERE imm_rfi_id = ?";
        baseRepository.delete(sql, rfiid);
    }

    public void deleteById(Long sensorId) {
        String sql = "DELETE FROM sensors WHERE id = ?";
        baseRepository.delete(sql, sensorId);
    }

    public void deleteAll() {
        String sql = "TRUNCATE TABLE sensors";
        baseRepository.deleteAll(sql);
    }

    private Sensor rowMapper(ResultSet rs, int rowNum) throws SQLException {
        return new Sensor(
            rs.getLong(1),
            rs.getLong(2),
            rs.getString(3),
            rs.getString(4),
            rs.getString(5),
            rs.getInt(6),
            rs.getInt(7)
        );
    }
}
