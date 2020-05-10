package com.biblio.microservicebatch.proxy;

import com.biblio.microservicebatch.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "microservice-user", configuration = AccountClientConfiguration.class)
public interface MicroserviceUserProxy {

    @GetMapping(value = "uaa/users/{id}")
    User getUserById(@PathVariable("id") Long id);
}
