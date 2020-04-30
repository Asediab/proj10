package com.biblio.microserviceloan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableDiscoveryClient
public class MicroserviceLoanApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceLoanApplication.class, args);
    }

}
