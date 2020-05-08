package com.biblio.microserviceuser.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    MEMBRE,
    ADMIN;


    @Override
    public String getAuthority() {
        return name();
    }


}
