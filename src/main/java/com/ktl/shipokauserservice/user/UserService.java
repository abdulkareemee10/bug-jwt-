package com.ktl.shipokauserservice.user;

import com.ktl.shipokauserservice.Utils.Response;

import com.ktl.shipokauserservice.employeeSize.EmployeeSize;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class UserService {

//    @Autowired
//    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<Response> signUp(UserRequest userRequest) {
        //if the user exists - return error
        try {

            if (userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
                return ResponseEntity.badRequest().body(Response.builder()
                        .responseCode(400)
                        .responseMessage("Attempt to save duplicate email record")
                        .build());
            }
            if (!userRequest.getPassword().equals(userRequest.getConfirmPassword())) {
                return ResponseEntity.badRequest().body(Response.builder()
                        .responseCode(400)
                        .responseMessage("password does not match, please confirm password")
                        .build());
            }
            if (!userRequest.getIsTermAgreement()) {
                return ResponseEntity.badRequest().body(Response.builder()
                        .responseCode(400)
                        .responseMessage("agree with terms and condition")
                        .build());
            } else {
                final String ids = UUID.randomUUID().toString().replace("-", "");
                byte[] unhashed = Base64.getDecoder().decode(userRequest.getPassword());
                String unhashedPassword = new String(unhashed);
                User user = User.builder()
                        .userId(ids)
                        .email(userRequest.getEmail())
                        .password(passwordEncoder.encode(unhashedPassword))
                        .isTermAgreement(userRequest.getIsTermAgreement())
                        .dateCreated(LocalDateTime.now())
                        .createdBy("Gloria")
                        .dateUpdated(null)
                        .isEmailVerified(false)
                        .build();
                User savedUser = userRepository.save(user);
                return ResponseEntity.ok(Response.builder().responseCode(200).responseMessage("SUCCESS")
                        .build());
            }
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(Response.builder()
                    .responseCode(-2)
                    .responseMessage(e.getMessage())
                    .build());
        }
    }

    public ResponseEntity<List<User>> getAllUsers(){
        try{
            List<User> users = userRepository.findAll() ;
            return new ResponseEntity<>(users, HttpStatus.OK);
        }catch (Exception e){
            return null;
        }

    }

    public Response deleteUser(String id){

        Response response = new Response();
        try {
            User user = userRepository.findById(id).orElseThrow(() -> new

                    DataRetrievalFailureException("user not found"));
            userRepository.deleteById(id);
            response.setResponseCode(0);
            response.setResponseMessage("User deleted successfully");

        } catch (Exception e) {
            response.setResponseCode(-2);
            response.setResponseMessage(e.getMessage());
        }
        return response;

}
    }
    
    
//    Collection<? extends GrantedAuthority> mapRoleToAuthorities(Collection<Role> roles){
//        return  roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName()))
//                .collect(Collectors.toList());
//
//    }


  

