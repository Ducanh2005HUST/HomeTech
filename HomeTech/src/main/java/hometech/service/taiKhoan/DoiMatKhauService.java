package hometech.service.taiKhoan;

import hometech.model.dto.ResponseDto;
import hometech.model.dto.taiKhoan.DoiMatKhauDto;

public interface DoiMatKhauService {

    ResponseDto doiMatKhau(DoiMatKhauDto doiMatKhauDto);
}
