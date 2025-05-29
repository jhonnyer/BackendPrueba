package com.comercio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching 
public class ComercioApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComercioApiApplication.class, args);
    }
}
