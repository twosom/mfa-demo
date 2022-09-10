package com.icloud.authentication.token;

import org.icloud.model.TokenLoginRequestModel;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class UsernameCodeAuthToken extends UsernamePasswordAuthenticationToken {

    public UsernameCodeAuthToken(TokenLoginRequestModel requestModel) {
        super(requestModel.getUsername(), requestModel.getCode());
    }

    public static UsernameCodeAuthToken unauthenticated(TokenLoginRequestModel requestModel) {
        return new UsernameCodeAuthToken(requestModel);
    }
}
