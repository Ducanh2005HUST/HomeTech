package hometech.service.taiKhoan.impl;

import hometech.model.dto.ResponseDto;
import hometech.model.dto.taiKhoan.DoiMatKhauDto;
import hometech.model.entity.TaiKhoan;
import hometech.repository.TaiKhoanRepository;
import hometech.service.taiKhoan.DoiMatKhauService;
import hometech.util.HashPasswordUtil;
import hometech.session.Session;
import org.springframework.stereotype.Service;

@Service
public class DoiMatKhauServiceImpl implements DoiMatKhauService {
    private final TaiKhoanRepository taiKhoanRepository;

    public DoiMatKhauServiceImpl(TaiKhoanRepository taiKhoanRepository) {
        this.taiKhoanRepository = taiKhoanRepository;
    }

    @Override
    public ResponseDto doiMatKhau(DoiMatKhauDto doiMatKhauDto) {
        // Lấy email từ phiên đăng nhập (Session)
        String email = Session.getCurrentUser().getEmail();
        // Truy vấn tài khoản (không cần kiểm tra null vì đã đăng nhập)
        TaiKhoan taiKhoan = taiKhoanRepository.findById(email).orElse(null);
        // So sánh mật khẩu cũ
        if (!HashPasswordUtil.verifyPassword(doiMatKhauDto.getMatKhauCu(), taiKhoan.getMatKhau())) {
            return new ResponseDto(false, "Mật khẩu hiện tại không đúng");
        }
        // So sánh mật khẩu mới và xác nhận
        if (!doiMatKhauDto.getMatKhauMoi().equals(doiMatKhauDto.getXacNhanMatKhauMoi())) {
            return new ResponseDto(false, "Mật khẩu mới và xác nhận không khớp");
        }
        // Cập nhật mật khẩu mới
        taiKhoan.setMatKhau(HashPasswordUtil.hashPassword(doiMatKhauDto.getMatKhauMoi()));
        taiKhoanRepository.save(taiKhoan);
        return new ResponseDto(true, "Đổi mật khẩu thành công");
    }
}
