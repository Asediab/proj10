package com.biblio.microserviceuser.web.controller;

import com.biblio.microserviceuser.model.User;
import com.biblio.microserviceuser.web.exceptions.UserNotFoundException;
import com.biblio.microserviceuser.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/current")
    public Principal getUser(@AuthenticationPrincipal Principal principal) {
        return principal;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@Valid @RequestBody User user) {
        User userSave = userService.create(user);
        if (userSave == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().build();
    }


    @GetMapping(value = "/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        if (user == null) {
            throw new UserNotFoundException("Invalid userID");
        }
        return user;
    }
}
