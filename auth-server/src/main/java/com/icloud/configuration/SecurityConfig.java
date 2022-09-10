package com.icloud.configuration;

import com.icloud.authentication.filter.TokenLoginFilter;
import com.icloud.authentication.filter.UserLoginFilter;
import com.icloud.authentication.handler.TokenLoginSuccessHandler;
import com.icloud.authentication.handler.UserLoginSuccessHandler;
import com.icloud.authentication.provider.UsernamePasswordAuthenticationProvider;
import com.icloud.authentication.provider.UsernameTokenAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;
    private final UsernameTokenAuthenticationProvider usernameTokenAuthenticationProvider;
    private final UserLoginSuccessHandler userLoginSuccessHandler;
    private final TokenLoginSuccessHandler tokenLoginSuccessHandler;

    public SecurityConfig(UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider,
                          UsernameTokenAuthenticationProvider usernameTokenAuthenticationProvider,
                          UserLoginSuccessHandler userLoginSuccessHandler,
                          TokenLoginSuccessHandler tokenLoginSuccessHandler) {
        this.usernamePasswordAuthenticationProvider = usernamePasswordAuthenticationProvider;
        this.usernameTokenAuthenticationProvider = usernameTokenAuthenticationProvider;
        this.userLoginSuccessHandler = userLoginSuccessHandler;
        this.tokenLoginSuccessHandler = tokenLoginSuccessHandler;
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
        TokenLoginFilter tokenLoginFilter = createTokenLoginFilter();
        http.addFilterAt(userLoginFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterAfter(tokenLoginFilter, UserLoginFilter.class);
    }

    private TokenLoginFilter createTokenLoginFilter() throws Exception {
        var filter = new TokenLoginFilter("/auth/token");
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(tokenLoginSuccessHandler);
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
                .authenticationProvider(usernameTokenAuthenticationProvider);
    }
}
