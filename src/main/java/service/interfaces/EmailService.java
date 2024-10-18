package service.interfaces;

import javax.mail.MessagingException;

public interface EmailService {
    void sendEmail(String recipient, String subject, String body)throws MessagingException;
}
