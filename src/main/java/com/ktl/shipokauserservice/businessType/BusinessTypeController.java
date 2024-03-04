package com.ktl.shipokauserservice.businessType;



import com.ktl.shipokauserservice.Utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "BusinessType", description = "APIs for business type")
@RestController
@RequestMapping("api/v1/businessType")
public class BusinessTypeController {
    @Autowired
    private BusinessTypeService businessTypeService;

    @PostMapping("/post")
    @Operation(
            operationId = "saveBusinessType",
            summary = "save business type",
            description = "this endpoint will be used to save business type"
    )
    @ApiResponse(
            responseCode = "200",
            description = "returns business type saved successfully",
            content = @Content(mediaType = "application/json")
    )
    public ResponseEntity<Response> saveBusinessType(@RequestBody BusinessTypeRequest businessTypeRequest){

     try {
        return new ResponseEntity<>(businessTypeService.saveBusinessType(businessTypeRequest), HttpStatus.CREATED);
    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
    @GetMapping("/get")
    @Operation(
            operationId = "getAllBusinessTypes",
            summary = "get all business types",
            description = "this endpoint will be used to get all saved business types"
    )
    @ApiResponse(
            responseCode = "200",
            description = "returns list of business types",
            content = @Content(mediaType = "application/json")
    )
    public ResponseEntity<List<BusinessType>> getAllBusiness() {
         return businessTypeService.readBusinessType();
          }

    @PutMapping("/{id}")
    @Operation(
            operationId = "updateBusinessType",
            summary = "update business type",
            description = "this endpoint will be used to update business type"
    )
    @ApiResponse(
            responseCode = "200",
            description = "returns business type updated successfully",
            content = @Content(mediaType = "application/json")
    )
    public ResponseEntity<Response> updateBusinessType(@PathVariable("id") Long id, @RequestBody BusinessType businessType){

     try {
        return new ResponseEntity<>(businessTypeService.updateBusinessType(businessType, id), HttpStatus.CREATED);
    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
    @DeleteMapping("/{id}")
    @Operation(
            operationId = "deleteBusinessType",
            summary = "delete business type",
            description = "this endpoint will be used to delete a business type"
    )
    @ApiResponse(
            responseCode = "200",
            description = "returns business type deleted successfully",
            content = @Content(mediaType = "application/json")
    )
    public  ResponseEntity<Response> deleteBusinessType(@PathVariable("id") Long id){
        try {
        return new ResponseEntity<>(businessTypeService.deleteBusinessById(id), HttpStatus.CREATED);
    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

}
