package com.biblio.microserviceuser.web.service;

import com.biblio.microserviceuser.model.User;

public interface UserService {

    User findByEmail(String email);

    void save(User registration);
}
