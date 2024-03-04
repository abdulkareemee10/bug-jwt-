package com.ktl.shipokauserservice.businessType;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusinessTypeRepository extends JpaRepository<BusinessType, Long> {
    Optional<BusinessType> findByBusinessType (String businessType);
}
