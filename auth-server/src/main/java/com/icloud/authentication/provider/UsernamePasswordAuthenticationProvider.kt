package com.icloud.authentication.provider

import com.icloud.authentication.token.UsernamePasswordAuthToken
import com.icloud.service.UserAuthService
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class UsernamePasswordAuthenticationProvider(
    private val userAuthService: UserAuthService,
    private val passwordEncoder: PasswordEncoder
) : AuthenticationProvider {
    override fun authenticate(authentication: Authentication): Authentication {
        val findUser = userAuthService.loadUserByUsername(authentication.name)
        if (!passwordEncoder.matches(authentication.credentials.toString(), findUser.password)) {
            throw BadCredentialsException("Authentication Exception Occurred")
        }

        return UsernamePasswordAuthToken.authenticated(
            findUser,
            authentication.credentials,
            listOf(GrantedAuthority { "USER" })
        )
    }

    override fun supports(aClass: Class<*>): Boolean {
        return UsernamePasswordAuthToken::class.java == aClass
    }

}