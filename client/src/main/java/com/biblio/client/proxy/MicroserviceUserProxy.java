package com.biblio.client.proxy;

import com.biblio.client.DTO.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.security.Principal;

//@FeignClient(name = "zuul-server")
//@RibbonClient(name = "microservice-user")


@FeignClient(name = "microservice-user", configuration = AccountClientConfiguration.class)
public interface MicroserviceUserProxy {

    @RequestMapping(value = "/uaa/users/current", method = RequestMethod.GET)
    Principal getUser(Principal principal);

    @RequestMapping(value = "/uaa/users", method = RequestMethod.POST)
    void createUser(@Valid @RequestBody UserDTO user);
}
