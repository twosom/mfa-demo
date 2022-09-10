package com.icloud.repository;

import com.icloud.entity.Otp;
import com.icloud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, Long> {
    Optional<Otp> findOtpByUserAndValidatedFalse(User user);

    @Query("""
            SELECT o 
            FROM Otp o 
            WHERE o.user.username = :username 
            AND o.validated = false 
            """)
    Optional<Otp> findAvailableOtpByUsername(String username);
}
