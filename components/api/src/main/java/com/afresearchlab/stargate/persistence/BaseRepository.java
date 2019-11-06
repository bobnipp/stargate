package com.afresearchlab.stargate.persistence;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class BaseRepository {
    private final JdbcTemplate jdbcTemplate;
    private final GeneratedKeyHolder keyHolder;

    public DataSource getDataSource() {
        return this.jdbcTemplate.getDataSource();
    }

    public BaseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.keyHolder = new GeneratedKeyHolder();
    }

    public Long insert(String sql, Object... fields) {
        jdbcTemplate.update(preparedStatementCreator(sql, fields), keyHolder);
        return keyHolder.getKey().longValue();
    }

    public void insertNoId(String sql, Object... fields) {
        jdbcTemplate.update(preparedStatementCreator(sql, fields));
    }

    public <T> T select(String sql, RowMapper<T> mapper, Object... fields) {
        return jdbcTemplate.queryForObject(sql, fields, mapper);
    }

    public <T> List<T> selectAll(String sql, RowMapper<T> mapper, Object... fields) {
        return jdbcTemplate.query(sql, fields, mapper);
    }

    public void update(String sql, Object... fields) {
        jdbcTemplate.update(sql, fields);
    }

    public void delete(String sql, Object id) {
        jdbcTemplate.update(sql, id);
    }

    private PreparedStatementCreator preparedStatementCreator(String sql, Object... fields) {
        return connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int i = 1; i <= fields.length; i++) {
                ps.setObject(i, fields[i - 1]);
            }
            return ps;
        };
    }

    public void deleteAll(String sql) {
        jdbcTemplate.execute(sql);
    }

    public boolean healthCheck() {
        if (jdbcTemplate.queryForObject("SELECT count(*) from flyway_schema_history", int.class) > 0) {
            return true;
        }

        return false;
    }
}
