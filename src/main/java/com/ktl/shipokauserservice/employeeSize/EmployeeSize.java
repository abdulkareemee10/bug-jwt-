package com.ktl.shipokauserservice.employeeSize;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity

@Data
public class EmployeeSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeSizeId;
    private String employeeSize;
}
