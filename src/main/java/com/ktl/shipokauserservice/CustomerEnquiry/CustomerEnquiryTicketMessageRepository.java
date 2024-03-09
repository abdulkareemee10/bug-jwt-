package com.ktl.shipokauserservice.CustomerEnquiry;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerEnquiryTicketMessageRepository extends JpaRepository<CustomerEnquiryTicketMessage, String> {

    Optional<CustomerEnquiryTicketMessage> findByCustomerEnquiryId(String customerEnquiryId);
}
