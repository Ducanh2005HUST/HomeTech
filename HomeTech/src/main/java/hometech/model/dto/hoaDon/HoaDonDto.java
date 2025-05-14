package hometech.model.dto.hoaDon;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoaDonDto {
    private Integer maHoaDon;
    private String tenKhoanThu;
    private Integer soTien;
    private LocalDateTime ngayNop;
    private boolean daNop;
}
