package com.biblio.microserviceuser.web.service.impl;

import com.biblio.microserviceuser.DAO.UserDAO;
import com.biblio.microserviceuser.model.Role;
import com.biblio.microserviceuser.model.User;
import com.biblio.microserviceuser.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {


    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private UserDAO repository;

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public User create(User user) {

        User existing = repository.findByEmail(user.getEmail());
        if (existing != null) {
            throw new IllegalArgumentException("User already exists: " + user.getEmail());
        }

        String hash = encoder.encode(user.getPassword());
        user.setPassword(hash);
        user.setRoles(Collections.singleton(Role.USER));

        return repository.save(user);
    }

    @Override
    public User findById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
