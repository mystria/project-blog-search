package com.mys.projectblogsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {

        System.setProperty("spring.config.name", "application,web,domain,infra");
        SpringApplication.run(Main.class, args);
    }

}
