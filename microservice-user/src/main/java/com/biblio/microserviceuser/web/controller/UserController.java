package com.biblio.microserviceuser.web.controller;

import com.biblio.microserviceuser.model.User;
import com.biblio.microserviceuser.web.exceptions.UserNotFoundException;
import com.biblio.microserviceuser.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/current")
    public Principal getUser(Principal principal) {
        return principal;
    }

    @PostMapping
    public void createUser(@Valid @RequestBody User user) {
        userService.create(user);
    }


    //    @PreAuthorize("#oauth2.hasScope('server')")
    @GetMapping(value = "/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        if (user == null) {
            throw new UserNotFoundException("Invalid userID");
        }
        return user;
    }
}
