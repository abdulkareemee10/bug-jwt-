package com.ktl.shipokauserservice.otp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OtpResponse {
    private Boolean isOtpValid;
}
