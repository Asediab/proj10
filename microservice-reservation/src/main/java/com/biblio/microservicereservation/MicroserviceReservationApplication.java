package com.biblio.microservicereservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@SpringBootApplication
@EnableDiscoveryClient
@EnableOAuth2Client
@EnableFeignClients("com.biblio.microservicereservation.proxy")
public class MicroserviceReservationApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceReservationApplication.class, args);
    }

}
