package com.methodsecurity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AuthRequest
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    private String username ;
    private String password;
}
