package com.ktl.shipokauserservice.forgotPassword;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword, String> {
    Optional<ForgotPassword> findByEmail(String email);

    Optional<ForgotPassword> findByUserId(String userId);

    Optional<ForgotPassword> findByUserIdAndIsOtpUsed(String userId, Boolean isOtpUsed);

    Optional<ForgotPassword> findByEmailAndVerificationCode(String email, String otp);
}
