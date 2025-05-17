package com.souptik.pepsales.Notification.controllers;


import com.souptik.pepsales.Notification.helper.CustomResponse;
import com.souptik.pepsales.Notification.helper.EmailRequest;
import com.souptik.pepsales.Notification.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest emailRequest) {

        emailService.sendHtmlEmail(emailRequest.getTo(), emailRequest.getCc(), emailRequest.getSubject(), emailRequest.getMessage()); // You'll need to adjust your service method signature
        return ResponseEntity.ok(
                CustomResponse.builder().message("Email sent successfully").httpStatus(HttpStatus.OK).success(true).build()
        );
    }


}
