package com.icloud.extensions

import org.icloud.model.OtpLoginRequestModel
import org.icloud.model.UserLoginRequestModel
import org.springframework.security.core.Authentication

fun Authentication.getUserLoginRequestModel(): UserLoginRequestModel {
    return UserLoginRequestModel(this.name, this.credentials.toString())
}

fun Authentication.getOtpLoginRequestModel(): OtpLoginRequestModel {
    return OtpLoginRequestModel(this.name, this.credentials.toString())
}