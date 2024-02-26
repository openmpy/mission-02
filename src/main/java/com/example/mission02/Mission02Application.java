package com.example.mission02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Mission02Application {

    public static void main(String[] args) {
        SpringApplication.run(Mission02Application.class, args);
    }

}
