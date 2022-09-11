package com.icloud.authentication.handler;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Value("${jwt.signing.key}")
    private String signingKey;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) {
        if (isOtpRequest(request)) {
            var key = Keys.hmacShaKeyFor(signingKey.getBytes(StandardCharsets.UTF_8));
            var jwt = Jwts.builder()
                    .setClaims(Map.of("username", authentication.getName()))
                    .signWith(key)
                    .compact();
            response.setHeader(HttpHeaders.AUTHORIZATION, jwt);
        } else if (isJwtRequest(request)) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    private boolean isJwtRequest(HttpServletRequest request) {
        return request.getHeader(HttpHeaders.AUTHORIZATION) != null;
    }

    private boolean isOtpRequest(HttpServletRequest request) {
        return request.getParameter("code") != null;
    }
}
