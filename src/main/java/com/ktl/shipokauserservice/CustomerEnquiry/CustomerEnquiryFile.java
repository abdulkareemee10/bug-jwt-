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
public class CustomerEnquiryFile {

    @Id
    private String customerEnquiryFileId;

    private String customerEnquiryId;

    private String customerEnquiryTicketMessageId;

    private String customerEnquiryFileName;

    private String url;

    private LocalDateTime dateCreated;

    private LocalDateTime dateUpdated;

    private  String  updatedBy;

    private String createdBy;

    private String voidedBy;

    private LocalDateTime dateVoided;

    private Boolean voided;
}
