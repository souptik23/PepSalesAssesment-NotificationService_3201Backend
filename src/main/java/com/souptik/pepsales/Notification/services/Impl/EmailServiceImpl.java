package com.souptik.pepsales.Notification.services.Impl;

import java.util.List;

import com.souptik.pepsales.Notification.services.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    private Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Override
    public void sendEmail(String to, String subject, String message) {
        if (to == null || to.isEmpty()) {
            logger.error("The 'to' address is null or empty.");
            throw new IllegalArgumentException("To address must not be null or empty");
        }

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        simpleMailMessage.setFrom("souptikkaran23@gmail.com"); // Set From address

        mailSender.send(simpleMailMessage);
        logger.info("Email has been sent successfully to {}", to);
    }

    @Override
    public void sendHtmlEmail(String to, List<String> cc, String subject, String message) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setFrom("souptikkaran23@gmail.com"); // Set From address (FIXED)
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(message, true); // true for HTML content

            if (cc != null && !cc.isEmpty()) {
                for (String ccEmail : cc) {
                    helper.addCc(ccEmail);
                }
            }

            mailSender.send(mimeMessage);
            logger.info("HTML email sent successfully to {}", to);

        } catch (MessagingException e) {
            logger.error("Failed to send HTML email", e);
            throw new RuntimeException("Error occurred while sending HTML email", e);
        }
    }

}