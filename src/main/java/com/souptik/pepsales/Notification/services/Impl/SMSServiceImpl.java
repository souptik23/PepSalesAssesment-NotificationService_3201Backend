package com.souptik.pepsales.Notification.services.Impl;

import com.souptik.pepsales.Notification.services.SMSService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SMSServiceImpl implements SMSService {

    @Value("${twilio.account.sid}")
    String ACCOUNT_SID;

    @Value("${twilio.auth.token}")
    String AUTH_TOKEN;

    @Value("${twilio.outgoing.sms.number}")
    String OUTGOING_SMS_NUMBER;


    public SMSServiceImpl(){
        log.info("hello");
        log.info("ACCOUNT_SID:"+ACCOUNT_SID);
    }

    @PostConstruct
    public void setup(){
        log.info("initializing the twilio");
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

    }

    @Override
    public String sendSMS(String smsNumber, String smsMessage) {

        Message message1 = Message.creator(
                new PhoneNumber(smsNumber),
                new PhoneNumber(OUTGOING_SMS_NUMBER),
                smsMessage).create();
        return message1.getStatus().toString();
    }

}
