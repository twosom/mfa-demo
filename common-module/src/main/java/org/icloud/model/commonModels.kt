package org.icloud.model

data class UserLoginRequestModel(val username: String?, val password: String?) {
    constructor() : this(null, null)
}

data class OtpLoginRequestModel(val username: String?, val code: String?) {
    constructor() : this(null, null)
}