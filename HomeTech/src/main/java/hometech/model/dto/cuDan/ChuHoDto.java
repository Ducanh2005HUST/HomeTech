package hometech.model.dto.cuDan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChuHoDto {
    private String maDinhDanh;
    private String hoVaTen;
    private String soDienThoai;
    private String email;
    private String trangThaiCuTru;
}
