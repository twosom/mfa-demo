package com.icloud.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public enum GenerateCodeUtil {
    ;

    public static String generateCode() {
        String code;
        try {
            var secureRandom = SecureRandom.getInstanceStrong();
            int c = secureRandom.nextInt(9000) + 1000;
            code = String.valueOf(c);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Problem when generating the random code.");
        }
        return code;
    }
}
