package com.ktl.shipokauserservice.CustomerEnquiry;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
public class CustomerEnquiry {
    @Id
    private String customerEnquiryId;

    private String enquiryFileId;

    private String customerId;

    private String enquiryTypeId;

    private String firstName;

    private String lastName;

    private String middleName;

    private String email;

    private String phoneNumber;

    private String subject;

    private String message;

    private Boolean isRegisteredCustomer;

    private String enquiryStatus;

    private LocalDateTime dateCreated;

    private LocalDateTime dateUpdated;

    private String createdBy;

    private String updatedBy;

    private LocalDateTime dateVoided;

    private String voidedBy;

    private Boolean voided;

}
