package com.afresearchlab.stargate.filter;

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
public class FiltersRepository {

    private final BaseRepository baseRepository;
    private final ObjectMapper objectMapper;

    public FiltersRepository(BaseRepository repository, ObjectMapper objectMapper) {
        this.baseRepository = repository;
        this.objectMapper = objectMapper;
    }

    public Filters getFiltersByUser(String user) {
        String sql = "SELECT filters FROM user_data where user = ?";
        List<Filters> filtersList = baseRepository.selectAll(sql, this::rowMapper, user);
        if (filtersList.size() == 1) {
            return filtersList.get(0);
        }

        return null;
    }

    public void saveFiltersByUser(String user, Filters request) throws JsonProcessingException {
        String sql = "REPLACE INTO user_data (user, filters) VALUES (?, ?)";
        baseRepository.update(sql, user, objectMapper.writeValueAsString(request));
    }

    private Filters rowMapper(ResultSet rs, int i) throws SQLException {
        TypeReference<Filters> filterType = new TypeReference<Filters>() {};
        try {
            return this.objectMapper.readValue(rs.getString(1), filterType);
        } catch (IOException e) {
            throw new RuntimeException("Invalid data in database", e);
        }
    }

}

