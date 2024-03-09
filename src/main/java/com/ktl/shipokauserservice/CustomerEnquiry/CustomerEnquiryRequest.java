package com.ktl.shipokauserservice.CustomerEnquiry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEnquiryRequest {

    private String enquiryFileId;
    private String customerId;
    private String enquiryTypeId;
    private String firstName;
    private String lastName;
    private String email;
    private String middleName;
    private String phoneNumber;
    private String subject;
    private String message;
    private String enquiryStatus;
}
