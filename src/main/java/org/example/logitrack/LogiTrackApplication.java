package org.example.logitrack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
@EnableFeignClients
@SpringBootApplication
public class LogiTrackApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogiTrackApplication.class, args);
    }

}
