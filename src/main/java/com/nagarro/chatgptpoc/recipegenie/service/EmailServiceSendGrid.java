package com.nagarro.chatgptpoc.recipegenie.service;

import com.nagarro.chatgptpoc.recipegenie.model.User;
import com.nagarro.chatgptpoc.recipegenie.repository.UserRepository;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class EmailServiceSendGrid {

    @Autowired
    UserRepository userRepository;
    @Value("${API_KEY_SEND_GRID:demo}")
    private String API_KEY_SEND_GRID;

    @Async
    public void sendEmail(String subject, String content) {
        log.info("API Key is : " + API_KEY_SEND_GRID);
        Email from = new Email("recipegenie07@gmail.com");
        Content emailContent = new Content("text/plain", content);
        List<User> users = userRepository.findAll();
        Mail mail = new Mail(from, subject, new Email(users.get(0).getEmail()), emailContent);

        for (int i=1;i<users.size();i++) {
            mail.personalization.get(0).addTo(new Email(users.get(i).getEmail()));
        }
        SendGrid sg = new SendGrid(API_KEY_SEND_GRID);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
