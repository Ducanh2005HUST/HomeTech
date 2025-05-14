package hometech.service.taiKhoan;

import hometech.model.dto.taiKhoan.ThongTinTaiKhoanDto;
import hometech.model.dto.ResponseDto;

public interface QuanLyTaiKhoanService {

    ResponseDto thayDoiThongTinTaiKhoan(ThongTinTaiKhoanDto thongTinTaiKhoanDto);
    ResponseDto xoaTaiKhoan(ThongTinTaiKhoanDto thongTinTaiKhoanDto);
}
