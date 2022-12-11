package com.belyaeva;

import com.belyaeva.model.services.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class Confectionery {
    public static void main(String[] args) {
        SpringApplication.run(Confectionery.class, args);
    }
}
