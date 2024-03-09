package com.ktl.shipokauserservice.forgotPassword;

import com.ktl.shipokauserservice.Utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "ForgotPassword", description = "forgot password APIs")
@RestController
@RequestMapping("api/v1/forgotPassword")
public class ForgotPasswordController {

    @Autowired
    private ForgotPasswordService forgotPasswordService;
    @PostMapping("/sendOtpForgotPassword")
    @Operation(
            operationId = "sendOtp",
            summary = "send otp ",
            description = "will be used to send otp for forgot password endpoint"
    )
    @ApiResponse(
            responseCode = "200",
            description = "returns otp sent successfully ",
            content = @Content(mediaType = "application/json")
    )
    public Response sendOtpForgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest) {

            return forgotPasswordService.sendOtpForgotPassword(forgotPasswordRequest);

    }
    @PostMapping("/validateOtpForgotPassword")
    @Operation(
            operationId = "validateOtp",
            summary = "validate otp ",
            description = "will be used to validate otp for forgot password endpoint"
    )
    @ApiResponse(
            responseCode = "200",
            description = "returns otp validated successfully",
            content = @Content(mediaType = "application/json")
    )
    public ResponseEntity<Response> validateOtpForgotPassword(@RequestBody ForgotPasswordValidationRequest forgotPasswordValidationRequest) {

        try {
            return new ResponseEntity<>(forgotPasswordService.validateOtpForgotPassword(forgotPasswordValidationRequest), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/changePassword/{userId}")

    @Operation(
            operationId = "changePassword",
            summary = "change saved password",
            description = "will be used to change password endpoint"
    )
    @ApiResponse(
            responseCode = "200",
            description = "returns password changed successfully",
            content = @Content(mediaType = "application/json")
    )
    public ResponseEntity<Response> changePassword(@PathVariable("userId") String userId, @RequestBody ForgotPasswordRequest forgotPasswordRequest) {

        try {
            return new ResponseEntity<>(forgotPasswordService.changePassword(forgotPasswordRequest, userId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}