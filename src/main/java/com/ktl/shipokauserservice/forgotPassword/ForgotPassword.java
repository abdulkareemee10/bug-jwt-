package com.ktl.shipokauserservice.forgotPassword;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ForgotPassword {
    @Id
    private String forgotPasswordId;
    private String email;
    private String userId;
    private String verificationCode;
    private Boolean isPasswordChanged;
    private Boolean isOtpUsed;
   private LocalDateTime timeOtpCreated;
    private Boolean isOtpExpired;
    private LocalDateTime otpUsedTime;
    private LocalDateTime timePasswordChanged;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
    private String createdBy;
    private String updatedBy;
    private String voidedBy;
    private LocalDateTime dateVoided;
    private Boolean voided;
}
