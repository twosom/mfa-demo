package com.icloud.authentication.token;

import org.icloud.model.OtpLoginRequestModel;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class UsernameCodeAuthToken extends UsernamePasswordAuthenticationToken {

    public UsernameCodeAuthToken(OtpLoginRequestModel requestModel) {
        super(requestModel.getUsername(), requestModel.getCode());
    }

    public static UsernameCodeAuthToken unauthenticated(OtpLoginRequestModel requestModel) {
        return new UsernameCodeAuthToken(requestModel);
    }
}
