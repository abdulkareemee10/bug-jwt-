package com.ktl.shipokauserservice.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class User {
    @Id
    private String userId;

    private String email;

    private String password;

    private Boolean isTermAgreement;

    private Boolean isEmailVerified;

    private String middleName;

    private Long userCategoryId;

    private String firstName;

    private String lastName;

    private String phoneCountryCode;

    private  String phoneNumber;

    private Boolean isPhoneNumberVerified;

    private  String pin;

    private String gender;

    private String contactEmailAddress;

    private String contactFirstName;

    private String contactLastName;

    private String contactMiddleName;

    private String contactPhoneNumber;

    private String contactPhoneCountryCode;

    private String profilePicture;

    private LocalDateTime dateCreated;

    private LocalDateTime dateUpdated;

    private  String  updatedBy;

    private String createdBy;

    private String voidedBy;

    private LocalDateTime dateVoided;

    private Boolean voided;


}
