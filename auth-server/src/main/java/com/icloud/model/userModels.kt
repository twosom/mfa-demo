package com.icloud.model

import com.icloud.entity.User
import org.springframework.security.crypto.password.PasswordEncoder

data class UserJoinRequest(val username: String, val password: String) {
    fun toEntity(encoder: PasswordEncoder): User {
        return User(username, encoder.encode(password))
    }
}

