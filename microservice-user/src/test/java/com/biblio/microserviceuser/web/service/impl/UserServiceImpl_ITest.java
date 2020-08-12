package com.biblio.microserviceuser.web.service.impl;


import com.biblio.microserviceuser.DTO.UserDTO;
import com.biblio.microserviceuser.model.Role;
import com.biblio.microserviceuser.model.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;


@SpringBootTest
class UserServiceImpl_ITest {

    @Autowired
    UserServiceImpl userServiceImpl;

    User user = new User();

    @BeforeEach
     void start() {
        user.setId(1L);
        user.setSurname("User");
        user.setEmail("ocr.proj07@yandex.ru");
        user.setName("User");
        user.setPassword("$2a$10$a3w89n.b/aRcUYPwdPlO8.89WhanqcUYmDssnr0BgIQ98cK9bxN0q");
        user.setRoles(Collections.singleton(Role.USER));
    }

    @Test
    @Tag("findByEmail")
    @DisplayName("Test findByEmail")
    void findByEmail() {
        try {
            User user1 = userServiceImpl.findByEmail("ocr.proj07@yandex.ru");
            Assertions.assertNotNull(user1);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Test
    @Tag("findById")
    @DisplayName("Test findById")
    void findByID() {
        try {
            User user1 = userServiceImpl.findById(1L);
            Assertions.assertNotNull(user1);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Test
    @Tag("create")
    @DisplayName("Test Create new User")
    void create() {
        UserDTO userSave = new UserDTO();
        userSave.setSurname("Surname");
        userSave.setEmail("email@email.com");
        userSave.setName("Name");
        userSave.setPassword("password");
        try {
            User user1 = userServiceImpl.create(userSave);
            Assertions.assertNotNull(user1);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}