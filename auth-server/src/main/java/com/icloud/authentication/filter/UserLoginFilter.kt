package com.icloud.authentication.filter

import com.icloud.authentication.token.UsernamePasswordAuthToken
import com.icloud.extensions.getUserLoginRequest
import org.springframework.security.core.Authentication
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class UserLoginFilter(filterProcessesUrl: String) : AbstractUserAndTokenLoginFilter(filterProcessesUrl) {

    override fun attemptAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse
    ): Authentication {
        validateHttpMethod(request)
        val loginRequestModel = request.getUserLoginRequest(objectMapper)
        val authRequest = UsernamePasswordAuthToken.unauthenticated(loginRequestModel)
        setDetails(authRequest, request)
        return this.authenticationManager.authenticate(authRequest)
    }


}