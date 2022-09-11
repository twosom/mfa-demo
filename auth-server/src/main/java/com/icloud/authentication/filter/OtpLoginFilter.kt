package com.icloud.authentication.filter

import com.icloud.authentication.token.UsernameCodeAuthToken
import com.icloud.extensions.getTokenLoginRequest
import org.springframework.security.core.Authentication
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

//TODO auth/token
class OtpLoginFilter(filterProcessingUrl: String) : AbstractUserAndTokenLoginFilter(filterProcessingUrl) {


    override fun attemptAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse
    ): Authentication {
        validateHttpMethod(request)
        val loginRequestModel = request.getTokenLoginRequest(objectMapper)
        val authRequest = UsernameCodeAuthToken.unauthenticated(loginRequestModel)
        setDetails(authRequest, request)
        return this.authenticationManager.authenticate(authRequest)
    }
}