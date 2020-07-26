package com.biblio.microserviceuser.web.service.impl;

import com.biblio.microserviceuser.DAO.UserDAO;
import com.biblio.microserviceuser.DTO.UserDTO;
import com.biblio.microserviceuser.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.Mockito.*;

class UserServiceImplTest {
    @Mock
    BCryptPasswordEncoder encoder;
    @Mock
    UserDAO repository;
    @InjectMocks
    UserServiceImpl userServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindByEmail() {
        when(repository.findByEmail(anyString())).thenReturn(new User());

        User result = userServiceImpl.findByEmail("email");
        Assertions.assertEquals(new User(), result);
    }

    @Test
    void testCreate() {
        when(repository.findByEmail(anyString())).thenReturn(new User());

        User result = userServiceImpl.create(new UserDTO());
        Assertions.assertEquals(new User(), result);
    }

    @Test
    void testFindById() {
        User result = userServiceImpl.findById(Long.valueOf(1));
        Assertions.assertEquals(new User(), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme