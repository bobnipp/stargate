package com.afresearchlab.stargate.lists;

import com.afresearchlab.stargate.persistence.BaseRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ListsRepository {

    private final BaseRepository baseRepository;
    private final ObjectMapper objectMapper;

    public ListsRepository(BaseRepository repository, ObjectMapper objectMapper) {
        this.baseRepository = repository;
        this.objectMapper = objectMapper;
    }

    public List<CustomList> getLists() {
        String sql = "SELECT name, records FROM lists";
        return baseRepository.selectAll(sql, this::rowMapper);
    }

    public void createList(CustomList request) {
        String sql = "INSERT INTO lists (name, records) VALUES (?, JSON_ARRAY())";
        baseRepository.insertNoId(sql, request.getName());
    }

    public void updateList(CustomList request) throws JsonProcessingException {
        String sql = "UPDATE lists SET records = ? WHERE name = (?)";
        String newRecords = objectMapper.writeValueAsString(request.getRecords());
        baseRepository.update(sql, newRecords, request.getName());
    }

    public void addRecordToList(String listName, RecordIdentifier request) throws JsonProcessingException {
        String sql = "UPDATE lists SET records = JSON_MERGE(records, (?)) WHERE lists.name = (?) AND NOT JSON_CONTAINS(lists.records, (?));";
        String newRecord = objectMapper.writeValueAsString(request);
        baseRepository.update(sql, newRecord, listName, newRecord);
    }

    public void removeRecordFromAllLists(RecordIdentifier recordIdentifier) {
        String jsonContains = "{\"recordId\": \"" + recordIdentifier.getRecordId() + "\",\"recordType\":\"" + recordIdentifier.getRecordType() + "\"}";
        String sql = "UPDATE lists SET records = " +
            "JSON_REMOVE(records, " +
            "SUBSTRING(JSON_SEARCH(records, 'all', (?), NULL, '$[*].recordId'),2,POSITION(']' " +
            "IN JSON_SEARCH(records, 'all', (?), NULL, '$[*].recordId'))-1)) " +
            "WHERE JSON_CONTAINS(records, (?))";
        baseRepository.update(sql, recordIdentifier.getRecordId(), recordIdentifier.getRecordId(), jsonContains);
    }

    public void removeRecordFromList(String listName, RecordIdentifier recordIdentifier) {
        String jsonContains = "{\"recordId\": \"" + recordIdentifier.getRecordId() + "\",\"recordType\":\"" + recordIdentifier.getRecordType() + "\"}";
        String sql = "UPDATE lists SET records = " +
            "JSON_REMOVE(records, " +
            "SUBSTRING(JSON_SEARCH(records, 'all', (?), NULL, '$[*].recordId'),2,POSITION(']' " +
            "IN JSON_SEARCH(records, 'all', (?), NULL, '$[*].recordId'))-1)) " +
            "WHERE JSON_CONTAINS(records, (?)) AND name=(?)";
        baseRepository.update(sql, recordIdentifier.getRecordId(), recordIdentifier.getRecordId(), jsonContains, listName);
    }

    public void deleteList(String name) {
        String sql = "DELETE FROM lists WHERE name = ?";
        baseRepository.delete(sql, name);
    }

    public void deleteAll() {
        String sql = "TRUNCATE TABLE lists";
        baseRepository.deleteAll(sql);
    }

    private CustomList rowMapper(ResultSet rs, int i) throws SQLException {
        TypeReference<List<RecordIdentifier>> recordIdType = new TypeReference<List<RecordIdentifier>>() {};
        try {
            List<RecordIdentifier> records = this.objectMapper.readValue(rs.getString(2), recordIdType);
            return new CustomList(
                    rs.getString(1),
                    records
            );
        } catch (IOException e) {
            throw new RuntimeException("Invalid data in database", e);
        }
    }

}

