package com.drivelab.autocenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class AutocenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutocenterApplication.class, args);
    }

}
