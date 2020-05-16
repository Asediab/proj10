package com.biblio.microserviceuser.web.service.impl;

import com.biblio.microserviceuser.DAO.UserDAO;
import com.biblio.microserviceuser.DTO.UserDTO;
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
    public User create(UserDTO user) {

        if (repository.findByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("User already exists: " + user.getEmail());
        }
        User userSave = new User();
        userSave.setName(user.getName());
        userSave.setSurname(user.getSurname());
        userSave.setEmail(user.getEmail());
        userSave.setPassword(encoder.encode(user.getPassword()));
        userSave.setRoles(Collections.singleton(Role.USER));

        return repository.save(userSave);
    }

    @Override
    public User findById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
