package com.jianzhong.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@Slf4j
public class MailService
{
    @Value("${spring.mail.username}")
    private String sender;

    @Autowired
    public JavaMailSender mailSender;

    public void send(String to,String subject,String content)
    {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(this.sender);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(content);
            mailSender.send(message);
        }catch (Exception e){
            log.error(e.getMessage().toString());
        }
    }

    public void sendHtml(String to,String subject,String content)
    {
        MimeMessage mineMessage = mailSender.createMimeMessage();
        MimeMessageHelper message = null;
        try {
            message = new MimeMessageHelper(mineMessage,true);
            message.setFrom(sender);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(content,true);
            mailSender.send(mineMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}