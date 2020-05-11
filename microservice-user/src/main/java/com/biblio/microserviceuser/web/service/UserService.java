package com.biblio.microserviceuser.web.service;

import com.biblio.microserviceuser.model.User;

public interface UserService {

    User create(User user);

    User findByEmail(String email);

    User findById(Long id);

}
