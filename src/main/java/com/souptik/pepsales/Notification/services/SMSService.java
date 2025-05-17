package com.souptik.pepsales.Notification.services;

public interface SMSService {

    String sendSMS(String toNumber, String message);
}
