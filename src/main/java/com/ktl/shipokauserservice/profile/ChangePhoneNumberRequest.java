package com.ktl.shipokauserservice.profile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePhoneNumberRequest {
    private String phoneNumber;
}
