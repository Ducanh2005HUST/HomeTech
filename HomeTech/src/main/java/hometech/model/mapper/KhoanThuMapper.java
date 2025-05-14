package hometech.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hometech.model.dto.khoanThu.KhoanThuDto;
import hometech.model.entity.KhoanThu;

@Mapper(componentModel = "spring", uses = { PhiGuiXeMapper.class })
public interface KhoanThuMapper {
    KhoanThuDto toKhoanThuDto(KhoanThu khoanThu);

    @Mapping(target = "hoaDonList", ignore = true)
    @Mapping(target = "taoHoaDon", ignore = true)
    KhoanThu fromKhoanThuDto(KhoanThuDto khoanThuDto);
}
