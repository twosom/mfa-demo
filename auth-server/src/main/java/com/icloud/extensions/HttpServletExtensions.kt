package com.icloud.extensions

import com.fasterxml.jackson.databind.ObjectMapper
import com.icloud.exception.CannotParseAuthRequestException
import com.icloud.model.TokenLoginRequestModel
import com.icloud.model.UserLoginRequestModel
import javax.servlet.http.HttpServletRequest

fun HttpServletRequest.getUserLoginRequest(objectMapper: ObjectMapper): UserLoginRequestModel {
    return objectMapper.readValue(inputStream, UserLoginRequestModel::class.java)
        ?: throw CannotParseAuthRequestException("cannot parse username or password or code.")
}

fun HttpServletRequest.getTokenLoginRequest(objectMapper: ObjectMapper): TokenLoginRequestModel {
    return objectMapper.readValue(inputStream, TokenLoginRequestModel::class.java)
        ?: throw CannotParseAuthRequestException("cannot parse username or password or code.")
}