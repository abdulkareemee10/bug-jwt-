package com.ktl.shipokauserservice.forgotPassword;


import com.ktl.shipokauserservice.Utils.AppUtils;
import com.ktl.shipokauserservice.Utils.Response;
import com.ktl.shipokauserservice.otp.*;

import com.ktl.shipokauserservice.user.UserResponse;
import com.ktl.shipokauserservice.user.User;
import com.ktl.shipokauserservice.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class ForgotPasswordService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final ForgotPasswordRepository forgotPasswordRepository;

    public Response sendOtpForgotPassword(ForgotPasswordRequest forgotPasswordRequest) {
        //generate the otp
        //send the otp
        //save the otp
       try{
        Optional<User> existingEmail = userRepository.findByEmail(forgotPasswordRequest.getEmail());

        if (!existingEmail.isPresent()) {
            return Response.builder().responseCode(400)
                    .responseMessage("the entered email is not linked to an active account").build();
        }
        if (existingEmail.isPresent()) {


            String otp = AppUtils.generateOtp();
            //ideally,  an otp should not be logged because you dont want to expose an otp to the terminal
            final String ids = UUID.randomUUID().toString().replace("-", "");
            String userID = existingEmail.get().getUserId();
            log.info("Otp:{}", otp);
            forgotPasswordRepository.save(ForgotPassword.builder()
                    .forgotPasswordId(ids)
                    .email(forgotPasswordRequest.getEmail())
                    .userId(userID)
                    .isOtpExpired(false)
                    .isOtpUsed(false)
                    .isOtpExpired(false)
                    .verificationCode(otp)
                    .timeOtpCreated(LocalDateTime.now()).build());

            emailService.sendEmail(EmailDetails.builder()
                    .subject("testing otp").recipient(forgotPasswordRequest.getEmail())
                    .messageBody("hello , " + existingEmail.get().getEmail() + " shipoka has sent you an otp, " +
                            "it takes only two minutes" +
                            " before it expires  " + otp).build());
            return Response.builder().responseCode(200).responseMessage(otp).build();

        }
        return Response.builder().responseCode(400).responseMessage("you need to enter the email " +
                "you used to signup " +
                "before you can have an otp ").build();
    }catch (Exception e){
            Response.builder().responseCode(400).responseMessage(e.getMessage());
        }
        return Response.builder().build();
    }

    //ascertain that the user actually sent an otp
    //ascertain that the otp has not expired
    //ascertain that the otp is correct
    //check if the email has an otp
//
    public Response validateOtpForgotPassword(ForgotPasswordValidationRequest forgotPasswordValidationRequest) {


        Response response = new Response();
        try {


            ForgotPassword forgotPassword = forgotPasswordRepository.findByEmailAndVerificationCode(forgotPasswordValidationRequest.getEmail(), forgotPasswordValidationRequest.getOtp()).get();
            log.info("Email:{}", forgotPasswordValidationRequest.getEmail());
            LocalDateTime expiresAt = forgotPassword.getTimeOtpCreated().plusMinutes(1);
//            if (!otp.getVerificationCode().equals(otpValidationRequest.getOtp())) {
//                return Response.builder().responseCode(400)
//                        .responseMessage("invalid otp").build();
//            }

            if (expiresAt.isBefore(LocalDateTime.now())) {
                forgotPassword.setIsOtpExpired(true);
                forgotPasswordRepository.save(forgotPassword);
                return Response.builder().responseCode(400).responseMessage("the otp has expired").build();

            } else {

                forgotPassword.setOtpUsedTime(LocalDateTime.now());
                forgotPassword.setIsOtpUsed(true);
                forgotPassword.setIsPasswordChanged(false);
                forgotPasswordRepository.save(forgotPassword);

                User user = userRepository.findByEmail(forgotPasswordValidationRequest.getEmail()).get();
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





    public Response changePassword(ForgotPasswordRequest forgotPasswordRequest, String userId) {
        Response response = new Response();
        try {
            User existingUser = userRepository.findById(userId).orElseThrow(()
                    -> new DataRetrievalFailureException("not found"));
            byte[] unhashed = Base64.getDecoder().decode(forgotPasswordRequest.getPassword());
            String unhashedPassword = new String(unhashed);
            if (forgotPasswordRequest.getPassword().equals(forgotPasswordRequest.getConfirmPassword())) {
                ForgotPassword forgotPassword = forgotPasswordRepository.findByUserIdAndIsOtpUsed(userId, true).get();

                existingUser.setPassword(passwordEncoder.encode(unhashedPassword));
                forgotPassword.setIsPasswordChanged(true);
                forgotPassword.setEmail(forgotPasswordRequest.getEmail());
                forgotPassword.setTimePasswordChanged(LocalDateTime.now());
                response.setResponseCode(200);
                response.setResponseMessage("SUCCESS");
                forgotPasswordRepository.save(forgotPassword);
                userRepository.save(existingUser);
            } else {
                response.setResponseCode(400);
                response.setResponseMessage("password does not match");
            }
        } catch (Exception e) {
            response.setResponseCode(-1);
            response.setResponseMessage(e.getMessage());
        }
        return response;
    }
}


