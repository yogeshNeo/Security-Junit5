package com.methodSecurity.dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class Role implements GrantedAuthority {

    private String name;

    @Override
    public String getAuthority() {
        return this.name;
    }
}