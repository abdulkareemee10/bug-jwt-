package com.ktl.shipokauserservice.forgotPassword;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ForgotPasswordValidationRequest {
    private String otp;
    private String email;
}
