package com.example.hillel_garage_v2.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Owner {
    private int id;
    private String name;
    private int age;
}
