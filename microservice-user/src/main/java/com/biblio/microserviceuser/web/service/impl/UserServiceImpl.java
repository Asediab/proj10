package com.biblio.microserviceuser.web.service.impl;

import com.biblio.microserviceuser.DAO.UserDAO;
import com.biblio.microserviceuser.DTO.UserRegistrationDto;
import com.biblio.microserviceuser.model.Role;
import com.biblio.microserviceuser.model.User;
import com.biblio.microserviceuser.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    public User save(UserRegistrationDto registration) {
        User user = new User();
        user.setName(registration.getName());
        user.setSurname(registration.getSurname());
        user.setEmail(registration.getEmail());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        user.setRoles(Collections.singleton(Role.USER));
        return userDAO.save(user);
    }
}
