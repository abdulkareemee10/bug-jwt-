package com.ktl.shipokauserservice.CustomerEnquiry;


import com.ktl.shipokauserservice.Utils.Response;
import com.ktl.shipokauserservice.employeeSize.EmployeeSize;
import com.ktl.shipokauserservice.employeeSize.EmployeeSizeRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "EnquiryType", description = "APIs for enquiry type")
@RestController
@RequestMapping("api/v1/enquiryType")
public class EnquiryTypeController {

@Autowired
    private EnquiryTypeService enquiryTypeService;

    @PostMapping("/post")
    @Operation(
            operationId = "saveEnquiryType",
            summary = "save enquiry type ",
            description = "this endpoint will be used to save enquiry type"
    )
    @ApiResponse(
            responseCode = "200",
            description = "returns enquiry type saved successfully ",
            content = @Content(mediaType = "application/json")
    )
    public ResponseEntity<Response> saveEnquiryType(@RequestBody EnquiryTypeRequest enquiryTypeRequest) {

        try {
            return new ResponseEntity<>(enquiryTypeService.saveEnquiryType(enquiryTypeRequest), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/get")
    @Operation(
            operationId = "getAllEnquiryType",
            summary = "get all enquiry types",
            description = "this endpoint will be used to get all the enquiry type"
    )
    @ApiResponse(
            responseCode = "200",
            description = "returns list of saved enquiry type",
            content = @Content(mediaType = "application/json")
    )
    public ResponseEntity<List<EnquiryType>> getAllEnquiryTypes() {

        return enquiryTypeService.readEnquiryType();
    }


    @PutMapping("/{id}")
    @Operation(
            operationId = "updateEnquiryType",
            summary = "update enquiry type",
            description = "this endpoint will be used to update enquiry types"
    )
    @ApiResponse(
            responseCode = "200",
            description = "returns enquiry type updated successfully",
            content = @Content(mediaType = "application/json")
    )

    public ResponseEntity<Response> updateEnquiryType(@PathVariable("id") Integer id, @RequestBody EnquiryTypeRequest enquiryTypeRequest) {

        try {
            return new ResponseEntity<>(enquiryTypeService.updateEnquiryTpe(enquiryTypeRequest, id), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(
            operationId = "deleteEnquiryType",
            summary = "delete enquiry type",
            description = "this endpoint will be used to delete enquiry type"
    )
    @ApiResponse(
            responseCode = "200",
            description = "returns enquiry type deleted successfully",
            content = @Content(mediaType = "application/json")
    )
    public ResponseEntity<Response> deleteEnquiryType(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity<>(enquiryTypeService.deleteEnquiryType(id), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
