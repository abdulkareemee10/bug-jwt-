package com.ktl.shipokauserservice.appReview;

import com.ktl.shipokauserservice.Utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ShipokaAppReviewService {

    @Autowired
    private ShipokaAppReviewRepository shipokaAppReviewRepository;

    public ResponseEntity<Response> saveAppReview(ShipokaAppReviewRequest shipokaAppReviewRequest) {
        try {
            final String ids = UUID.randomUUID().toString().replace("-", "");
            ShipokaAppReview shipokaAppReview = new ShipokaAppReview();
            shipokaAppReview.setShipokaAppReviewId(ids);
            shipokaAppReview.setRatings(shipokaAppReviewRequest.getRatings());
            shipokaAppReview.setComments(shipokaAppReviewRequest.getComments());
            shipokaAppReview.setCustomerId("271c73c14a6841b792f8681c1131b70a");
            shipokaAppReview.setCreatedBy("Gloria");
            shipokaAppReviewRepository.save(shipokaAppReview);
            return ResponseEntity.ok(Response.builder().responseCode(200).responseMessage("SUCCESS")
                    .build());
        } catch (Exception e) {
            return ResponseEntity.ok(Response.builder().responseCode(-2).responseMessage(e.getMessage())
                    .build());
        }
    }

}
