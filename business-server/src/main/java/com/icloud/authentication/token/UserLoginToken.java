package com.icloud.authentication.token;

import com.icloud.extensions.LoginRequest;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class UserLoginToken extends UsernamePasswordAuthenticationToken {

    public UserLoginToken(LoginRequest loginRequest) {
        super(loginRequest.getUsername(), loginRequest.getPassword());
    }
}
