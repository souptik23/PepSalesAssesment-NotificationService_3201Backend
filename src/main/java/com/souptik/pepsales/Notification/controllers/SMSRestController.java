package com.souptik.pepsales.Notification.controllers;

import com.souptik.pepsales.Notification.helper.SmsSendRequest;
import com.souptik.pepsales.Notification.services.SMSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sms")
@Slf4j
public class SMSRestController {

    @Autowired
    SMSService smsService;

    @RequestMapping("/processSMS")
    public String processSMS(@RequestBody SmsSendRequest smsSendRequest){

        log.info("processSMS:  "+smsSendRequest.toString());
        return smsService.sendSMS(smsSendRequest.getDestinationSMSNumber() , smsSendRequest.getSmsMessage());
    }
}
