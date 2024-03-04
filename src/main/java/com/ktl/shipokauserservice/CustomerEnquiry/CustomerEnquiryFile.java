package com.ktl.shipokauserservice.CustomerEnquiry;

import jakarta.persistence.Entity;
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
public class CustomerEnquiryFile {

    @Id
    private String customerEnquiryFileId;
    private String customerEnquiryId;
    private String customerEnquiryTicketMessageId;
    private String customerEnquiryFileName;
    private String url;
}
