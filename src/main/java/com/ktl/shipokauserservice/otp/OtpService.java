package com.ktl.shipokauserservice.otp;


import com.ktl.shipokauserservice.Utils.AppUtils;
import com.ktl.shipokauserservice.Utils.Response;
import com.ktl.shipokauserservice.user.User;
import com.ktl.shipokauserservice.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class OtpService {


    private final OtpRepository otpRepository;

    private final UserRepository userRepository;

    private final EmailService emailService;

    public ResponseEntity<Response> sendOtp(OtpRequest otpRequest) {
        //generate the otp
        //send the otp
        //save the otp
        //to have just one otp saved for one email * even if multiple otp is sent to this same email, only one is saved
        try {

            Optional<User> existingEmail = userRepository.findByEmail(otpRequest.getEmail());
            if (existingEmail.isPresent()) {


                String otp = AppUtils.generateOtp();
                //ideally,  an otp should not be logged because you dont want to expose an otp to the terminal

//            String existingID = existingEmail.get().getId();
//            if (existingOtp.getDateCreated().isBefore(LocalDateTime.now().plusMinutes(10))){
//                otpRepository.save(Otp.builder().
//                        isCodeExpired(true).build());
//            }

                log.info("Otp:{}", otp);

                final String ids = UUID.randomUUID().toString().replace("-", "");
                Otp saveOtp = new Otp();
                saveOtp.setOtpId(ids);
                saveOtp.setUserId("87a5d9ba945049818fa399aad7083eae");
                saveOtp.setEmail(otpRequest.getEmail());
                saveOtp.setVerificationCode(otp);
                saveOtp.setTimeCodeCreated(LocalDateTime.now());
                saveOtp.setIsCodeExpired(false);
                saveOtp.setIsVerificationCodeUsed(false);
                saveOtp.setDateCreated(LocalDateTime.now());
                otpRepository.save(saveOtp);

                emailService.sendEmail(EmailDetails.builder()
                        .subject("testing otp").recipient(otpRequest.getEmail())
                        .messageBody("hello , " + existingEmail.get().getEmail() + "  shipoka has sent you an otp, " +
                                "it takes only two minutes" +
                                " before it expires  " + otp).build());
                return ResponseEntity.ok().body(Response.builder().responseCode(200).responseMessage("SUCCESS")
                        .build());

            } else {
                return ResponseEntity.badRequest().body(Response.builder()
                        .responseCode(400).
                        responseMessage("you need to enter the email " +
                                "you used to signup " +
                                "before you can have an otp ")
                        .build());
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Response.builder()
                    .responseCode(-2)
                    .responseMessage(e.getMessage())
                    .build());
        }
    }


    //ascertain that the user actually sent an otp
    //ascertain that the otp has not expired
    //ascertain that the otp is correct
    //check if the email has an otp
//
    public Response validateOtp(OtpValidationRequest otpValidationRequest) {

        Response response = new Response();
        try {


            Otp otp = otpRepository.findByEmailAndVerificationCode(otpValidationRequest.getEmail(), otpValidationRequest.getOtp()).get();
            log.info("Email:{}", otpValidationRequest.getEmail());
            LocalDateTime expiresAt = otp.getTimeCodeCreated().plusMinutes(1);
//            if (!otp.getVerificationCode().equals(otpValidationRequest.getOtp())) {
//                return Response.builder().responseCode(400)
//                        .responseMessage("invalid otp").build();
//            }

            if (expiresAt.isBefore(LocalDateTime.now())) {
                otp.setIsCodeExpired(true);
                otpRepository.save(otp);
                return Response.builder().responseCode(400).responseMessage("the otp has expired").build();

            } else {

                otp.setVerificationUsedTime(LocalDateTime.now());
                otp.setIsVerificationCodeUsed(true);
                otpRepository.save(otp);

                User user = userRepository.findByEmail(otpValidationRequest.getEmail()).get();
                user.setIsEmailVerified(true);
                userRepository.save(user);

                return Response.builder().responseCode(200).responseMessage("SUCCESS")
                        .build();

            }
        } catch (Exception e) {
            return Response.builder().responseCode(-2).responseMessage(e.getMessage())
                    .build();
        }

    }

}
