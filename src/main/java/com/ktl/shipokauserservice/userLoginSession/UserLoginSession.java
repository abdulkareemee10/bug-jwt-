package com.ktl.shipokauserservice.userLoginSession;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserLoginSession {
    @Id
    private String userSessionId;
    private String userId;
    private LocalDateTime loginDate;
    private String deviceUsedToLogin;
    private String IpAddress;
    private String CountryOfLoginCountryId;
    private LocalDateTime dateCreated;
    private String createdBy;
    private  LocalDateTime createdDate;
    private String updatedBy;
    private LocalDateTime dateUpdated;
    private String voidedBy;
    private LocalDateTime dateVoided;
    private Boolean voided;

}
