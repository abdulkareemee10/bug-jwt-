package com.ktl.shipokauserservice.appReview;

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
public class ShipokaAppReview {
    @Id
    private String shipokaAppReviewId;
    private String customerId;
    private String ratings;
    private String comments;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
    private LocalDateTime dateVoided;
    private String createdBy;
    private String updatedBy;
    private String voidedBy;
    private Boolean voided;
}
