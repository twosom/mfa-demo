package com.icloud.proxy;


import feign.RequestLine;
import org.icloud.model.OtpLoginRequestModel;
import org.icloud.model.UserLoginRequestModel;
import org.springframework.web.bind.annotation.RequestBody;

import static feign.FeignException.Unauthorized;

public interface AuthProxy {
    /**
     * 인증 서버에 로그인 요청을 보냅니다.
     *
     * @param loginRequestModel 인증 정보를 담은 모델입니다.
     * @throws Unauthorized 인증에 실패한 경우 던지는 예외입니다.
     */
    @RequestLine("POST /auth/login")
    Void userLogin(@RequestBody UserLoginRequestModel loginRequestModel) throws Unauthorized;

    /**
     * 인증 서버에 OTP 로그인 요청을 보냅니다.
     *
     * @param otpLoginRequestModel 인증 정보를 담은 모델입니다.
     * @throws Unauthorized 인증에 실패한 경우 던지는 예외입니다.
     */
    @RequestLine("POST /auth/otp")
    Void otpLogin(@RequestBody OtpLoginRequestModel otpLoginRequestModel) throws Unauthorized;
}
