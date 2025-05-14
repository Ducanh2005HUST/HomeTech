package hometech.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hometech.model.dto.phuongTien.PhuongTienDto;
import hometech.model.entity.PhuongTien;

@Mapper(componentModel = "spring")
public interface PhuongTienMapper {
    PhuongTienDto toPhuongTienDto(PhuongTien phuongTien);

    @Mapping(target = "ngayHuyDangKy", ignore = true)
    @Mapping(target = "canHo", ignore = true)
    PhuongTien fromPhuongTienDto(PhuongTienDto phuongTienDto);
}
