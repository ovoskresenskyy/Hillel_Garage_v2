package com.example.hillel_garage_v2.repository.mapper;

import com.example.hillel_garage_v2.model.Car;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarMapper implements RowMapper<Car> {

    @Override
    public Car mapRow(ResultSet rows, int rowNum) throws SQLException {
        return Car.builder()
                .id(rows.getInt("id"))
                .model(rows.getString("model"))
                .color(rows.getString("color"))
                .ownerID(rows.getInt("owner_id"))
                .build();
    }
}
