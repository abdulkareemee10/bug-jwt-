package com.ktl.shipokauserservice.employeeSize;


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

@Tag(name = "EmployeeSize", description = "APIs for employee size")
@RestController
@RequestMapping("api/v1/employeeSize")
public class EmployeeSizeController {

    @Autowired
    private EmployeeSizeService employeeSizeService;

    @PostMapping("/post")
    @Operation(
            operationId = "saveEmployeeSize",
            summary = "save employee size ",
            description = "this endpoint will be used to save employee size"
    )
    @ApiResponse(
            responseCode = "200",
            description = "returns employee size saved successfully ",
            content = @Content(mediaType = "application/json")
    )
    public ResponseEntity<Response> saveEmployeeSize(@RequestBody EmployeeSizeRequest employeeSize) {

        try {
            return new ResponseEntity<>(employeeSizeService.saveEmployeeSize(employeeSize), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/get")
    @Operation(
            operationId = "getAllEmployeeSize",
            summary = "get all employee size",
            description = "this endpoint will be used to get all the saved employee sizes"
    )
    @ApiResponse(
            responseCode = "200",
            description = "returns list of saved employee sizes",
            content = @Content(mediaType = "application/json")
    )
    public ResponseEntity<List<EmployeeSize>> getAllEmployeeSize() {

        return employeeSizeService.readEmployeeType();
    }


    @PutMapping("/{id}")
    @Operation(
            operationId = "updateEmployeeSize",
            summary = "update employee size",
            description = "this endpoint will be used to update employee size"
    )
    @ApiResponse(
            responseCode = "200",
            description = "returns employee size updated successfully",
            content = @Content(mediaType = "application/json")
    )

    public ResponseEntity<Response> updateEmployeeSize(@PathVariable("id") Long id, @RequestBody EmployeeSizeRequest employeeSize) {

        try {
            return new ResponseEntity<>(employeeSizeService.updateEmployeeSize(employeeSize, id), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(
            operationId = "deleteEmployeeSize",
            summary = "delete employee size",
            description = "this endpoint will be used to delete employee size"
    )
    @ApiResponse(
            responseCode = "200",
            description = "returns employee size deleted successfully",
            content = @Content(mediaType = "application/json")
    )
    public ResponseEntity<Response> deleteEmployeeSize(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(employeeSizeService.deleteEmployeeById(id), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
