package com.ktl.shipokauserservice.user;




import com.ktl.shipokauserservice.Utils.Response;
import com.ktl.shipokauserservice.businessType.BusinessType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User", description = "User Registration APIs")
@Controller
@RequestMapping("api/v1/authenticate")

public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signUp")
    @Operation(
            operationId = "userSignUp",
            summary = "save signed up user",
            description = "this endpoint is to save signed up user"
    )
    @ApiResponse(
            responseCode = "200",
            description = "returns user signed up successfully",
            content = @Content(mediaType = "application/json")
    )
    public ResponseEntity<Response> signup(
            @RequestBody UserRequest userRequest) {
        try {
            return userService.signUp(userRequest);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getUsers")
    @Operation(
            operationId = "getSignedUpUsers",
            summary = "get signed up users",
            description = "this endpoint is to all signed up users"
    )
    @ApiResponse(
            responseCode = "200",
            description = "returns list of signed up user",
            content = @Content(mediaType = "application/json")
    )
    public ResponseEntity<List<User>> getAllUsers() {
        return userService.getAllUsers();
    }


    @DeleteMapping("/deleteUsers")
    @Operation(
            operationId = "deleteSignedUpUser",
            summary = "delete signed up user",
            description = "this endpoint is to delete signed up user"
    )
    @ApiResponse(
            responseCode = "200",
            description = "returns signed up user deleted successfully",
            content = @Content(mediaType = "application/json")
    )
    public ResponseEntity<Response> deleteUser(String id){

     try {
        return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.ACCEPTED);
    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

}
