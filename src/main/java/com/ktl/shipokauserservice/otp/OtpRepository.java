package com.ktl.shipokauserservice.otp;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, String> {

Otp findByEmail(String email);

Optional<Otp> findByEmailAndVerificationCode(String email, String verificationCode);

//   Optional<Otp> findFirstByEmailOrderByTimeCodeCreatedDesc(String email);
}
