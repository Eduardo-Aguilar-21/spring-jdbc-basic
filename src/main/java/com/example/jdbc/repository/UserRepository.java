package com.example.jdbc.repository;

import com.example.jdbc.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<UserModel> listUsers(){
        String sql = "SELECT * FROM USERS";

        RowMapper<UserModel> rowMapper = new RowMapper<UserModel>(){
            @Override
            public UserModel mapRow(ResultSet rs, int numRows) throws SQLException{
                return new UserModel(
                        rs.getLong("ID"),
                        rs.getString("NAME"),
                        rs.getString("EMAIL")
                );
            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }

}

