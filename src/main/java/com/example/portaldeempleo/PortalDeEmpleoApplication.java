package com.example.portaldeempleo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PortalDeEmpleoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PortalDeEmpleoApplication.class, args);
    }

}
