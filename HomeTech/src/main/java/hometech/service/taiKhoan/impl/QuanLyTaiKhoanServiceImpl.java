package hometech.service.taiKhoan.impl;

import hometech.model.dto.taiKhoan.ThongTinTaiKhoanDto;
import hometech.service.taiKhoan.QuanLyTaiKhoanService;
import hometech.model.entity.TaiKhoan;
import hometech.repository.TaiKhoanRepository;
import hometech.model.dto.ResponseDto;
import hometech.session.Session;
import java.time.LocalDateTime;

public class QuanLyTaiKhoanServiceImpl implements QuanLyTaiKhoanService {
    private final TaiKhoanRepository taiKhoanRepository;

    public QuanLyTaiKhoanServiceImpl(TaiKhoanRepository taiKhoanRepository) {
        this.taiKhoanRepository = taiKhoanRepository;
    }

    @Override
    public ResponseDto thayDoiThongTinTaiKhoan(ThongTinTaiKhoanDto dto) {
        if (Session.getCurrentUser() == null || !"Tổ trưởng".equals(Session.getCurrentUser().getVaiTro())) {
            return new ResponseDto(false, "Bạn không có quyền thay đổi thông tin tài khoản. Chỉ Tổ trưởng mới được phép.");
        }
        TaiKhoan taiKhoan = taiKhoanRepository.findById(dto.getEmail()).orElse(null);
        taiKhoan.setHoTen(dto.getHoTen());
        taiKhoan.setVaiTro(dto.getVaiTro());
        taiKhoan.setNgayCapNhat(LocalDateTime.now());
        taiKhoanRepository.save(taiKhoan);
        return new ResponseDto(true, "Thay đổi thông tin tài khoản thành công.");
    }

    @Override
    public ResponseDto xoaTaiKhoan(ThongTinTaiKhoanDto thongTinTaiKhoanDto) {
        if (Session.getCurrentUser() == null || !"Tổ trưởng".equals(Session.getCurrentUser().getVaiTro())) {
            return new ResponseDto(false, "Bạn không có quyền xóa tài khoản. Chỉ Tổ trưởng mới được phép.");
        }
        taiKhoanRepository.deleteById(thongTinTaiKhoanDto.getEmail());
        return new ResponseDto(true, "Xóa tài khoản thành công.");
    }
}

