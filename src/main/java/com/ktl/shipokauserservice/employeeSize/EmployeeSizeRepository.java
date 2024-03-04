package com.ktl.shipokauserservice.employeeSize;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeSizeRepository extends JpaRepository<EmployeeSize, Long> {

    Optional<EmployeeSize> findByEmployeeSize(String employeeSize);
}
