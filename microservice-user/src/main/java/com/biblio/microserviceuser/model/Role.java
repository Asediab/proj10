package com.biblio.microserviceuser.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    MEMBRE;


    @Override
    public String getAuthority() {
        return name();
    }
}
