package com.biblio.client.proxy;

import com.biblio.client.DTO.UserRegister;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.security.Principal;


@FeignClient(name = "microservice-user", fallback = MicroserviceUserProxy.UserFallback.class)
public interface MicroserviceUserProxy {

    @RequestMapping(value = "/uaa/users/current", method = RequestMethod.GET)
    Principal getUser(Principal principal);

    @RequestMapping(value = "/uaa/users", method = RequestMethod.POST)
    void createUser(@Valid @RequestBody UserRegister user);


    @Component
    class UserFallback implements MicroserviceUserProxy {

        @Override
        public Principal getUser(Principal principal) {
            return null;
        }

        @Override
        public void createUser(@Valid UserRegister user) {
        }
    }
}
