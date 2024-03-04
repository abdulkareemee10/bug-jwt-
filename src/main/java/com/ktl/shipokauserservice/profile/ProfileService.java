package com.ktl.shipokauserservice.profile;
import com.ktl.shipokauserservice.Utils.Response;

import com.ktl.shipokauserservice.user.User;
import com.ktl.shipokauserservice.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.UUID;

@Service
public class ProfileService {
//
    @Autowired
    private UserRepository userRepository;



//    @Autowired
//    private UserCategoryRepository userCategoryRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



     public Response saveProfile(ProfileValidationRequest personalValidationRequest) {
         Response response = new Response();
//                   UserCategory userCat = userCategoryRepository.findByCategoryName(personalValidationRequest.getUserCategory()).get();
//           Long userCats = userCat.getUserCategoryId();
         User currentUser1 = userRepository.findByEmail(personalValidationRequest.getEmail()).get();
         String currentUserID = currentUser1.getUserId();
         User user = User.builder()
                 .userId(currentUserID)
                 .userCategoryId(personalValidationRequest.getUserCategoryId())
                 .firstName(personalValidationRequest.getFirstName())
                 .lastName(personalValidationRequest.getLastName())
                 .phoneCountryCode(personalValidationRequest.getPhoneCountryCode())
                 .profilePicture(personalValidationRequest.getProfilePicture())
                 .phoneNumber(personalValidationRequest.getPhoneNumber())
                 .contactPhoneNumber(personalValidationRequest.getContactPhoneNumber())
                 .contactEmailAddress(personalValidationRequest.getContactEmailAddress())
                 .contactLastName(personalValidationRequest.getContactLastName())
                 .contactFirstName(personalValidationRequest.getContactFirstName())
                 .contactPhoneCountryCode(personalValidationRequest.getContactPhoneCountryCode())
                 .isPhoneNumberVerified(false)
                 .gender(personalValidationRequest.getGender())
                 .dateCreated(LocalDateTime.now())
                 .build();
         userRepository.save(user);
       try {
           if (userRepository.save(user) != null) {

               response.setResponseCode(0);
               response.setResponseMessage("Profile successfully saved.");
           } else {
               response.setResponseCode(-1);
               response.setResponseMessage("Problem saving profile.");
           }
       }
     catch (Exception e) {
        response.setResponseCode(-2);
        response.setResponseMessage(e.getMessage());
    }
        return response;
}

     public Response pin(PinRequest pinRequest, String userId){
Response response= new Response();
     try{
     if (pinRequest.getPin().equals(pinRequest.getConfirmPin())){
         byte[] unhashed = Base64.getDecoder().decode(pinRequest.getPin());
         String unhashedPassword = new String(unhashed);
              User user =  userRepository.findByUserId(userId).get();
             user.setPin(passwordEncoder.encode(unhashedPassword));
             userRepository.save(user);
         response.setResponseCode(0);
         response.setResponseMessage("pin changed successfully");
     } else {
         response.setResponseCode(-1);
         response.setResponseMessage("Problem changing pin");
     }
     } catch (Exception e) {
         response.setResponseCode(-2);
         response.setResponseMessage(e.getMessage());
     }
         return response;
     }

//     public ResponseEntity<String> save(Profile profile){
//         final String ids = UUID.randomUUID().toString().replace("-", "");
//         profile.setProfileId(ids);
//         profileRepository.save(profile);
//         return new ResponseEntity<>( "New pin successfully created", HttpStatus.CREATED);
//     }

    public Response changePhoneNumber(ChangePhoneNumberRequest changePhone, String id) {
        Response response = new Response();
        try {
            User existingProfile = userRepository.findById(id).orElseThrow(()
                    -> new DataRetrievalFailureException("not found"));

            existingProfile.setPhoneNumber(changePhone.getPhoneNumber());
            userRepository.save(existingProfile);
            if (userRepository.save(existingProfile) != null) {
                response.setResponseCode(0);
                response.setResponseMessage("phone number changed successfully");
            } else {
                response.setResponseCode(-1);
                response.setResponseMessage("Problem changing phone number");
            }
        } catch (Exception e) {
            response.setResponseCode(-2);
            response.setResponseMessage(e.getMessage());
        }

         return response;
}
}

