package hometech.service.taiKhoan.impl;

import org.springframework.stereotype.Service;

import hometech.model.dto.taiKhoan.DangNhapDto;
import hometech.model.dto.taiKhoan.ThongTinTaiKhoanDto;
import hometech.model.entity.TaiKhoan;
import hometech.model.mapper.TaiKhoanMapper;
import hometech.repository.TaiKhoanRepository;
import hometech.service.taiKhoan.DangNhapServive;
import hometech.util.PasswordUtil;
import hometech.session.Session;
import hometech.model.dto.ResponseDto;

@Service
public class DangNhapServiceImpl implements DangNhapServive {

    private final TaiKhoanRepository taiKhoanRepository;
    private final TaiKhoanMapper taiKhoanMapper;

    public DangNhapServiceImpl(TaiKhoanRepository taiKhoanRepository, TaiKhoanMapper taiKhoanMapper) {
        this.taiKhoanRepository = taiKhoanRepository;
        this.taiKhoanMapper = taiKhoanMapper;
    }

    @Override
    public ResponseDto dangNhap(DangNhapDto dangNhapDto) {
        TaiKhoan taiKhoan = taiKhoanRepository.findById(dangNhapDto.getEmail()).orElse(null);
        if (taiKhoan == null) {
            return new ResponseDto(false, "Tài khoản không tồn tại");
        }
        boolean isMatch = PasswordUtil.verifyPassword(dangNhapDto.getMatKhau(), taiKhoan.getMatKhau());
        if (!isMatch) {
            return new ResponseDto(false, "Mật khẩu không đúng");
        }
        ThongTinTaiKhoanDto thongTinTaiKhoanDto = taiKhoanMapper.toThongTinTaiKhoanDto(taiKhoan);
        Session.setCurrentUser(thongTinTaiKhoanDto);
        return new ResponseDto(true, "Đăng nhập thành công");
    }

}


