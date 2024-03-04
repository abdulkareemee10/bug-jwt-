package com.ktl.shipokauserservice.profile;


import com.ktl.shipokauserservice.Utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Profile", description = "user profile APIs for both personal and business users")
@RestController
@RequestMapping("api/v1/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    //        @PostMapping("/save")
//    public ResponseEntity<String> saveProfile(@RequestBody PersonalValidationRequest personalValidationRequest){
//        profileService.savePersonalPersonalProfile(personalValidationRequest);
//        return new ResponseEntity<>("saved", HttpStatus.OK);
//
//    }
    @PostMapping("/post")
    @Operation(
            operationId = "saveUserProfile",
            summary = "save user profile",
            description = "this endpoint is to save both personal and business user profile"
    )
    @ApiResponse(
            responseCode = "200",
            description = "returns user profile saved successfully",
            content = @Content(mediaType = "application/json")
    )
    public ResponseEntity<Response> saveProfile(@RequestBody ProfileValidationRequest personalValidationRequest) {

        try {
            return new ResponseEntity<>(profileService.saveProfile(personalValidationRequest), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @PutMapping("/changePhone/{id}")
    @Operation(
            operationId = "changePhoneNumber",
            summary = "change phone number",
            description = "this endpoint will be used to edit phone number"
    )
    @ApiResponse(
            responseCode = "200",
            description = "returns phone number changed successfully",
            content = @Content(mediaType = "application/json")
    )
    public ResponseEntity<Response> changeNumber(@PathVariable("id") String id, @RequestBody ChangePhoneNumberRequest changePhone) {
        try {
            return new ResponseEntity<>(profileService.changePhoneNumber(changePhone, id), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/setPin/{id}")
    @Operation(
            operationId = "ADDPIN",
            summary = "add phone pin",
            description = "this endpoint will be used to set pin"
    )
    @ApiResponse(
            responseCode = "200",
            description = "returns pin set successfully",
            content = @Content(mediaType = "application/json")
    )
    public ResponseEntity<Response> addPin(@PathVariable("id") String userId, @RequestBody PinRequest pinRequest) {

        try {
            return new ResponseEntity<>(profileService.pin(pinRequest, userId), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}