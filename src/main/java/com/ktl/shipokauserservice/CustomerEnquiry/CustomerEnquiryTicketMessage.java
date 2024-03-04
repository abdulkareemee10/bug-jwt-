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
public class CustomerEnquiryTicketMessage {
    @Id
    private String customerEnquiryTicketMessageId;
    private String customerEnquiryId;
    private Boolean isAdmin;
    private String MessageResponse;
    private String respondedByShipokaAdminId;
    private LocalDateTime messageDateTime;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
    private LocalDateTime dateVoided;
    private String createdBy;
    private String updatedBy;
    private String voidedBy;
    private Boolean voided;
}
