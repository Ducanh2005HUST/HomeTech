package hometech.service.taiKhoan.impl;

import hometech.service.taiKhoan.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService{
    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject); // subject sẽ bổ sung sau
        message.setText(content);    // content sẽ bổ sung sau
        mailSender.send(message);
    }

    @Override
    public void sendEmail(String to, String subject, String content, boolean isHtml) {
        if (isHtml) {
            try {
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
                helper.setTo(to);
                helper.setSubject(subject);
                helper.setText(content, true);
                mailSender.send(message);
            } catch (MessagingException e) {
                // Nếu gửi HTML thất bại, fallback sang gửi text thuần
                System.err.println("Gửi email HTML thất bại, thử gửi dạng text: " + e.getMessage());
                sendEmail(to, subject, content);
            }
        } else {
            sendEmail(to, subject, content);
        }
    }
}
