package hometech.service.canHo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import hometech.model.dto.ResponseDto;
import hometech.model.dto.canHo.CanHoChiTietDto;
import hometech.model.dto.canHo.CanHoDto;

public interface CanHoService {
    List<CanHoDto> getAllCanHo();

    ResponseDto addCanHo(CanHoDto canHoDto);

    ResponseDto updateCanHo(CanHoDto canHoDto);

    ResponseDto deleteCanHo(CanHoDto canHoDto);

    CanHoChiTietDto getCanHoChiTiet(CanHoDto canHoDto);

    ResponseDto importFromExcel(MultipartFile file);

    ResponseDto exportToExcel(String filePath);
}
