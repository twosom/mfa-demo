package com.icloud.authentication.token;

import com.icloud.extensions.LoginRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class OtpLoginToken extends UsernamePasswordAuthenticationToken {

    public OtpLoginToken(LoginRequest loginRequest) {
        super(loginRequest.getUsername(), loginRequest.getCode());
    }
}
