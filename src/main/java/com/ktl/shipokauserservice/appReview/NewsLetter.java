package com.ktl.shipokauserservice.appReview;

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
public class NewsLetter {
    @Id
    private Integer newsletterId;
    private String email;
    private String subscriptionStatus;
}
