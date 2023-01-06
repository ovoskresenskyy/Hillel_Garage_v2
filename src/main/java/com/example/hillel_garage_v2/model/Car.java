package com.example.hillel_garage_v2.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Car {
    private int id;
    private String model;
    private String color;
    private int ownerID;
}
