package com.example.hillel_garage_v2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class HillelGarageV2Application {

    public static void main(String[] args) {
        SpringApplication.run(HillelGarageV2Application.class, args);
    }

}
