package com.afresearchlab.stargate.users;

import com.afresearchlab.stargate.persistence.BaseRepository;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepository {

    private final BaseRepository baseRepo;

    public UserRepository(BaseRepository baseRepo) {
        this.baseRepo = baseRepo;
    }

    public UserDTO save(final UserDTO userDTO) {
        String sql = "INSERT INTO users (username, first_name, last_name, email, notes) VALUES (?, ?, ?, ?, ?)";
        Long id = baseRepo.insert(sql, userDTO.getUsername(), userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail(), userDTO.getNotes());
        return getUserById(id.intValue());
    }

    public UserDTO getUserById(Integer userId) {
        String sql = "SELECT id, username, first_name, last_name, email, notes from users where id=?";
        return baseRepo.select(sql, this::rowMapper, userId);
    }

    public UserDTO getUserByUsername(String username) {
        String sql = "SELECT id, username, first_name, last_name, email, notes from users where username=?";
        return baseRepo.select(sql, this::rowMapper, username);
    }

    public List<UserDTO> getUsers() {
        String sql = "SELECT id, username, first_name, last_name, email, notes from users";
        return baseRepo.selectAll(sql, this::rowMapper);
    }

    private UserDTO rowMapper(ResultSet rs, int rowNum) throws SQLException {
        return new UserDTO(rs.getInt(1),
            rs.getString(2),
            rs.getString(3),
            rs.getString(4),
            rs.getString(5),
            rs.getString(6));
    }
}
