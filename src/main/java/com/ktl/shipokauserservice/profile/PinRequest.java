package com.ktl.shipokauserservice.profile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PinRequest {

    private String pin;
    private String confirmPin;
}
