package com.example.hillel_garage_v2.repository;

import com.example.hillel_garage_v2.model.Owner;
import com.example.hillel_garage_v2.repository.query.OwnerQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
public class OwnerRepositoryImpTest {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void findByIdTest() {

        Owner owner = Owner.builder()
                .id(7)
                .name("Alex333")
                .age(55)
                .build();

        OwnerRepositoryImp ownerRepositoryImp = new OwnerRepositoryImp(jdbcTemplate);

        Mockito.when(jdbcTemplate.queryForObject(OwnerQuery.GET_BY_ID.getQuery(), Owner.class)).thenReturn(owner);
        Owner expectedOwner = ownerRepositoryImp.findById(99);
        Assertions.assertEquals(owner, expectedOwner);
    }
}
