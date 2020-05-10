package com.biblio.microservicedocument;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;


@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
        org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class})
@EnableDiscoveryClient
@EnableOAuth2Client
public class MicroserviceDocumentApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceDocumentApplication.class, args);
    }

}
