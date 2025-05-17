package com.souptik.pepsales.Notification.services;

import java.util.List;

public interface EmailService {

    //send email to single recipient
    void sendEmail(String to, String subject, String message);

    //send email with html content
    void sendHtmlEmail(String to, List<String> cc, String subject, String message);
}
