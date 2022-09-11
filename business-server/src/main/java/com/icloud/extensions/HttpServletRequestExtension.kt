package com.icloud.extensions

import javax.servlet.http.HttpServletRequest


enum class AuthType {
    LOGIN,
    OTP,
    NONE;
}

fun HttpServletRequest.getLoginRequest(): LoginRequest {
    return LoginRequest(this)
}

data class LoginRequest(
    val username: String?,
    val password: String?,
    val code: String?,
) {
    constructor(request: HttpServletRequest) : this(
        request.getParameter("username"),
        request.getParameter("password"),
        request.getParameter("code"),
    )

    fun getAuthType(): AuthType {
        return when {
            isLogin() -> AuthType.LOGIN
            isOtp() -> AuthType.OTP
            else -> AuthType.NONE
        }
    }

    private fun isOtp() = username != null && password == null && code != null

    private fun isLogin() = username != null && password != null && code == null
}