package com.ktl.shipokauserservice.CustomerEnquiry;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerEnquiryFileRepository extends JpaRepository<CustomerEnquiryFile, String > {

    Optional<CustomerEnquiryFile> findByCustomerEnquiryFileName(String fileName);
}
