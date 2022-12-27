package com.example.hillel_garage_v2.repository;

import com.example.hillel_garage_v2.model.Car;
import com.example.hillel_garage_v2.model.Owner;
import com.example.hillel_garage_v2.repository.Dao.OwnerRepository;
import com.example.hillel_garage_v2.repository.mapper.OwnerMapper;
import com.example.hillel_garage_v2.repository.query.OwnerQuery;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Repository
public class OwnerRepositoryImp implements OwnerRepository {

    private final JdbcTemplate jdbcTemplate;

    public OwnerRepositoryImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Owner owner) {
        jdbcTemplate.update(
                OwnerQuery.SAVE.getQuery()
                , owner.getName()
                , owner.getAge());
    }

    @Override
    public void update(Owner owner) {
        jdbcTemplate.update(
                OwnerQuery.UPDATE.getQuery()
                , owner.getName()
                , owner.getAge()
                , owner.getId());
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update(
                OwnerQuery.DELETE.getQuery()
                , id);
    }

    @Override
    public Owner getById(int id) {
        Owner owner = jdbcTemplate.queryForObject(
                OwnerQuery.GET_BY_ID.getQuery()
                , new OwnerMapper()
                , id);

        if (owner == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Owner with this ID not found");
        return owner;
    }

    @Override
    public Map<Owner, List<Car>> getAll() {
        return jdbcTemplate.query(
                OwnerQuery.GET_ALL.getQuery()
                , (ResultSet rows) -> {
                    HashMap<Owner, List<Car>> results = new HashMap<>();
                    while (rows.next()) {
                        Owner owner = Owner.builder()
                                .id(rows.getInt("owner_id"))
                                .name(rows.getString("owner_name"))
                                .age(rows.getInt("owner_age"))
                                .build();

                        Car car = Car.builder()
                                .id(rows.getInt("car_id"))
                                .model(rows.getString("car_model"))
                                .color(rows.getString("car_color"))
                                .ownerID(rows.getInt("owner_id"))
                                .build();

                        List<Car> cars = results.getOrDefault(owner, new LinkedList<>());
                        cars.add(car);

                        results.put(owner, cars);
                    }
                    return results;
                });
    }
}
