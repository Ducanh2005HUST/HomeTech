package hometech.model.dto.taiKhoan;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ThongTinTaiKhoanDto {
    private String email;
    private String hoTen;
    private String vaiTro;
}
