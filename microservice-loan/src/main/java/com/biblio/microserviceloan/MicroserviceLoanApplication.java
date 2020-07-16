package com.biblio.microserviceloan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;


@SpringBootApplication
@EnableDiscoveryClient
@EnableOAuth2Client
@EnableFeignClients("com.biblio.microserviceloan.proxy")
public class MicroserviceLoanApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceLoanApplication.class, args);
    }

}
