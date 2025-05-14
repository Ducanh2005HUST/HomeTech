package hometech.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hometech.model.dto.phiGuiXe.PhiGuiXeDto;
import hometech.model.entity.PhiGuiXe;

@Mapper(componentModel = "spring")
public interface PhiGuiXeMapper {
    @Mapping(target = "maKhoanThu", source = "khoanThu.maKhoanThu")
    PhiGuiXeDto toPhiGuiXeDto(PhiGuiXe phiGuiXe);

    @Mapping(target = "khoanThu.maKhoanThu", source = "maKhoanThu")
    PhiGuiXe fromPhiGuiXeDto(PhiGuiXeDto phiGuiXeDto);
}
