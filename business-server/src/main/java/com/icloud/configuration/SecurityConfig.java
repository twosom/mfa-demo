package com.icloud.configuration;

import com.icloud.authentication.filter.CustomAuthenticationFilter;
import com.icloud.authentication.filter.JwtAuthenticationFilter;
import com.icloud.authentication.handler.LoginSuccessHandler;
import com.icloud.authentication.provider.OtpLoginProvider;
import com.icloud.authentication.provider.UserLoginProvider;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserLoginProvider userLoginProvider;
    private final OtpLoginProvider otpLoginProvider;
    private final LoginSuccessHandler loginSuccessHandler;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(UserLoginProvider userLoginProvider,
                          OtpLoginProvider otpLoginProvider,
                          LoginSuccessHandler loginSuccessHandler, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.userLoginProvider = userLoginProvider;
        this.otpLoginProvider = otpLoginProvider;
        this.loginSuccessHandler = loginSuccessHandler;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;

    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.sessionManagement().disable();

        http.authorizeRequests()
                .anyRequest()
                .authenticated();

        http.addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterAt(jwtAuthenticationFilter, BasicAuthenticationFilter.class);
    }

    @NotNull
    private CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        var filter = new CustomAuthenticationFilter("/login");
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(loginSuccessHandler);
        return filter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(userLoginProvider)
                .authenticationProvider(otpLoginProvider)
        ;
    }
}
