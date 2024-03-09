package com.ktl.shipokauserservice.CustomerEnquiry;

import com.ktl.shipokauserservice.Utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "CustomerEnquiryTicketMessageController", description = "APIs for customer ticket message")
@RestController
@RequestMapping("api/v1/customerEnquiryTicketMessage")
public class CustomerEnquiryTicketMessageController {

    @Autowired
    private CustomerEnquiryTicketMessageService customerEnquiryTicketMessageService;

    @PostMapping("/post")
    @Operation(
            operationId = "saveCustomerEnquiryTicketMessage",
            summary = "save Customer Enquiry Ticket Message",
            description = "this endpoint will be used to save customer enquiry message ticket"
    )
    @ApiResponse(
            responseCode = "200",
            description = "returns customer enquiry ticket message saved successfully ",
            content = @Content(mediaType = "application/json")
    )
    public ResponseEntity<Response> saveCustomerEnquiryTicketMessage(@RequestBody CustomerEnquiryTicketMessageRequest
                                                                                 customerEnquiryTicketMessageRequest) {

        try {
            return customerEnquiryTicketMessageService.saveCustomerTicketMessage(customerEnquiryTicketMessageRequest);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


        @GetMapping("/get/{customerEnquiryId}")
        @Operation(
                operationId = "getResponseFromAdminOverComplaint ",
                summary = "get response from admin over complaint ",
                description = "this endpoint will be used to get response from admin over complaint "
        )
        @ApiResponse(
                responseCode = "200",
                description = "returns response from admin",
                content = @Content(mediaType = "application/json")
        )
        public ResponseEntity<Response> getCustomerComplaintResponse(@PathVariable("customerEnquiryId") String customerEnquiryId) {

            return customerEnquiryTicketMessageService.getCustomerEnquiryMessageByCustomerEnquiryId(customerEnquiryId);
        }


}
