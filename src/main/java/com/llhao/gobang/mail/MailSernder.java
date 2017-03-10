package com.llhao.gobang.mail;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

/**
 * @author 罗浩
 **/
public class MailSernder {
    private static Properties props = new Properties();

    static {
        try {
            props.load(MailSernder.class
                    .getResourceAsStream("/conf/mail.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMail(String content, String adress, String subject) {
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(props.getProperty("name"),
                        props.getProperty("password"));
            }
        };
        Session session = Session.getInstance(props, authenticator);
        Message message = new MimeMessage(session);
        try {
            message.setSubject(subject);
            message.setText(content);
            message.setFrom(new InternetAddress("五子棋对战-验证信息"));
            message.setRecipient(RecipientType.TO, new InternetAddress(adress));
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
