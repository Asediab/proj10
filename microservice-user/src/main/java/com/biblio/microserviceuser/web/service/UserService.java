package com.biblio.microserviceuser.web.service;

import com.biblio.microserviceuser.DTO.UserDTO;
import com.biblio.microserviceuser.model.User;

public interface UserService {

    User create(UserDTO user);

    User findByEmail(String email);

    User findById(Long id);

}
