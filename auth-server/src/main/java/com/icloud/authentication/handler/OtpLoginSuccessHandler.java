package com.icloud.authentication.handler;

import com.icloud.service.OtpService;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OtpLoginSuccessHandler implements AuthenticationSuccessHandler {

    private final OtpService otpService;

    public OtpLoginSuccessHandler(OtpService otpService) {
        this.otpService = otpService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        otpService.updateSucceedOtp(authentication.getName());
    }
}
