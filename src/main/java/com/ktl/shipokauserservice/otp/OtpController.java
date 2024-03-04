package com.ktl.shipokauserservice.otp;





import com.ktl.shipokauserservice.Utils.Response;
import com.ktl.shipokauserservice.user.UserResponse;
import com.ktl.shipokauserservice.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Otp", description = "APIs for sending and validating Otp")
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/otp")
public class OtpController {

    private final OtpService otpService;

    @PostMapping("/send")
    @Operation(
            operationId = "sendOtp",
            summary = "send otp ",
            description = "this endpoint will be used to send otp"
    )
    @ApiResponse(
            responseCode = "200",
            description = "returns otp password successfully ",
            content = @Content(mediaType = "application/json")
    )
    public ResponseEntity<Response> sendOtp(@RequestBody OtpRequest otpRequest) {
        try{
            return (otpService.sendOtp(otpRequest));
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/validateOtp")
    @Operation(
            operationId = "validateOtp",
            summary = "validate otp ",
            description = "this endpoint will be used to validate otp"
    )
    @ApiResponse(
            responseCode = "200",
            description = "returns otp validated successfully",
            content = @Content(mediaType = "application/json")
    )
    public ResponseEntity<Response> validateOtp(@RequestBody OtpValidationRequest otpValidationRequest) {

        try {
            return new ResponseEntity<>(otpService.validateOtp(otpValidationRequest), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
