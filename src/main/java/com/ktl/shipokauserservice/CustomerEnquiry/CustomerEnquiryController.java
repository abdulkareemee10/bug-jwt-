package com.ktl.shipokauserservice.CustomerEnquiry;

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

@Tag(name = "Customer Enquiry", description = "APIs for customer enquiry")
@RestController
@RequestMapping("api/v1/customerEnquiry")
public class CustomerEnquiryController {

    @Autowired
    private CustomerEnquiryService customerEnquiryService;

    @PostMapping("/post")
    @Operation(
            operationId = "saveCustomerEnquiry",
            summary = "save customer enquiry ",
            description = "this endpoint will be used to save customer enquiry"
    )
    @ApiResponse(
            responseCode = "200",
            description = "returns customer enquiry saved successfully ",
            content = @Content(mediaType = "application/json")
    )
    public ResponseEntity<Response> saveCustomerEnquiry(@RequestBody CustomerEnquiryRequest customerEnquiryRequest) {

        try {
            return customerEnquiryService.saveCustomerEnquiry(customerEnquiryRequest);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
