package service.implementations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class EmailServiceImplTest {

    private EmailServiceImpl emailService;

    @BeforeEach
    void setUp() {
        emailService = new EmailServiceImpl();
    }

    @Test
    void sendEmail_ValidInput_Success() throws MessagingException {
        // Arrange
        String recipientEmail = "test@example.com";
        String subject = "Test Subject";
        String messageText = "This is a test message.";

        Transport transportMock = mock(Transport.class);


        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, null);
        MimeMessage message = new MimeMessage(session);
        message.setFrom("erradaoumaima@gmail.com");
        message.setRecipients(javax.mail.Message.RecipientType.TO, recipientEmail);
        message.setSubject(subject);
        message.setText(messageText);

        emailService.sendEmail(recipientEmail, subject, messageText);

        verify(transportMock).send(message);
    }

    @Test
    void sendEmail_InvalidEmail_ThrowsMessagingException() {

        String recipientEmail = "invalid-email";
        String subject = "Test Subject";
        String messageText = "This is a test message.";


        assertThrows(MessagingException.class, () -> {
            emailService.sendEmail(recipientEmail, subject, messageText);
        });
    }
}
