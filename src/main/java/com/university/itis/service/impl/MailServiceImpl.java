package com.university.itis.service.impl;


import com.university.itis.model.User;
import com.university.itis.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created on 17.05.17.
 */
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private MailSender mailSender;

    @Override
    @Async
    public void sendConfirmationMail(User user, String password) {
        mailSender.send(createMessage(user, password));
    }

    private SimpleMailMessage createMessage(User user, String password) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(EmailProperties.Email);
        message.setSubject(EmailProperties.Subject);

        String emailMessage = "Password: " + password + "\n" +
                "Email: " + user.getEmail();

        message.setText(emailMessage);
        message.setTo(user.getEmail());
        return message;
    }

    public class EmailProperties {
        static final String Email = "itis.news.app@gmail.com";
        static final String Subject = "University Notification Application credentials";
    }

}
