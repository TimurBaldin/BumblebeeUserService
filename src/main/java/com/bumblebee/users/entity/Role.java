package com.bumblebee.users.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER("USER_ROLE"),
    ANONYMOUS("ANONYMOUS");

    private final String role;

    Role(String role) {
        this.role=role;
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
