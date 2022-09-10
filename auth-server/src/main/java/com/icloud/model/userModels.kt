package com.icloud.model

import com.icloud.entity.User
import org.springframework.security.crypto.password.PasswordEncoder

data class UserJoinRequest(val username: String, val password: String) {
    fun toEntity(encoder: PasswordEncoder): User {
        return User(username, encoder.encode(password))
    }
}

data class UserLoginRequestModel(val username: String?, val password: String?) {
    constructor() : this(null, null)
}

data class TokenLoginRequestModel(val username: String?, val code: String?) {
    constructor() : this(null, null)
}

