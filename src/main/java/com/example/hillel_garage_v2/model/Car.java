package com.example.hillel_garage_v2.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table("car")
public class Car {
    @Id
    private int id;
    private String model;
    private String color;
    private int ownerID;
}
