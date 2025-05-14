package hometech.model.dto.khoanThu;

import java.time.LocalDate;
import java.util.List;

import hometech.model.dto.phiGuiXe.PhiGuiXeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KhoanThuDto {
    private String maKhoanThu;
    private String tenKhoanThu;
    private boolean batBuoc;
    private String donViTinh;
    private int soTien;
    private String phamVi;
    private LocalDate ngayTao;
    private LocalDate thoiHan;
    private String ghiChu;
    private List<PhiGuiXeDto> phiGuiXeList;
}
