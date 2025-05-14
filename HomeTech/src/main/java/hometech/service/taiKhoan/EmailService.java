package hometech.service.taiKhoan;

public interface EmailService {
    void sendEmail(String to, String subject, String content);
    void sendEmail(String to, String subject, String content, boolean isHtml); // Thêm phương thức hỗ trợ gửi HTML
}
