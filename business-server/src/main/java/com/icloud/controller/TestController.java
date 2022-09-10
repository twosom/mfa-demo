package com.icloud.controller;

import com.icloud.proxy.AuthProxy;
import org.icloud.model.UserLoginRequestModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final AuthProxy authProxy;

    public TestController(AuthProxy authProxy) {
        this.authProxy = authProxy;
    }

    @GetMapping("/test")
    public String test() {
        return authProxy.login(new UserLoginRequestModel("hope", "hope123"));
    }
}
