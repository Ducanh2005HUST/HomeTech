package hometech.service.taiKhoan;

import hometech.model.dto.taiKhoan.DangNhapDto;
import hometech.model.dto.ResponseDto;

public interface DangNhapServive {

    ResponseDto dangNhap(DangNhapDto dangNhapDto);
}
