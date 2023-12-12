package com.example.Application2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Application2Application {

    public static void main(String[] args) {
        SpringApplication.run(Application2Application.class, args);
    }

}
