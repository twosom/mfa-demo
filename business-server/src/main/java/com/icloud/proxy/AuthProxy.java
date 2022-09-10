package com.icloud.proxy;


import feign.RequestLine;
import org.icloud.model.UserLoginRequestModel;
import org.springframework.web.bind.annotation.RequestBody;

import static feign.FeignException.Unauthorized;

public interface AuthProxy {
    /**
     * 인증 서버에 로그인 요청을 보냅니다.
     *
     * @param loginRequestModel 인증 정보를 담은 모델입니다.
     * @return
     * @throws Unauthorized 인증에 실패한 경우 던지는 예외입니다.
     */
    @RequestLine("POST /auth/login")
    String login(@RequestBody UserLoginRequestModel loginRequestModel) throws Unauthorized;
}
