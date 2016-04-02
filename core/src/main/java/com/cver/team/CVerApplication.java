package com.cver.team;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by Dimitar on 4/2/2016.
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableWebMvc
public class CVerApplication extends WebMvcAutoConfiguration {
    public static void main(String args[]) {
        SpringApplication.run(CVerApplication.class);
    }
}
