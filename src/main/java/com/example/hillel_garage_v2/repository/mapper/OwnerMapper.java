package com.example.hillel_garage_v2.repository.mapper;

import com.example.hillel_garage_v2.model.Owner;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OwnerMapper implements RowMapper<Owner> {
    @Override
    public Owner mapRow(ResultSet rows, int rowNum) throws SQLException {
        return Owner.builder()
                .id(rows.getInt("id"))
                .name(rows.getString("name"))
                .age(rows.getInt("age"))
                .build();
    }
}
