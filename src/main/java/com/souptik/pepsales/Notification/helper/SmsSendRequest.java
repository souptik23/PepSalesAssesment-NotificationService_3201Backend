package com.souptik.pepsales.Notification.helper;

import lombok.Data;

@Data
public class SmsSendRequest {
    private String destinationSMSNumber;
    private String smsMessage;
}
