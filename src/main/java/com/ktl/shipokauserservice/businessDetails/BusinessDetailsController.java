package com.ktl.shipokauserservice.businessDetails;


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

@Tag(name = "BusinessDetails", description = "APIs for business details")
@RestController
@RequestMapping("api/v1/businessDetails")
public class BusinessDetailsController {

    @Autowired
    private BusinessDetailsService businessDetailsService;

    @PostMapping("/post")
    @Operation(
            operationId = "saveBusinessDetails",
            summary = "save business details",
            description = "this endpoint will be used to save business details"
    )
    @ApiResponse(
            responseCode = "200",
            description = "returns business details saved successfully",
            content = @Content(mediaType = "application/json")
    )
    public ResponseEntity<Response> saveBusinessDetails(@RequestBody BusinessDetailsRequest businessDetailsRequest) {

        try {
            return new ResponseEntity<>(businessDetailsService.saveBusinessDetails(businessDetailsRequest), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
