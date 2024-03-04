package com.ktl.shipokauserservice.otp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity

public class Otp {

    @Id
    private String otpId;
    private String userId;
    private String email;
    private String verificationCode;
    private LocalDateTime timeCodeCreated;
    private Boolean isCodeExpired;
    private Boolean isVerificationCodeUsed;
    private LocalDateTime verificationUsedTime;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
    private String createdBy;
    private String updatedBy;
    private String voidedBy;
    private LocalDateTime dateVoided;
    private Boolean voided;

}
