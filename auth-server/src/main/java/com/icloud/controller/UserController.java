package com.icloud.controller;

import com.icloud.model.UserJoinRequest;
import com.icloud.service.UserJoinService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    //TODO 유저 관련 컨트롤러
    private final UserJoinService userJoinService;

    public UserController(UserJoinService userJoinService) {
        this.userJoinService = userJoinService;
    }

    @PostMapping("/join")
    public void joinUser(@RequestBody UserJoinRequest joinRequest) {
        userJoinService.join(joinRequest);
    }

}
