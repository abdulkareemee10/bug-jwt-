package com.ktl.shipokauserservice.businessType;


import com.ktl.shipokauserservice.Utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessTypeService {

    @Autowired
    private BusinessTypeRepository businessTypeRepository;

    public Response saveBusinessType(BusinessTypeRequest businessTypeRequest){
        Response response = new Response();
        try{
        BusinessType businessType1 = new BusinessType();
        businessType1.setBusinessType(businessTypeRequest.getBusinessType());
        businessTypeRepository.save(businessType1);
        if(businessTypeRepository.save(businessType1) != null){
            response.setResponseCode(0);
            response.setResponseMessage("business type successfully saved.");
        } else {
            response.setResponseCode(-1);
            response.setResponseMessage("Problem saving business type.");
        }
        } catch (Exception e) {
            response.setResponseCode(-2);
            response.setResponseMessage(e.getMessage());
        }
        return response;
    }


    public ResponseEntity<List<BusinessType>> readBusinessType(){
        try {
            List<BusinessType> businessTypes = businessTypeRepository.findAll();
            return new ResponseEntity<>(businessTypes, HttpStatus.OK);
        }catch (Exception e){
            return null;
        }
    }

    public Response updateBusinessType(BusinessType businessType, Long id) {
        Response response = new Response();
        try {
            BusinessType existingBusinessType = businessTypeRepository.findById(id).orElseThrow(()
                    -> new DataRetrievalFailureException("Business type not found"));

            existingBusinessType.setBusinessType(businessType.getBusinessType());
            businessTypeRepository.save(existingBusinessType);
            if (businessTypeRepository.save(existingBusinessType) != null){
                response.setResponseCode(0);
                response.setResponseMessage("Business type successfully saved.");
            } else {
                response.setResponseCode(-1);
                response.setResponseMessage("Problem saving business type.");
            }
        } catch (Exception e) {
            response.setResponseCode(-2);
            response.setResponseMessage(e.getMessage());
        }
        return response;
    }

    public Response deleteBusinessById(Long id) {
        Response response = new Response();

        try{
            BusinessType businessType = businessTypeRepository.findById(id).orElseThrow(()
                    -> new DataRetrievalFailureException("Business type Not Found"));

        businessTypeRepository.deleteById(id);
            response.setResponseCode(0);
            response.setResponseMessage("Business type deleted successfully");

        } catch (Exception e) {
            response.setResponseCode(-2);
            response.setResponseMessage(e.getMessage());
        }
        return response;

}

}
