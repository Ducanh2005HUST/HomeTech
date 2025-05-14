package hometech.service.taiKhoan.impl;

import hometech.model.entity.TaiKhoan;
import hometech.repository.TaiKhoanRepository;
import hometech.util.OtpUtil;
import hometech.util.HashPasswordUtil;
import hometech.model.dto.ResponseDto;
import hometech.model.dto.taiKhoan.DatLaiMatKhauDto;
import hometech.service.taiKhoan.QuenMatKhauService;
import hometech.service.taiKhoan.EmailService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class QuenMatKhauServiceImpl implements QuenMatKhauService {
    private final TaiKhoanRepository taiKhoanRepository;
    private final EmailService emailService;

    public QuenMatKhauServiceImpl(TaiKhoanRepository taiKhoanRepository, EmailService emailService) {
        this.taiKhoanRepository = taiKhoanRepository;
        this.emailService = emailService;
    }

    @Override
    public ResponseDto guiMaOtp(DatLaiMatKhauDto dto) {
        TaiKhoan taiKhoan = taiKhoanRepository.findById(dto.getEmail()).orElse(null);
        if (taiKhoan == null) {
            return new ResponseDto(false, "Tài khoản không tồn tại");
        }
        String otp = OtpUtil.generateOtp();
        taiKhoan.setOtp(otp);
        taiKhoan.setThoiHanOtp(LocalDateTime.now().plusMinutes(5));
        taiKhoanRepository.save(taiKhoan);
        // Gửi email OTP (HTML)
        String subject = "Mã xác thực OTP";
        String content = String.format(
            """
            <div style='font-family:Arial,sans-serif;'>
                <h2>Xin chào %s,</h2>
                <p>Bạn vừa yêu cầu lấy mã xác thực OTP để đặt lại mật khẩu.</p>
                <ul>
                    <li><b>Mã OTP:</b> <span style='color:black;font-size:18px;font-weight:bold;'>%s</span></li>
                    <li><b>Thời hạn hiệu lực:</b> 5 phút kể từ khi nhận email này</li>
                </ul>
                <p style='color:red;'><b>Lưu ý:</b> Không cung cấp mã OTP cho bất kỳ ai. Không phản hồi email này.</p>
                <p>Trân trọng!</p>
            </div>
            """,
            taiKhoan.getHoTen() != null ? taiKhoan.getHoTen() : "bạn",
            otp
        );
        emailService.sendEmail(dto.getEmail(), subject, content, true);
        return new ResponseDto(true, "OTP đã được gửi về email");
    }

    @Override
    public ResponseDto xacThucOtp(DatLaiMatKhauDto datLaiMatKhauDto) {
        TaiKhoan taiKhoan = taiKhoanRepository.findById(datLaiMatKhauDto.getEmail()).orElse(null);
        if (taiKhoan == null) {
            return new ResponseDto(false, "Tài khoản không tồn tại");
        }
        if (taiKhoan.getOtp() == null || !taiKhoan.getOtp().equals(datLaiMatKhauDto.getOtp())) {
            return new ResponseDto(false, "OTP không hợp lệ");
        }
        if (taiKhoan.getThoiHanOtp() == null || LocalDateTime.now().isAfter(taiKhoan.getThoiHanOtp())) {
            return new ResponseDto(false, "OTP đã hết hạn");
        }
        return new ResponseDto(true, "OTP hợp lệ");
    }

    @Override
    public ResponseDto datLaiMatKhau(DatLaiMatKhauDto dto) {
        TaiKhoan taiKhoan = taiKhoanRepository.findById(dto.getEmail()).orElse(null);
        if (!dto.getMatKhauMoi().equals(dto.getXacNhanMatKhauMoi())) {
            return new ResponseDto(false, "Mật khẩu mới và xác nhận mật khẩu không khớp");
        }
        taiKhoan.setMatKhau(HashPasswordUtil.hashPassword(dto.getMatKhauMoi()));
        taiKhoan.setOtp(null);
        taiKhoan.setThoiHanOtp(null);
        taiKhoan.setNgayCapNhat(LocalDateTime.now());
        taiKhoanRepository.save(taiKhoan);
        return new ResponseDto(true, "Đặt lại mật khẩu thành công");
    }
}
