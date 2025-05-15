package hometech.service.khoanThu;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import hometech.model.dto.ResponseDto;
import hometech.model.dto.khoanThu.KhoanThuDto;

public interface KhoanThuService {
    List<KhoanThuDto> getAllKhoanThu();
    ResponseDto addKhoanThu(KhoanThuDto khoanThuDto);
    ResponseDto updateKhoanThu(KhoanThuDto khoanThuDto);
    ResponseDto deleteKhoanThu(String maKhoanThu);
    ResponseDto importFromExcel(MultipartFile file);
    
}
