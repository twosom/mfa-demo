package com.icloud.service;

import com.icloud.entity.User;
import com.icloud.model.UserJoinRequest;
import com.icloud.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserJoinService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserJoinService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void join(UserJoinRequest joinRequest) {
        User entity = joinRequest.toEntity(passwordEncoder);
        userRepository.save(entity);
    }
}
