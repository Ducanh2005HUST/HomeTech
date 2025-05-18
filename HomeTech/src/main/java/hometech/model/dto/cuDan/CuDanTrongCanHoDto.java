package hometech.model.dto.cuDan;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuDanTrongCanHoDto {
    private String maDinhDanh;
    private String hoVaTen;
    private String gioiTinh;
    private LocalDate ngaySinh;
}
