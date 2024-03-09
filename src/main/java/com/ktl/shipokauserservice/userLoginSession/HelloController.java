package com.ktl.shipokauserservice.userLoginSession;

import com.ktl.shipokauserservice.CustomerEnquiry.EnquiryType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "User login session", description = "APIs for user login session")
@RestController
@RequestMapping("api/")
public class HelloController {

    @Autowired
    private LoginDetailService loginDetailService;

    @GetMapping("/login-details")
    @Operation(
            operationId = "getUserLoginSessionDetails",
            summary = "get user login session",
            description = "this endpoint will be used to get the user login session"
    )
    @ApiResponse(
            responseCode = "200",
            description = "returns the user login session information",
            content = @Content(mediaType = "application/json")
    )

    public String loginDetails(HttpServletRequest request) {
        loginDetailService.printLoginDetails(request);
        return "Login details printed in the console";
    }
}