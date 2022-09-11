package com.icloud.authentication.provider

import com.icloud.authentication.token.UserLoginToken
import com.icloud.extensions.getUserLoginRequestModel
import com.icloud.proxy.AuthProxy
import feign.FeignException
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Component

@Component
class UserLoginProvider(
    private val authProxy: AuthProxy
) : AuthenticationProvider {

    override fun authenticate(authentication: Authentication): Authentication {
        try {
            authProxy.userLogin(authentication.getUserLoginRequestModel())
            return UserLoginToken.authenticated(
                authentication.name,
                authentication.credentials,
                listOf()
            )
        } catch (e: FeignException.Unauthorized) {
            throw BadCredentialsException("Authentication Exception Occurred")
        }
    }

    override fun supports(authentication: Class<*>): Boolean {
        return UserLoginToken::class.java == authentication
    }
}