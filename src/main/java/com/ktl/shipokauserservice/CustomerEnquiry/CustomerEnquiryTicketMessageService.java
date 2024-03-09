package com.ktl.shipokauserservice.CustomerEnquiry;

import com.ktl.shipokauserservice.Utils.Response;
import com.ktl.shipokauserservice.businessType.BusinessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CustomerEnquiryTicketMessageService {

    @Autowired
    private CustomerEnquiryTicketMessageRepository customerEnquiryTicketMessageRepository;

    public ResponseEntity<Response> saveCustomerTicketMessage(CustomerEnquiryTicketMessageRequest
                                              customerEnquiryTicketMessageRequest){
        final String ids = UUID.randomUUID().toString().replace("-", "");
        try {
            CustomerEnquiryTicketMessage customerEnquiryTicketMessage = new CustomerEnquiryTicketMessage();
            customerEnquiryTicketMessage.setMessageResponse(customerEnquiryTicketMessageRequest.getMessageResponse());
            customerEnquiryTicketMessage.setCustomerEnquiryTicketMessageId(ids);
            customerEnquiryTicketMessage.setMessageDateTime(LocalDateTime.now());
            customerEnquiryTicketMessage.setCustomerEnquiryId("12345");
            customerEnquiryTicketMessage.setIsAdmin(true);
            customerEnquiryTicketMessage.setRespondedByShipokaAdminId("12345");
            customerEnquiryTicketMessageRepository.save(customerEnquiryTicketMessage);
            return ResponseEntity.ok(Response.builder().responseCode(200).responseMessage("SUCCESS")
                    .build());
        }catch(Exception e){
            return ResponseEntity.ok(Response.builder().responseCode(-2).responseMessage(e.getMessage())
                    .build());
            }
        }

        public ResponseEntity<Response> getCustomerEnquiryMessageByCustomerEnquiryId(String customerEnquiryId){

                          Response response = new Response();
                try {
                    CustomerEnquiryTicketMessage customerEnquiryTicketMessage = customerEnquiryTicketMessageRepository.findByCustomerEnquiryId(customerEnquiryId).orElseThrow(()
                            -> new DataRetrievalFailureException("customer enquiry id not found"));

                    if (customerEnquiryTicketMessage!= null){
                        return ResponseEntity.ok(Response.builder().responseCode(200).responseMessage(customerEnquiryTicketMessage.getMessageResponse())
                                .build());
                      } else {
                        return ResponseEntity.ok(Response.builder().responseCode(200).responseMessage("Problem sending response over complaint")
                                .build());

                    }
                } catch (Exception e) {
                    return ResponseEntity.ok(Response.builder().responseCode(200).responseMessage(e.getMessage())
                            .build());
                }

        }
 }
