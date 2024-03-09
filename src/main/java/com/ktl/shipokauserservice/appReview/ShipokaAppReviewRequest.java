package com.ktl.shipokauserservice.appReview;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipokaAppReviewRequest {
    private String ratings;
    private String comments;
}
