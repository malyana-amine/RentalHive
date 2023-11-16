package com.example.RentalHive;

import org.springframework.boot.builder.SpringApplicationBuilder;

public class ServletInitializer {

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(RentalHiveApplication.class);
    }
}
