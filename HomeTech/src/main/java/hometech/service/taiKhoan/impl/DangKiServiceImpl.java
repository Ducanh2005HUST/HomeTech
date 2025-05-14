package hometech.service.taiKhoan.impl;

import hometech.model.dto.ResponseDto;
import hometech.model.dto.taiKhoan.DangKiDto;
import hometech.model.entity.TaiKhoan;
import hometech.model.mapper.TaiKhoanMapper;
import hometech.repository.TaiKhoanRepository;
import hometech.service.taiKhoan.DangKiService;
import hometech.util.HashPasswordUtil;
import hometech.util.GeneratePasswordUtil;
import hometech.service.taiKhoan.EmailService;
import hometech.session.Session;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public class DangKiServiceImpl implements DangKiService {
    private final TaiKhoanRepository taiKhoanRepository;
    private final TaiKhoanMapper taiKhoanMapper;
    private final EmailService emailService;

    public DangKiServiceImpl(TaiKhoanRepository taiKhoanRepository, TaiKhoanMapper taiKhoanMapper, EmailService emailService) {
        this.taiKhoanRepository = taiKhoanRepository;
        this.taiKhoanMapper = taiKhoanMapper;
        this.emailService = emailService;
    }

    @Override
    public ResponseDto dangKiTaiKhoan(DangKiDto dangKiDto) {
        // Kiểm tra quyền: chỉ 'Tổ trưởng' mới được đăng ký tài khoản
        if (Session.getCurrentUser() == null || !"Tổ trưởng".equals(Session.getCurrentUser().getVaiTro())) {
            return new ResponseDto(false, "Bạn không có quyền đăng ký tài khoản. Chỉ Tổ trưởng mới được phép.");
        }
        // Kiểm tra email đã tồn tại chưa
        if (taiKhoanRepository.existsById(dangKiDto.getEmail())) {
            return new ResponseDto(false, "Email đã tồn tại");
        }
        // Tạo mật khẩu ngẫu nhiên
        String matKhau = GeneratePasswordUtil.generate();
        // Tạo tài khoản mới
        TaiKhoan taiKhoan = taiKhoanMapper.fromDangKiDto(dangKiDto);
        taiKhoan.setMatKhau(HashPasswordUtil.hashPassword(matKhau));
        taiKhoan.setNgayTao(LocalDateTime.now());
        taiKhoan.setNgayCapNhat(LocalDateTime.now());
        taiKhoanRepository.save(taiKhoan);
        // Gửi email thông tin tài khoản cho người dùng (HTML)
        String subject = "Thông tin tài khoản BlueMoonManagement";
        String content = String.format(
            """
            <div style='font-family:Arial,sans-serif;'>
                <h2>Chào %s,</h2>
                <p>Tài khoản của bạn đã được tạo thành công.</p>
                <ul>
                    <li><b>Họ tên:</b> %s</li>
                    <li><b>Tên đăng nhập (Email):</b> %s</li>
                    <li><b>Mật khẩu:</b> %s</li>
                </ul>
                <p style='color:red;'><b>Lưu ý:</b> Không cung cấp thông tin tài khoản cho bất kỳ ai. Không phản hồi email này.</p>
                <p>Vui lòng đổi mật khẩu sau khi đăng nhập lần đầu.</p>
                <p>Trân trọng!</p>
            </div>
            """,
            dangKiDto.getHoTen(),
            dangKiDto.getHoTen(),
            dangKiDto.getEmail(),
            matKhau
        );
        emailService.sendEmail(dangKiDto.getEmail(), subject, content, true);
        // Trả về phản hồi thành công
        return new ResponseDto(true, "Đăng kí thành công");
    }
}
