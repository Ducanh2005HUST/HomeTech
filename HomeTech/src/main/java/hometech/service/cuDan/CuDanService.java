package hometech.service.cuDan;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import hometech.model.dto.ResponseDto;
import hometech.model.dto.cuDan.CudanDto;

public interface CuDanService {
    List<CudanDto> getAllCuDan();
    ResponseDto addCuDan(CudanDto cudanDto);
    ResponseDto updateCuDan(CudanDto cudanDto);
    ResponseDto deleteCuDan(CudanDto cudanDto);    
    ResponseDto importFromExcel(MultipartFile file);
    ResponseDto exportToExcel(String filePath);
}
