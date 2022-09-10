package com.icloud.authentication.filter;

import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomRequestFilter extends OncePerRequestFilter {

    public static final String X_AUTHORIZATION_MING = "X-Authorization-Ming";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        validate(request, response);
        filterChain.doFilter(request, response);
    }

    private void validate(HttpServletRequest request, HttpServletResponse response) {
        String header = request.getHeader(X_AUTHORIZATION_MING);
        if (!StringUtils.hasText(header) || !header.equals("MING")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            throw new AuthorizationServiceException("error");
        }
    }
}
