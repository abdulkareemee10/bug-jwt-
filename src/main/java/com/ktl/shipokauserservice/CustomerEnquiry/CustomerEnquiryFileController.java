package com.ktl.shipokauserservice.CustomerEnquiry;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Tag(name = "Customer Enquiry File", description = "APIs for customer enquiry files")
@RestController
@RequestMapping("api/v1/customerEnquiryFile")
public class CustomerEnquiryFileController {


@Autowired
private CustomerEnquiryFileService customerEnquiryFileService;

    @PostMapping("/post/{userId}")
    @Operation(
            operationId = "saveCustomerEnquiryUploadedFiles",
            summary = "save customer enquiry files",
            description = "this endpoint will be used to save customer enquiry uploaded files"
    )
    @ApiResponse(
            responseCode = "200",
            description = "returns customer enquiry file saved successfully ",
            content = @Content(mediaType = "application/json")
    )
    public ResponseEntity<?> uploadFile(@PathVariable("userId") String userId, @RequestParam("file") MultipartFile file) throws IOException {
        String uploadFile = customerEnquiryFileService.uploadFile(file, userId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadFile);
    }
    @GetMapping("/download/{fileName}")
    @Operation(
            operationId = "getComplaintFilesUploadedByCustomer ",
            summary = "get complaint files  uploaded by customer",
            description = "this endpoint will be used to get the files uploaded by customer for complaint "
    )
    @ApiResponse(
            responseCode = "200",
            description = "returns complaint files uploaded by the customer",
            content = @Content(mediaType = "application/json")
    )


    public ResponseEntity<?> downloadFile(@PathVariable String fileName) throws IOException {
        byte[] uploadedFile = customerEnquiryFileService.downloadFile(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadedFile);
    }
}

