package com.ktl.shipokauserservice.usercategory;


import com.ktl.shipokauserservice.Utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "UserCategory", description = "User Category APIs")
@RestController
@RequestMapping("api/v1/userCategory")
public class UserCategoryController {

    @Autowired
    private UserCategoryService userCategoryService;

    @PostMapping("/add")
    @Operation(
            operationId = "addUserCategory",
            summary = "add user category",
            description = "this endpoint is to add/initiate user categories (personal and business)"
    )
    @ApiResponse(
            responseCode = "200",
            description = "returns user category successfully saved",
            content = @Content(mediaType = "application/json")
    )
    public Response addCategory(@RequestBody UserCategoryRequest userCategoryRequest) {
        return userCategoryService.addCategory(userCategoryRequest);

    }

    @GetMapping("/get")
    @Operation(
            operationId = "getUserCategory",
            summary = "get all user categories",
            description = "this endpoint is to get all the saved categories (personal and business)"
    )
    @ApiResponse(
            responseCode = "200",
            description = "returns a list of user category",
            content = @Content(mediaType = "application/json")
    )
    public ResponseEntity<List<UserCategory>> getCategory() {
        return userCategoryService.getAllCategories();

    }

    @PutMapping("/{id}")
    @Operation(
            operationId = "updateUserCategory",
            summary = "update user category",
            description = "this endpoint is to edit already saved user category (personal and business)"
    )
    @ApiResponse(
            responseCode = "200",
            description = "returns user category successfully updated",
            content = @Content(mediaType = "application/json")
    )

    public Response updateUserCategory(@PathVariable("id") Long id, @RequestBody UserCategoryRequest userCategoryRequest) {

        return userCategoryService.updateCategory(userCategoryRequest, id);
    }

    @DeleteMapping("/{id}")
    @Operation(
            operationId = "deleteUserCategory",
            summary = "delete user category",
            description = "this endpoint is to delete a user category (personal and business)"
    )
    @ApiResponse(
            responseCode = "200",
            description = "deletes a saved user category",
            content = @Content(mediaType = "application/json")
    )
    public Response deleteUserCategory(@PathVariable("id") Long id) {
        return  userCategoryService.deleteUserCategory(id);
       }
}
