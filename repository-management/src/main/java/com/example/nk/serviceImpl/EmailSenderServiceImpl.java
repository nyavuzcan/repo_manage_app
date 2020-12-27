package com.example.nk.serviceImpl;

import com.example.nk.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {
  @Autowired
  private JavaMailSender javaMailSender;

  @Override
  @Async
  public void sendEmail(SimpleMailMessage email) {
    javaMailSender.send(email);
  }
}
