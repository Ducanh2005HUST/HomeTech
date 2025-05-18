package hometech.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hometech.model.dto.cuDan.ChuHoDto;
import hometech.model.dto.cuDan.CuDanTrongCanHoDto;
import hometech.model.dto.cuDan.CudanDto;
import hometech.model.entity.CuDan;

@Mapper(componentModel = "spring")
public interface CuDanMapper {
    CuDanTrongCanHoDto toCuDanTrongCanHoDto(CuDan cuDan);

    @Mapping(target = "maCanHo", source = "cuDan.canHo.maCanHo")
    CudanDto toCudanDto(CuDan cuDan);

    ChuHoDto toChuHoDto(CuDan cuDan);

    @Mapping(target = "canHo.maCanHo", source = "cudanDto.maCanHo")
    CuDan fromCudanDto(CudanDto cudanDto);

    @Mapping(target = "canHo", ignore = true)
    @Mapping(target = "ngayChuyenDi", ignore = true)
    @Mapping(target = "gioiTinh", ignore = true)
    @Mapping(target = "ngaySinh", ignore = true)
    CuDan fromChuHoDto(ChuHoDto chuHoDto);
}
