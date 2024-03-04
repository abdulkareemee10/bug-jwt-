package com.ktl.shipokauserservice.businessDetails;



import com.ktl.shipokauserservice.Utils.Response;
import com.ktl.shipokauserservice.businessType.BusinessType;
import com.ktl.shipokauserservice.businessType.BusinessTypeRepository;
import com.ktl.shipokauserservice.employeeSize.EmployeeSize;
import com.ktl.shipokauserservice.employeeSize.EmployeeSizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class BusinessDetailsService {

    @Autowired
    private BusinessDetailsRepository businessDetailsRepository;

    @Autowired
    private EmployeeSizeRepository employeeSizeRepository;

    @Autowired
    private BusinessTypeRepository businessTypeRepository;



    public Response saveBusinessDetails(BusinessDetailsRequest businessDetailsRequest){

//        BusinessType businessType = businessTypeRepository.findByBusinessType(businessDetailsRequest.getBusinessType()).get();
//        Long businessTypeId = businessType.getBusinessTypeId();
//
//        EmployeeSize employeeSize = employeeSizeRepository.findByEmployeeSize(businessDetailsRequest.getEmployeeSize()).get();
//        Long employeSize = employeeSize.getEmployeeSizeId();

        Response response = new Response();
      try{ BusinessDetails businessDetails  = BusinessDetails.builder()
               .businessDetailsId(UUID.randomUUID().toString().replace("-", ""))
               .businessRegistrationNumber(businessDetailsRequest.getBusinessRegistrationNumber())
               .profileId("d3558beba006408b935e0f063db426d2")
               .businessTypeId(businessDetailsRequest.getBusinessTypeId())
               .employeeSizeId(businessDetailsRequest.getEmployeeSizeId())
               .businessName(businessDetailsRequest.getBusinessName())
               .createdBy("Gloria")
               .dateCreated(LocalDateTime.now())
        .build();
        businessDetailsRepository.save(businessDetails);
        if ( businessDetailsRepository.save(businessDetails) != null){
            response.setResponseCode(0);
            response.setResponseMessage("Business details successfully saved.");
        } else {
            response.setResponseCode(-1);
            response.setResponseMessage("Problem saving business details.");
        }
      } catch (Exception e) {
          response.setResponseCode(-2);
          response.setResponseMessage(e.getMessage());
      }
        return response;
    }


}
