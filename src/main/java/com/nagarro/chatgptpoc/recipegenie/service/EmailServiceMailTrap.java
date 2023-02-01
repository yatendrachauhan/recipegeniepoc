package com.nagarro.chatgptpoc.recipegenie.service;

import com.nagarro.chatgptpoc.recipegenie.model.User;
import com.nagarro.chatgptpoc.recipegenie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmailServiceMailTrap {

    @Autowired
    UserRepository userRepository;
    private final JavaMailSender emailSender;

    @Autowired
    public EmailServiceMailTrap(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Async
    public void sendEmail(String subject, String text) {
        List<User> users = userRepository.findAll();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(users.stream().map(User::getEmail).collect(Collectors.toList()).toArray(new String[0]));
        message.setSubject(subject);
        message.setText(text);
        message.setFrom("recipegenie07@gmail.com");
        emailSender.send(message);
    }
}