package com.example.hillel_garage_v2.repository;

import com.example.hillel_garage_v2.model.Car;
import com.example.hillel_garage_v2.repository.Dao.CarRepository;
import com.example.hillel_garage_v2.repository.mapper.CarMapper;
import com.example.hillel_garage_v2.repository.query.CarQuery;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Repository
public class CarRepositoryImp implements CarRepository {
    private final JdbcTemplate jdbcTemplate;

    public CarRepositoryImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Car> getAllByOwnerID(int ownerID) {
        return jdbcTemplate.query(
                CarQuery.GET_ALL.getQuery(),
                new CarMapper(),
                ownerID);
    }

    @Override
    public Car findById(int id) {
        Car car = jdbcTemplate.queryForObject(
                CarQuery.GET_BY_ID.getQuery(),
                new CarMapper(),
                id);

        if (car == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car with this ID not found");
        return car;
    }

    @Override
    public void save(Car car) {
        jdbcTemplate.update(
                CarQuery.SAVE.getQuery(),
                car.getModel(),
                car.getColor(),
                car.getOwnerID());
    }

    @Override
    public void update(Car car) {
        jdbcTemplate.update(
                CarQuery.UPDATE.getQuery(),
                car.getModel(),
                car.getColor(),
                car.getId());
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update(
                CarQuery.DELETE.getQuery(),
                id);
    }
}
