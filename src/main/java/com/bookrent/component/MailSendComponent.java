package com.bookrent.component;

import com.bookrent.dto.author.AuthorDto;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Component;

@Component
public class MailSendComponent {
    public void sendMail(AuthorDto authorDto) {
        String username = "onlinebrs402@gmail.com";
        String password = "jsfxhkjpytaozjws";
        try {
            Email email = new SimpleEmail();
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
            email.setAuthentication(username, password);
            email.setSSLOnConnect(true);
//            email.setFrom("tryingdemo65@gmail.com");
            email.setFrom("onlinebrs402@gmail.com");
            email.setSubject("Registration");
            email.setMsg("Hey " + authorDto.getName() + ",\nCongratulation you have registered as Author.");
            email.addTo(authorDto.getEmail());
            email.send();
            System.out.println("Mail sent successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error");
        }
    }
}
