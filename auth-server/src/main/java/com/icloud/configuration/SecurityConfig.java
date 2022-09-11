package com.icloud.configuration;

import com.icloud.authentication.filter.OtpLoginFilter;
import com.icloud.authentication.filter.UserLoginFilter;
import com.icloud.authentication.handler.OtpLoginSuccessHandler;
import com.icloud.authentication.handler.UserLoginSuccessHandler;
import com.icloud.authentication.provider.UsernamePasswordAuthenticationProvider;
import com.icloud.authentication.provider.OtpAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;
    private final OtpAuthenticationProvider otpAuthenticationProvider;
    private final UserLoginSuccessHandler userLoginSuccessHandler;
    private final OtpLoginSuccessHandler otpLoginSuccessHandler;

    public SecurityConfig(UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider,
                          OtpAuthenticationProvider otpAuthenticationProvider,
                          UserLoginSuccessHandler userLoginSuccessHandler,
                          OtpLoginSuccessHandler otpLoginSuccessHandler) {
        this.usernamePasswordAuthenticationProvider = usernamePasswordAuthenticationProvider;
        this.otpAuthenticationProvider = otpAuthenticationProvider;
        this.userLoginSuccessHandler = userLoginSuccessHandler;
        this.otpLoginSuccessHandler = otpLoginSuccessHandler;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.sessionManagement()
                .disable()
                .csrf()
                .disable();


        http.authorizeRequests()
                .anyRequest()
                .permitAll();

        //TODO 나중에 활성화 필요
//http.addFilterBefore(customRequestFilter, DisableEncodeUrlFilter.class);

        UserLoginFilter userLoginFilter = createUserLoginFilter();
        OtpLoginFilter otpLoginFilter = createTokenLoginFilter();
        http.addFilterAt(userLoginFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterAfter(otpLoginFilter, UserLoginFilter.class);
    }

    private OtpLoginFilter createTokenLoginFilter() throws Exception {
        var filter = new OtpLoginFilter("/auth/otp");
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(otpLoginSuccessHandler);
        return filter;
    }

    private UserLoginFilter createUserLoginFilter() throws Exception {
        var filter = new UserLoginFilter("/auth/login");
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(userLoginSuccessHandler);
        return filter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(usernamePasswordAuthenticationProvider)
                .authenticationProvider(otpAuthenticationProvider);
    }
}
