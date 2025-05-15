package hometech.service.hoaDon;

import java.util.List;

import hometech.model.dto.ResponseDto;
import hometech.model.dto.hoaDon.HoaDonDto;
import hometech.model.dto.hoaDon.HoaDonTuNguyenDto;
import hometech.model.dto.khoanThu.KhoanThuDto;
import hometech.model.dto.khoanThu.KhoanThuTuNguyenDto;
import org.springframework.web.multipart.MultipartFile;

public interface HoaDonService {
    ResponseDto generateHoaDon(KhoanThuDto khoanThuDto);
    List<HoaDonDto> getAllHoaDon();
    ResponseDto addHoaDonTuNguyen(HoaDonTuNguyenDto hoaDonTuNguyenDto, KhoanThuTuNguyenDto khoanThuTuNguyenDto);
    ResponseDto importFromExcel(MultipartFile file);
}
