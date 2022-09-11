package com.icloud.authentication.filter

import com.icloud.authentication.token.OtpLoginToken
import com.icloud.authentication.token.UserLoginToken
import com.icloud.extensions.AuthType
import com.icloud.extensions.getLoginRequest
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CustomAuthenticationFilter(defaultFilterProcessingUrl: String) :
    AbstractAuthenticationProcessingFilter(defaultFilterProcessingUrl) {

    private val postOnly: Boolean = true

    override fun attemptAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse
    ): Authentication {
        if (this.postOnly && !request.method.equals("POST")) {
            throw AuthenticationServiceException("Authentication method not supported ${request.method}")
        }

        val loginRequest = request.getLoginRequest()
        val authentication = when (loginRequest.getAuthType()) {
            AuthType.LOGIN -> UserLoginToken(loginRequest)
            AuthType.OTP -> OtpLoginToken(loginRequest)
            AuthType.NONE -> throw IllegalArgumentException()
        }
        setDetails(authentication, request)
        return authenticationManager.authenticate(authentication)
    }

    private fun setDetails(
        requestToken: UsernamePasswordAuthenticationToken,
        request: HttpServletRequest
    ) {
        requestToken.details = super.authenticationDetailsSource.buildDetails(request)
    }
}