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
    public List<Owner> getAll() {
        return jdbcTemplate.query(
                OwnerQuery.GET_ALL.getQuery(),
                new OwnerMapper());
    }

    @Override
    public Owner findById(int id) {
        Owner owner = jdbcTemplate.queryForObject(
                OwnerQuery.GET_BY_ID.getQuery(),
                new OwnerMapper(),
                id);

        if (owner == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Owner with this ID not found");
        return owner;
    }

    @Override
    public void save(Owner owner) {
        jdbcTemplate.update(
                OwnerQuery.SAVE.getQuery(),
                owner.getName(),
                owner.getAge());
    }

    @Override
    public void update(Owner owner) {
        jdbcTemplate.update(
                OwnerQuery.UPDATE.getQuery(),
                owner.getName(),
                owner.getAge(),
                owner.getId());
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update(
                OwnerQuery.DELETE.getQuery(),
                id);
    }
}
