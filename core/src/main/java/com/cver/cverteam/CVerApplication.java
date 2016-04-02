package com.cver.cverteam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class CVerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CVerApplication.class, args);
    }
}
