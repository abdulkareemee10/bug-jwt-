package com.ktl.shipokauserservice.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {

    private String email;
    private String confirmPassword;
    private String password;
    private Boolean isTermAgreement;

}