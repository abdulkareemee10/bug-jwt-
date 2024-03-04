package com.ktl.shipokauserservice.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
    private int statusCode;
    private String responseMessage;
//    private Long id;
//    private String email;
//    private String password;
//    private String firstname;
//    private String lastName;
//    @CreationTimestamp
//    private LocalDateTime dateCreated;
//    @CreationTimestamp
//    private LocalDateTime modifiedAt;
}
