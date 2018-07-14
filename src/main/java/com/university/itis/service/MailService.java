package com.university.itis.service;

import com.university.itis.model.User;

/**
 * Created on 17.05.17.
 */
public interface MailService {

    void sendConfirmationMail(User user, String password);
}
