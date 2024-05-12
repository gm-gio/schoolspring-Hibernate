package com.java.course.schoolspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "com.java.course.schoolspring")
public class SchoolSpring {
    public static void main(String[] args) {
        SpringApplication.run(SchoolSpring.class, args);
    }
}

