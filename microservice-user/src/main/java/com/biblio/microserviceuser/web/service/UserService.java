package com.biblio.microserviceuser.web.service;

import com.biblio.microserviceuser.DTO.UserRegistrationDto;
import com.biblio.microserviceuser.model.User;

public interface UserService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);
}
