package hometech.model.dto.canHo;

import hometech.model.dto.cuDan.ChuHoDto;
import hometech.model.dto.cuDan.CuDanTrongCanHoDto;
import hometech.model.dto.hoaDon.HoaDonDto;
import hometech.model.dto.phuongTien.PhuongTienDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CanHoChiTietDto {
    private String maCanHo;
    private String toaNha;
    private String tang;
    private String soNha;
    private double dienTich;
    private boolean daBanChua;
    private String trangThaiKiThuat;
    private String trangThaiSuDung;
    private ChuHoDto chuHo;
    private List<PhuongTienDto> phuongTienList;
    private List<CuDanTrongCanHoDto> cuDanList;
    private List<HoaDonDto> hoaDonList;
}
