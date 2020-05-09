package com.biblio.client.service;

import com.biblio.client.DTO.UserDTO;

import java.security.Principal;

public interface UserService {

    Principal getUser(Principal principal);

    void createUser(UserDTO user);
}
