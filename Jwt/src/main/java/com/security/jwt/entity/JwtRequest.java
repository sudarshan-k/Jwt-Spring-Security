package com.security.jwt.entity;

import lombok.Data;

@Data
public class JwtRequest {

    private String userName;
    private String password;
}
