package com.icloud.authentication.provider

import com.icloud.authentication.token.OtpLoginToken
import com.icloud.extensions.getOtpLoginRequestModel
import com.icloud.proxy.AuthProxy
import feign.FeignException
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class OtpLoginProvider(
    private val authProxy: AuthProxy
) : AuthenticationProvider {
    override fun authenticate(authentication: Authentication): Authentication {
        try {
            authProxy.otpLogin(authentication.getOtpLoginRequestModel())
            return OtpLoginToken.authenticated(
                authentication.name,
                authentication.credentials,
                listOf()
            )
        } catch (e: FeignException.Unauthorized) {
            throw BadCredentialsException("Authentication Exception Occurred")
        }
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return OtpLoginToken::class.java == authentication
    }

}