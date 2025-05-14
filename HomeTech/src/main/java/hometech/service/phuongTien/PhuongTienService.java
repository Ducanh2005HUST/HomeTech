package hometech.service.phuongTien;


import hometech.model.dto.ResponseDto;
import hometech.model.dto.phuongTien.PhuongTienDto;

public interface PhuongTienService {
    ResponseDto themPhuongTien(PhuongTienDto phuongTienDto);
    ResponseDto capNhatPhuongTien(PhuongTienDto phuongTienDto);
    ResponseDto xoaPhuongTien(PhuongTienDto phuongTienDto);
}
