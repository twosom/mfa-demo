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
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {

    private final OtpService otpService;

    public UserLoginSuccessHandler(OtpService otpService) {
        this.otpService = otpService;

    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        //TODO response 에 OTP 코드를 넣을 지 고민 필요
        String code = otpService.renewOtp(authentication);
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(code);
    }
}
