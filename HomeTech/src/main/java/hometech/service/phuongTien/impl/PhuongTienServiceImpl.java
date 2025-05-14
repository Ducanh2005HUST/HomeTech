package hometech.service.phuongTien.impl;

import hometech.model.dto.ResponseDto;
import hometech.model.dto.phuongTien.PhuongTienDto;
import hometech.model.entity.PhuongTien;
import hometech.model.mapper.PhuongTienMapper;
import hometech.repository.PhuongTienRepository;
import hometech.service.phuongTien.PhuongTienService;
import hometech.session.Session;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
@Service
public class PhuongTienServiceImpl implements PhuongTienService {
    private final PhuongTienRepository phuongTienRepository;
    private final PhuongTienMapper phuongTienMapper;

    public PhuongTienServiceImpl(PhuongTienRepository phuongTienRepository, PhuongTienMapper phuongTienMapper) {
        this.phuongTienRepository = phuongTienRepository;
        this.phuongTienMapper = phuongTienMapper;
    }

    @Override
    public ResponseDto themPhuongTien(PhuongTienDto phuongTienDto) {
        if (Session.getCurrentUser() == null || !"Tổ phó".equals(Session.getCurrentUser().getVaiTro())) {
            return new ResponseDto(false, "Bạn không có quyền thêm phương tiện. Chỉ Tổ phó mới được phép.");
        }
        if (phuongTienDto.getBienSo() != null && phuongTienRepository.existsByBienSo(phuongTienDto.getBienSo())) {
            return new ResponseDto(false, "Biển số xe đã tồn tại");
        }
        phuongTienDto.setNgayDangKy(LocalDate.now());
        PhuongTien phuongTien = phuongTienMapper.fromPhuongTienDto(phuongTienDto);
        phuongTienRepository.save(phuongTien);
        return new ResponseDto(true, "Thêm phương tiện thành công");
    }

    @Override
    public ResponseDto capNhatPhuongTien(PhuongTienDto phuongTienDto) {
        if (Session.getCurrentUser() == null || !"Tổ phó".equals(Session.getCurrentUser().getVaiTro())) {
            return new ResponseDto(false, "Bạn không có quyền cập nhật phương tiện. Chỉ Tổ phó mới được phép.");
        }
        PhuongTien phuongTien = phuongTienRepository.findById(phuongTienDto.getSoThuTu()).orElse(null);
        // Kiểm tra biển số đã tồn tại ở phương tiện khác
        boolean bienSoTrung = phuongTienRepository.existsByBienSo(phuongTienDto.getBienSo()) &&
            (!phuongTien.getBienSo().equals(phuongTienDto.getBienSo()));
        if (bienSoTrung) {
            return new ResponseDto(false, "Biển số xe đã tồn tại ở phương tiện khác");
        }
        phuongTien.setBienSo(phuongTienDto.getBienSo());
        phuongTienRepository.save(phuongTien);
        return new ResponseDto(true, "Cập nhật phương tiện thành công");
    }

    @Override
    public ResponseDto xoaPhuongTien(PhuongTienDto phuongTienDto) {
        if (Session.getCurrentUser() == null || !"Tổ phó".equals(Session.getCurrentUser().getVaiTro())) {
            return new ResponseDto(false, "Bạn không có quyền xóa phương tiện. Chỉ Tổ phó mới được phép.");
        }
        PhuongTien phuongTien = phuongTienRepository.findById(phuongTienDto.getSoThuTu()).orElse(null);
        phuongTien.setNgayHuyDangKy(LocalDate.now());
        phuongTienRepository.save(phuongTien);
        return new ResponseDto(true, "Đã hủy đăng ký phương tiện thành công");
    }
}
