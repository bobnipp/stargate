package com.afresearchlab.stargate.persistence;

import com.afresearchlab.prismadaptermodels.RecordHistory;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class HistoryRepository {

    private final BaseRepository baseRepository;

    public HistoryRepository(BaseRepository repository) {
        this.baseRepository = repository;
    }

    public void save(String username, String action, Long rfiId) {
        baseRepository.insertNoId("INSERT INTO record_history (username, action, rfi_id) VALUES (?, ?, ?)", username, action, rfiId);
    }

    public List<RecordHistory> getAllByRfiId(Long rfiId) {
        return baseRepository.selectAll("SELECT username, action, date FROM record_history WHERE rfi_id=? ORDER BY date DESC", this::rowMapper, rfiId);
    }

    public void deleteAllByRfiId(Long rfiId) {
        baseRepository.delete("DELETE from record_history WHERE rfi_id=?", rfiId);
    }

    public void deleteAll() {
        String sql = "TRUNCATE TABLE record_history";
        baseRepository.deleteAll(sql);
    }

    private RecordHistory rowMapper(ResultSet resultSet, int i) throws SQLException {
        return new RecordHistory(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
    }
}
