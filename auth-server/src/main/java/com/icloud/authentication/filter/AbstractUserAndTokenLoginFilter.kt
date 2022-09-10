package com.icloud.authentication.filter

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import javax.servlet.http.HttpServletRequest

abstract class AbstractUserAndTokenLoginFilter(filterProcessingUrl: String) :
    AbstractAuthenticationProcessingFilter(filterProcessingUrl) {

    protected val objectMapper = ObjectMapper()
    private val postOnly: Boolean = true

    protected fun validateHttpMethod(request: HttpServletRequest) {
        if (this.postOnly && !request.method.equals("POST")) {
            throw AuthenticationServiceException("Authentication method not supported ${request.method}")
        }
    }

    protected fun setDetails(
        requestToken: UsernamePasswordAuthenticationToken,
        request: HttpServletRequest
    ) {
        requestToken.details = super.authenticationDetailsSource.buildDetails(request)
    }
}