package com.sparta.assignment01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Assignment01Application {

    public static void main(String[] args) {
        SpringApplication.run(Assignment01Application.class, args);
    }

}
