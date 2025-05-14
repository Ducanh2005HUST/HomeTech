package hometech.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hometech.model.dto.hoaDon.HoaDonDto;
import hometech.model.dto.hoaDon.HoaDonTuNguyenDto;
import hometech.model.dto.hoaDon.HoaDonDichVuDto;
import hometech.model.entity.HoaDon;

@Mapper(componentModel = "spring")
public interface HoaDonMapper {
    @Mapping(target = "tenKhoanThu", source = "hoaDon.khoanThu.tenKhoanThu")
    HoaDonDto toHoaDonDto(HoaDon hoaDon);

    @Mapping(target = "maHoaDon", ignore = true)
    @Mapping(target = "khoanThu.tenKhoanThu", source = "hoaDonDichVuDto.tenKhoanThu")
    @Mapping(target = "canHo.maCanHo", source = "hoaDonDichVuDto.maCanHo")
    @Mapping(target = "ngayNop", ignore = true)
    @Mapping(target = "daNop", ignore = true)
    HoaDon fromHoaDonDichVuDto(HoaDonDichVuDto hoaDonDichVuDto);

    @Mapping(target = "ngayNop", ignore = true)
    @Mapping(target = "daNop", ignore = true)
    @Mapping(target = "khoanThu.tenKhoanThu", source = "hoaDonTuNguyenDto.tenKhoanThu")
    @Mapping(target = "canHo.maCanHo", source = "hoaDonTuNguyenDto.maCanHo")
    HoaDon fromHoaDonTuNguyenDto(HoaDonTuNguyenDto hoaDonTuNguyenDto);

    // MapStruct mapping from HoaDonDto to HoaDon (for import)
    @Mapping(target = "khoanThu", ignore = true) // Set manually if needed
    @Mapping(target = "canHo", ignore = true)   // Set manually if needed
    HoaDon fromHoaDonDto(HoaDonDto hoaDonDto);
}
