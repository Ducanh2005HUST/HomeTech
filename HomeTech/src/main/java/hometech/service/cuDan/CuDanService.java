package hometech.service.cuDan;

import java.util.List;

import hometech.model.dto.ResponseDto;
import hometech.model.dto.cuDan.CudanDto;

public interface CuDanService {
    List<CudanDto> getAllCuDan();
    ResponseDto addCuDan(CudanDto cudanDto);
    ResponseDto updateCuDan(CudanDto cudanDto);
    ResponseDto deleteCuDan(CudanDto cudanDto);    
}
