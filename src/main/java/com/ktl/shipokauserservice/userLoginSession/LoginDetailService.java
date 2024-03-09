package com.ktl.shipokauserservice.userLoginSession;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class LoginDetailService {

    @Autowired
    private UserLoginSessionRepository userLoginSessionRepository;

    public String extractDevice(HttpServletRequest request) {
        return request.getHeader("User-Agent");
    }

    // You might need to handle proxies and load balancers properly to get the real IP address
    public String extractIpAddress(HttpServletRequest request) {
        return request.getRemoteAddr();
    }

    // Implement logic to retrieve location information based on IP address
//    public Optional<String> extractLocation(String ipAddress) {
//        // Use a third-party service or API to retrieve location information
//        // This might involve making an HTTP request to a geolocation service
//        // and parsing the response to extract location details
//     //        return Optional.empty();
//    }

    public void printLoginDetails(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String device = extractDevice(request);
        String ipAddress = extractIpAddress(request);
//        Optional<String> location = extractLocation(ipAddress);

//        System.out.println("User: " + authentication.getName());
        System.out.println("Device: " + device);
        System.out.println("IP Address: " + ipAddress);

        UserLoginSession userLoginSession = new UserLoginSession();
        userLoginSession.setUserSessionId(UUID.randomUUID().toString().replace("-", ""));
        userLoginSession.setUserId("2a2a163bd9e044929d60a8518bc9b6af");
        userLoginSession.setDeviceUsedToLogin(device);
        userLoginSession.setLoginDate(LocalDateTime.now());
        userLoginSession.setIpAddress(ipAddress);
        userLoginSession.setCreatedDate(LocalDateTime.now());
        userLoginSession.setCountryOfLoginCountryId("japan");
        userLoginSessionRepository.save(userLoginSession);
//        location.ifPresent(l -> System.out.println("Location: " + l));
    }
}
