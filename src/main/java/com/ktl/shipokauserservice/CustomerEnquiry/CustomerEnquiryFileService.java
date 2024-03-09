package com.ktl.shipokauserservice.CustomerEnquiry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CustomerEnquiryFileService {

    @Autowired
    private CustomerEnquiryFileRepository customerEnquiryFileRepository;



    private final String FOLDER_PATH = "C:/Users/USER/IdeaProjects/Shipoka/uploaded-files/";

    public String uploadFile(MultipartFile file, String userId) throws IOException {
        String filePath = FOLDER_PATH+file.getOriginalFilename();
        final String ids = UUID.randomUUID().toString().replace("-", "");
       CustomerEnquiryFile customerEnquiryFile = new CustomerEnquiryFile();
               customerEnquiryFile.setCustomerEnquiryFileId(ids);
               customerEnquiryFile.setDateCreated(LocalDateTime.now());
               customerEnquiryFile.setCustomerEnquiryTicketMessageId("123456h679g789007f67fg");
               customerEnquiryFile.setCustomerEnquiryId("4de8ddba697c4e3882463e3ee5cdb052");
               customerEnquiryFile.setCustomerEnquiryFileName(file.getOriginalFilename());
               customerEnquiryFile.setUrl(filePath);
               customerEnquiryFileRepository.save(customerEnquiryFile);

       file.transferTo(new File(filePath));
       if( customerEnquiryFile != null){
           return "file uploaded successfully" + filePath;
       }
       return "problem uploading file";

    }

    public byte[] downloadFile (String fileName) throws IOException {
        CustomerEnquiryFile customerEnquiryFile = customerEnquiryFileRepository.findByCustomerEnquiryFileName(fileName).get();
          String filePath = customerEnquiryFile.getUrl();
        byte[] uploadedFile = Files.readAllBytes(new File(filePath).toPath());
        return uploadedFile;
    }
}
