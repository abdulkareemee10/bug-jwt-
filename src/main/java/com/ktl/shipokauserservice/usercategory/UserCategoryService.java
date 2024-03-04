package com.ktl.shipokauserservice.usercategory;


import com.ktl.shipokauserservice.Utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCategoryService {

    @Autowired
    private UserCategoryRepository userCategoryRepository;

    public Response addCategory(UserCategoryRequest userCategoryRequest) {
        Response response = new Response();
        try {
            UserCategory userCategory = new UserCategory();
            userCategory.setCategoryName(userCategoryRequest.getCategoryName());
            userCategoryRepository.save(userCategory);

            if (userCategoryRepository.save(userCategory) != null) {
                response.setResponseCode(0);
                response.setResponseMessage("User Category Successfully Saved");
            } else {
                response.setResponseCode(-1);
                response.setResponseMessage("Problem Saving User Category. ");
            }
        } catch (Exception e) {
            response.setResponseCode(-2);
            response.setResponseMessage(e.getMessage());
        }
        return response;
    }

    public ResponseEntity<List<UserCategory>> getAllCategories() {
        try {
            List<UserCategory> userCategory = userCategoryRepository.findAll();
            return new ResponseEntity<>(userCategory, HttpStatus.OK);
        } catch (Exception e) {
            return null;
        }
    }

    public Response updateCategory(UserCategoryRequest userCategoryRequest, Long id) {
        Response response = new Response();
        try {
            UserCategory existingCategory = userCategoryRepository.findById(id).orElseThrow(()
                    -> new DataRetrievalFailureException("user category not found"));

            existingCategory.setCategoryName(userCategoryRequest.getCategoryName());

            if (userCategoryRepository.save(existingCategory) != null) {
                response.setResponseCode(0);
                response.setResponseMessage("User Category edited successfully");
            } else {
                response.setResponseCode(-1);
                response.setResponseMessage("Problem editing user category. ");
            }

        } catch (Exception e) {
            response.setResponseCode(-2);
            response.setResponseMessage(e.getMessage());
        }
        return response;
    }

    public Response deleteUserCategory(Long id) {
        Response response = new Response();
        try {
            UserCategory userCategory = userCategoryRepository.findById(id).orElseThrow(() -> new

                    DataRetrievalFailureException("user category not found"));
            userCategoryRepository.deleteById(id);
            response.setResponseCode(0);
            response.setResponseMessage("User Category deleted successfully");

        } catch (Exception e) {
            response.setResponseCode(-2);
            response.setResponseMessage(e.getMessage());
        }
        return response;
    }
}
