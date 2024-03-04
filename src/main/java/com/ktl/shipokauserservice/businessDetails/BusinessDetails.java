package com.ktl.shipokauserservice.businessDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

@Builder
public class BusinessDetails {
    @Id
    private String businessDetailsId;
    private String profileId;
    private Long employeeSizeId;
    private Long businessTypeId;
    private String businessName;
    private String businessRegistrationNumber;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime dateVoided;
    private String voidedBy;
    private Boolean voided;

}
