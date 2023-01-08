package com.example.hillel_garage_v2.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class Owner {
    @Id
    private int id;
    private String name;
    private int age;
}
