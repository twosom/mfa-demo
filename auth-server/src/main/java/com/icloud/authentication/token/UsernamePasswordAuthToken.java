package com.icloud.authentication.token;

import com.icloud.model.UserLoginRequestModel;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class UsernamePasswordAuthToken extends UsernamePasswordAuthenticationToken {

    public UsernamePasswordAuthToken(UserLoginRequestModel requestModel) {
        super(requestModel.getUsername(), requestModel.getPassword());
    }

    public static UsernamePasswordAuthToken unauthenticated(UserLoginRequestModel requestModel) {
        return new UsernamePasswordAuthToken(requestModel);
    }
}
