package com.ktl.shipokauserservice.CustomerEnquiry;


import com.ktl.shipokauserservice.Utils.Response;
import com.ktl.shipokauserservice.employeeSize.EmployeeSize;
import com.ktl.shipokauserservice.employeeSize.EmployeeSizeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnquiryTypeService {

    @Autowired
    private EnquiryTypeRepository enquiryTypeRepository;

    public Response saveEnquiryType(EnquiryTypeRequest enquiryTypeRequest) {

                    Response response = new Response();

            try{
                EnquiryType enquiryType = new EnquiryType();
                enquiryType.setEnquiryType(enquiryTypeRequest.getEnquiryType());

                enquiryTypeRepository.save(enquiryType);
                if ( enquiryTypeRepository.save(enquiryType) != null) {
                    response.setResponseCode(0);
                    response.setResponseMessage("enquiry type successfully saved.");
                } else {
                    response.setResponseCode(-1);
                    response.setResponseMessage("Problem saving enquiry type.");
                }
            } catch (Exception e) {
                response.setResponseCode(-2);
                response.setResponseMessage(e.getMessage());
            }
            return response;
        }

        public ResponseEntity<List<EnquiryType>> readEnquiryType(){
            try{
                List<EnquiryType> enquiryTypes = enquiryTypeRepository.findAll() ;
                return new ResponseEntity<>(enquiryTypes, HttpStatus.OK);
            }catch (Exception e){
                return null;
            }

        }

        public Response updateEnquiryTpe(EnquiryTypeRequest enquiryTypeRequest, Integer id) {
            Response response = new Response();
            try {
                EnquiryType existingEnquiryType = enquiryTypeRepository.findById(id).orElseThrow(()
                        -> new DataRetrievalFailureException("enquiry type with id: "+ id + " Not Found"));

                existingEnquiryType.setEnquiryType(enquiryTypeRequest.getEnquiryType());


                enquiryTypeRepository.save(existingEnquiryType);
                if (enquiryTypeRepository.save(existingEnquiryType) != null){
                    response.setResponseCode(0);
                    response.setResponseMessage("enquiry type updated successfully");
                } else {
                    response.setResponseCode(-1);
                    response.setResponseMessage("problem updating enquiry type. ");
                }

            } catch (Exception e) {
                response.setResponseCode(-2);
                response.setResponseMessage(e.getMessage());
            }
            return response;
        }

        public Response deleteEnquiryType(Integer id) {
            Response response = new Response();

            try{ EnquiryType enquiryType = enquiryTypeRepository.findById(id).orElseThrow(()
                    -> new DataRetrievalFailureException("enquiry type Not Found"));
                enquiryTypeRepository.deleteById(id);
                response.setResponseCode(0);
                response.setResponseMessage("enquiry type deleted successfully");

            } catch (Exception e) {
                response.setResponseCode(-2);
                response.setResponseMessage(e.getMessage());
            }
            return response;
        }
}
