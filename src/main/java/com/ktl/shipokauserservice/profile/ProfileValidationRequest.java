package com.ktl.shipokauserservice.profile;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProfileValidationRequest {

    private Long userCategoryId;

    private String email;

    private String firstName;

    private String lastName;

    private String phoneCountryCode;

    private  String phoneNumber;

    private String gender;

    private String contactEmailAddress;

    private String contactFirstName;

    private String contactLastName;

    private String contactPhoneNumber;

    private String contactPhoneCountryCode;

    private String profilePicture;



}
