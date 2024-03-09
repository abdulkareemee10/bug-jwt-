package com.ktl.shipokauserservice.CustomerEnquiry;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnquiryType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer enquiryTypeId;

    private String enquiryType;
}
