package hometech.service.taiKhoan;

import hometech.model.dto.ResponseDto;
import hometech.model.dto.taiKhoan.DangKiDto;

public interface DangKiService {

    ResponseDto dangKiTaiKhoan(DangKiDto dangKiDto);
}
