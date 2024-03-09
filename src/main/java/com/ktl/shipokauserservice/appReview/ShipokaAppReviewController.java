package com.ktl.shipokauserservice.appReview;

import com.ktl.shipokauserservice.Utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Shipoka App Review", description = "APIs for Shipoka App review")
@RestController
@RequestMapping("api/v1/appReview")
public class ShipokaAppReviewController {

    @Autowired
    private ShipokaAppReviewService shipokaAppReviewService;

    @PostMapping("/post")
    @Operation(
            operationId = "saveShipokaAppReview",
            summary = "save Shipoka App Review",
            description = "this endpoint will be used to save app ratings and comments"
    )
    @ApiResponse(
            responseCode = "200",
            description = "returns we got your ratings",
            content = @Content(mediaType = "application/json")
    )
    public ResponseEntity<Response> saveAppReview(@RequestBody ShipokaAppReviewRequest shipokaAppReviewRequest) {

        try {
            return shipokaAppReviewService.saveAppReview(shipokaAppReviewRequest);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
