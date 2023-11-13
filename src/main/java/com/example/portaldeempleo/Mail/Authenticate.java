package com.example.portaldeempleo.Mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Authenticate {

    @Autowired
    MailManager mailManager;
    public void sendMensaggeUser(String email, String message){

        mailManager.sendMessage(email, message);

    }

}
