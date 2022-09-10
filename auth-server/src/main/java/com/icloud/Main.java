package com.icloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}

//TODO
// auth-server 해야 할 일
// 사용자 가입 및 로그인, OTP 발행
// business-server 와 auth-server 간의 통신에서 간단하게 인증 절차 필요
