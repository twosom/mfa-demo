package com.icloud.service;

import com.icloud.entity.Otp;
import com.icloud.entity.User;
import com.icloud.model.DefaultUserDetails;
import com.icloud.repository.OtpRepository;
import com.icloud.util.GenerateCodeUtil;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class OtpService {

    private final OtpRepository otpRepository;

    public OtpService(OtpRepository otpRepository) {
        this.otpRepository = otpRepository;
    }


    public String renewOtp(Authentication authentication) {
        String code = GenerateCodeUtil.generateCode();
        Object principal = authentication.getPrincipal();
        if (principal instanceof DefaultUserDetails defaultUserDetails) {
            User user = defaultUserDetails.getUser();
            otpRepository.findOtpByUserAndValidatedFalse(user)
                    .ifPresentOrElse(findOtp -> {
                        findOtp.updateCode(code);
                    }, () -> {
                        Otp otp = new Otp(user, code);
                        otpRepository.save(otp);
                    });
        }
        return code;
    }
}
