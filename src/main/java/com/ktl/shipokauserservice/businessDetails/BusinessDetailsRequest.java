package com.ktl.shipokauserservice.businessDetails;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BusinessDetailsRequest {
    private String businessName;
    private Long employeeSizeId;
    private Long BusinessTypeId;
    private String businessRegistrationNumber;
}
