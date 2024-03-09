package com.ktl.shipokauserservice.profile;
import com.ktl.shipokauserservice.CustomerEnquiry.CustomerEnquiryFile;
import com.ktl.shipokauserservice.Utils.Response;

import com.ktl.shipokauserservice.businessType.BusinessType;
import com.ktl.shipokauserservice.user.User;
import com.ktl.shipokauserservice.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.UUID;

@Service
public class ProfileService {
//
    @Autowired
    private UserRepository userRepository;

    private final String FOLDER_PATH = "C:/Users/USER/IdeaProjects/Shipoka/profile-picture/";



//    @Autowired
//    private UserCategoryRepository userCategoryRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



     public Response saveProfile(ProfileValidationRequest personalValidationRequest, String userId) {


             Response response = new Response();

                 User user = userRepository.findById(userId).orElseThrow(()
                         -> new DataRetrievalFailureException("user with id not found"));


//                   UserCategory userCat = userCategoryRepository.findByCategoryName(personalValidationRequest.getUserCategory()).get();
//           Long userCats = userCat.getUserCategoryId();
                    user.setUserCategoryId(personalValidationRequest.getUserCategoryId());
                    user.setFirstName(personalValidationRequest.getFirstName());
                    user.setLastName(personalValidationRequest.getLastName());
                    user.setPhoneCountryCode(personalValidationRequest.getPhoneCountryCode());
                    user.setProfilePicture(personalValidationRequest.getProfilePicture());
                    user.setPhoneNumber(personalValidationRequest.getPhoneNumber());
                    user.setContactPhoneNumber(personalValidationRequest.getContactPhoneNumber());
                    user.setContactEmailAddress(personalValidationRequest.getContactEmailAddress());
                    user.setContactLastName(personalValidationRequest.getContactLastName());
                    user.setContactFirstName(personalValidationRequest.getContactFirstName());
                    user.setContactPhoneCountryCode(personalValidationRequest.getContactPhoneCountryCode());
                    user.setIsPhoneNumberVerified(false);
                    user.setGender(personalValidationRequest.getGender());
                    user.setDateCreated(LocalDateTime.now());
                    userRepository.save(user);




//         user.builder()
//                 .userCategoryId(personalValidationRequest.getUserCategoryId())
//                 .firstName(personalValidationRequest.getFirstName())
//                 .lastName(personalValidationRequest.getLastName())
//                 .phoneCountryCode(personalValidationRequest.getPhoneCountryCode())
//                 .profilePicture(personalValidationRequest.getProfilePicture())
//                 .phoneNumber(personalValidationRequest.getPhoneNumber())
//                 .contactPhoneNumber(personalValidationRequest.getContactPhoneNumber())
//                 .contactEmailAddress(personalValidationRequest.getContactEmailAddress())
//                 .contactLastName(personalValidationRequest.getContactLastName())
//                 .contactFirstName(personalValidationRequest.getContactFirstName())
//                 .contactPhoneCountryCode(personalValidationRequest.getContactPhoneCountryCode())
//                 .isPhoneNumberVerified(false)
//                 .gender(personalValidationRequest.getGender())
//                 .dateCreated(LocalDateTime.now())
//                 .build();
//         userRepository.save(user);
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
         response.setResponseMessage("pin created successfully");
     } else {
         response.setResponseCode(-1);
         response.setResponseMessage("Problem creating pin");
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
    public String profilePicture(MultipartFile file, String userId) throws IOException {
        String filePath = FOLDER_PATH+ "/" + userId+file.getOriginalFilename();
        User user = userRepository.findByUserId(userId).get();
               user.setProfilePicture(filePath);
               userRepository.save(user);

        file.transferTo(new File(filePath));
        if( user != null){
            return "file uploaded successfully" + filePath;
        }else
            return "problem uploading file";

    }

    public byte[] downloadProfilePicture(String fileName) throws IOException {
        User user = userRepository.findByProfilePicture(fileName).get();
        String filePath = user.getProfilePicture();
        byte[] uploadedFile = Files.readAllBytes(new File(filePath).toPath());
        return uploadedFile;
    }
}

