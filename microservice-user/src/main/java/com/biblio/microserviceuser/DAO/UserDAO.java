package com.biblio.microserviceuser.DAO;

import com.biblio.microserviceuser.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
