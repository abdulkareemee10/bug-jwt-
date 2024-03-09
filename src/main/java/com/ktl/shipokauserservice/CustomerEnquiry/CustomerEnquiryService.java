package com.ktl.shipokauserservice.CustomerEnquiry;

import com.ktl.shipokauserservice.Utils.Response;
import com.ktl.shipokauserservice.user.User;
import com.ktl.shipokauserservice.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CustomerEnquiryService {

    @Autowired
    private CustomerEnquiryRepository customerEnquiryRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<Response> saveCustomerEnquiry(CustomerEnquiryRequest customerEnquiryRequest) {

        try {
            CustomerEnquiry customerEnquiry = new CustomerEnquiry();
            final String ids = UUID.randomUUID().toString().replace("-", "");

            if (userRepository.findByEmail(customerEnquiryRequest.getEmail()).isPresent()) {

                User user = userRepository.findByEmail(customerEnquiryRequest.getEmail()).get();
                customerEnquiry.setCustomerEnquiryId(ids);
                customerEnquiry.setEnquiryTypeId(customerEnquiryRequest.getEnquiryTypeId());
                customerEnquiry.setCustomerId(user.getUserId());
                customerEnquiry.setIsRegisteredCustomer(true);
                customerEnquiry.setEnquiryFileId(customerEnquiryRequest.getEnquiryFileId());
                customerEnquiry.setDateCreated(LocalDateTime.now());
                customerEnquiry.setEmail(customerEnquiryRequest.getEmail());
                customerEnquiry.setSubject(customerEnquiryRequest.getSubject());
                customerEnquiry.setMessage(customerEnquiryRequest.getMessage());
                customerEnquiry.setEnquiryStatus(customerEnquiryRequest.getEnquiryStatus());
                customerEnquiryRepository.save(customerEnquiry);
                return ResponseEntity.ok(Response.builder().responseCode(200).responseMessage("SUCCESS")
                        .build());
            }

           else  {
                customerEnquiry.setCustomerEnquiryId(ids);
                customerEnquiry.setFirstName(customerEnquiryRequest.getFirstName());
                customerEnquiry.setLastName(customerEnquiryRequest.getLastName());
                customerEnquiry.setMiddleName(customerEnquiryRequest.getMiddleName());
                customerEnquiry.setPhoneNumber(customerEnquiryRequest.getPhoneNumber());
                customerEnquiry.setEmail(customerEnquiryRequest.getEmail());
                customerEnquiry.setDateCreated(LocalDateTime.now());
                customerEnquiry.setIsRegisteredCustomer(false);
                customerEnquiry.setSubject(customerEnquiryRequest.getSubject());
                customerEnquiry.setMessage(customerEnquiryRequest.getMessage());
                customerEnquiry.setEnquiryStatus(customerEnquiryRequest.getEnquiryStatus());
                customerEnquiryRepository.save(customerEnquiry);
                return ResponseEntity.ok(Response.builder().responseCode(200).responseMessage("SUCCESS")
                        .build());

            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Response.builder()
                    .responseCode(-2)
                    .responseMessage(e.getMessage())
                    .build());

        }

       }

}
