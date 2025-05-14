package hometech.service.taiKhoan;

import hometech.model.dto.ResponseDto;
import hometech.model.dto.taiKhoan.DatLaiMatKhauDto;

public interface QuenMatKhauService {

    ResponseDto guiMaOtp(DatLaiMatKhauDto datLaiMatKhauDto);

    ResponseDto xacThucOtp(DatLaiMatKhauDto datLaiMatKhauDto);

    ResponseDto datLaiMatKhau(DatLaiMatKhauDto datLaiMatKhauDto);
    
}


