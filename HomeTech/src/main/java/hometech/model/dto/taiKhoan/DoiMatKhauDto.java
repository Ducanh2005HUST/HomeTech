package hometech.model.dto.taiKhoan;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DoiMatKhauDto {
    private String matKhauCu;
    private String matKhauMoi;
    private String xacNhanMatKhauMoi;
}
