package com.ktl.shipokauserservice.securityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                                .requestMatchers("/v2/api-docs",
                                        "/shipoka-user-service/swagger-resources",
                                        "/shipoka-user-service/swagger-resources/**",
                                        "/configuration/ui",
                                        "/configuration/security",
                                        "/shipoka-user-service/swagger-ui.html",
                                        "/webjars/**",
                                        "/v3/api-docs/**",
                                        "/shipoka-user-service/swagger-ui/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "swagger-ui/*").permitAll()
                                .requestMatchers(HttpMethod.POST, "api/v1/authenticate/signUp").permitAll()
                                .requestMatchers(HttpMethod.POST, "api/v1/otp/send").permitAll()
                                .requestMatchers(HttpMethod.POST, "api/v1/profile/setPin/{id}").permitAll()
                                .requestMatchers(HttpMethod.POST, "api/v1/profile/postPicture/{userId}").permitAll()
                                .requestMatchers(HttpMethod.GET, "api/v1/profile/download/{fileName}").permitAll()
                                .requestMatchers(HttpMethod.PUT, "api/v1/profile/changePhone/{id}").permitAll()
                                .requestMatchers(HttpMethod.POST, "api/v1/forgotPassword/sendOtpForgotPassword").permitAll()
                                .requestMatchers(HttpMethod.POST, "api/v1/forgotPassword/validateOtpForgotPassword").permitAll()
                                .requestMatchers(HttpMethod.PUT, "api/v1/forgotPassword/changePassword/{userId}").permitAll()
                                .requestMatchers(HttpMethod.POST, "api/v1/otp/validateOtp").permitAll()
                                .requestMatchers(HttpMethod.POST, "api/v1/userCategory/add").permitAll()
                                .requestMatchers(HttpMethod.POST, "api/v1/profile/post/{id}").permitAll()
                                .requestMatchers(HttpMethod.PUT, "api/v1/profile/{id}").permitAll()
                                .requestMatchers(HttpMethod.GET, "api/v1/userCategory/get").permitAll()
                                .requestMatchers(HttpMethod.PUT, "api/v1/userCategory/{id}").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "api/v1/userCategory/{id}").permitAll()
                                .requestMatchers(HttpMethod.POST, "api/v1/businessType/post").permitAll()
                                .requestMatchers(HttpMethod.POST, "api/v1/businessDetails/post").permitAll()
                                .requestMatchers(HttpMethod.GET, "api/v1/businessType/get").permitAll()
                                .requestMatchers(HttpMethod.PUT, "api/v1/businessType/{id}").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "api/v1/businessType/{id}").permitAll()
                                .requestMatchers(HttpMethod.POST, "api/v1/employeeSize/post").permitAll()
                                .requestMatchers(HttpMethod.POST, "api/v1/appReview/post").permitAll()
                                .requestMatchers(HttpMethod.GET, "api/v1/employeeSize/get").permitAll()
                                .requestMatchers(HttpMethod.GET, "api/login-details").permitAll()
                                .requestMatchers(HttpMethod.PUT, "api/v1/employeeSize/{id}").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "api/v1/employeeSize/{id}").permitAll()
                                .requestMatchers(HttpMethod.POST, "api/v1/customerEnquiryFile/post/{userId}").permitAll()
                                .requestMatchers(HttpMethod.POST, "api/v1/customerEnquiryTicketMessage/post").permitAll()
                                .requestMatchers(HttpMethod.POST, "api/v1/customerEnquiry/post").permitAll()
                                .requestMatchers(HttpMethod.GET, "api/v1/customerEnquiryTicketMessage/get/{customerEnquiryId}").permitAll()
                                .anyRequest().authenticated()
                        );
        return httpSecurity.build();

    }
}