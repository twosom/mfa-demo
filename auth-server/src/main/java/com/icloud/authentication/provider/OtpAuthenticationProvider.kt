package com.icloud.authentication.provider

import com.icloud.authentication.token.UsernameCodeAuthToken
import com.icloud.repository.OtpRepository
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component
class OtpAuthenticationProvider(
    private val otpRepository: OtpRepository
) : AuthenticationProvider {

    override fun authenticate(authentication: Authentication): Authentication {
        val otp = otpRepository.findAvailableOtpByUsername(authentication.name)
            .orElseThrow { throw UsernameNotFoundException("cannot find token with ${authentication.name}") }

        if (!otp.code.equals(authentication.credentials)) {
            throw BadCredentialsException("otp code not incorrect")
        }

        return UsernameCodeAuthToken.authenticated(authentication.name, authentication.credentials, listOf())
    }

    override fun supports(aClass: Class<*>?): Boolean {
        return UsernameCodeAuthToken::class.java == aClass
    }
}