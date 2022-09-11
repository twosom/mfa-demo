package com.icloud.extensions

import com.fasterxml.jackson.databind.ObjectMapper
import com.icloud.exception.CannotParseAuthRequestException
import org.icloud.model.OtpLoginRequestModel
import org.icloud.model.UserLoginRequestModel
import javax.servlet.http.HttpServletRequest

fun HttpServletRequest.getUserLoginRequest(objectMapper: ObjectMapper): UserLoginRequestModel {
    return objectMapper.readValue(inputStream, UserLoginRequestModel::class.java)
        ?: throw CannotParseAuthRequestException("cannot parse username or password or code.")
}

fun HttpServletRequest.getTokenLoginRequest(objectMapper: ObjectMapper): OtpLoginRequestModel {
    return objectMapper.readValue(inputStream, OtpLoginRequestModel::class.java)
        ?: throw CannotParseAuthRequestException("cannot parse username or password or code.")
}