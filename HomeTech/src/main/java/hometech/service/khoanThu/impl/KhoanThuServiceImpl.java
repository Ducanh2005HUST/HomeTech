package hometech.service.khoanThu.impl;

import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import hometech.model.dto.khoanThu.KhoanThuDto;
import hometech.model.dto.ResponseDto;
import hometech.service.khoanThu.KhoanThuService;
import hometech.model.entity.KhoanThu;
import hometech.model.mapper.KhoanThuMapper;
import hometech.repository.KhoanThuRepository;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import hometech.session.Session;

@Service
public class KhoanThuServiceImpl implements KhoanThuService {
    private final KhoanThuRepository khoanThuRepository;
    private final KhoanThuMapper khoanThuMapper;

    public KhoanThuServiceImpl(KhoanThuRepository khoanThuRepository, KhoanThuMapper khoanThuMapper) {
        this.khoanThuRepository = khoanThuRepository;
        this.khoanThuMapper = khoanThuMapper;
    }

    @Override
    public List<KhoanThuDto> getAllKhoanThu() {
        List<KhoanThu> khoanThuList = khoanThuRepository.findAll();
        return khoanThuList.stream()
                .map(khoanThuMapper::toKhoanThuDto)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseDto addKhoanThu(KhoanThuDto khoanThuDto) {
        if (Session.getCurrentUser() == null || !"Kế toán".equals(Session.getCurrentUser().getVaiTro())) {
            return new ResponseDto(false, "Bạn không có quyền thêm khoản thu. Chỉ Kế toán mới được phép.");
        }
        // Tạo mã khoản thu tự động theo định dạng mới: TN/BB-YYYYMM-XXX
        String maKhoanThu = generateMaKhoanThu(khoanThuDto);
        // Kiểm tra mã khoản thu đã tồn tại
        if (khoanThuRepository.existsById(maKhoanThu)) {
            return new ResponseDto(false, "Mã khoản thu đã tồn tại. Vui lòng thử lại.");
        }
        // Gán mã khoản thu mới vào DTO
        khoanThuDto.setMaKhoanThu(maKhoanThu);
        // Chuyển đổi DTO thành entity và lưu
        KhoanThu khoanThu = khoanThuMapper.fromKhoanThuDto(khoanThuDto);
        khoanThuRepository.save(khoanThu);
        return new ResponseDto(true, "Thêm khoản thu thành công");
    }
    
    /**
     * Tạo mã khoản thu theo định dạng mới: TN/BB-YYYYMM-XXX
     * Ví dụ: BB-202405-001 (Bắt buộc, tháng 5/2024, số thứ tự 001)
     * hoặc TN-202405-001 (Tự nguyện, tháng 5/2024, số thứ tự 001)
     * 
     * @param khoanThuDto Thông tin khoản thu cần tạo mã
     * @return Mã khoản thu được tạo theo định dạng
     */
    private String generateMaKhoanThu(KhoanThuDto khoanThuDto) {
        // Phần 1: TN hoặc BB (Tự nguyện / Bắt buộc)
        boolean batBuoc = khoanThuDto.isBatBuoc();
        String loaiKhoanThu = batBuoc ? "BB" : "TN";
        
        // Phần 2: Thời điểm tạo yyyyMM (năm + tháng)
        LocalDate now = LocalDate.now();
        String thoiDiemTao = now.format(DateTimeFormatter.ofPattern("yyyyMM"));
        
        // Lấy tháng và năm hiện tại
        int month = now.getMonthValue();
        int year = now.getYear();
        
        // Đếm số lượng khoản thu cùng loại trong tháng và năm hiện tại
        long count = khoanThuRepository.countByBatBuocAndMonthAndYear(batBuoc, month, year);
        
        // Tăng số thứ tự lên 1 (vì count là số lượng hiện có)
        String soThuTu = String.format("%03d", count + 1);
        
        // Ghép các phần lại với nhau sử dụng StringBuilder
        StringBuilder maBuilder = new StringBuilder();
        maBuilder.append(loaiKhoanThu)
                .append("-")
                .append(thoiDiemTao)
                .append("-")
                .append(soThuTu);
                
        return maBuilder.toString();
    }

    @Override
    public ResponseDto updateKhoanThu(KhoanThuDto khoanThuDto) {
        if (Session.getCurrentUser() == null || !"Kế toán".equals(Session.getCurrentUser().getVaiTro())) {
            return new ResponseDto(false, "Bạn không có quyền cập nhật khoản thu. Chỉ Kế toán mới được phép.");
        }

        KhoanThu khoanThu = khoanThuRepository.findById(khoanThuDto.getMaKhoanThu()).orElse(null);
        if (khoanThu != null && khoanThu.isTaoHoaDon()) {
            return new ResponseDto(false, "Khoản thu đã tạo hóa đơn, không thể cập nhật");
        }
        khoanThu = khoanThuMapper.fromKhoanThuDto(khoanThuDto);
        khoanThuRepository.save(khoanThu);
        return new ResponseDto(true, "Cập nhật khoản thu thành công");
    }

    @Override
    public ResponseDto deleteKhoanThu(String maKhoanThu) {
        if (Session.getCurrentUser() == null || !"Kế toán".equals(Session.getCurrentUser().getVaiTro())) {
            return new ResponseDto(false, "Bạn không có quyền xóa khoản thu. Chỉ Kế toán mới được phép.");
        }
        KhoanThu khoanThu = khoanThuRepository.findById(maKhoanThu).orElse(null);
        if (khoanThu != null && khoanThu.isTaoHoaDon()) {
            return new ResponseDto(false, "Khoản thu đã tạo hóa đơn, không thể xóa");
        }
        khoanThuRepository.deleteById(maKhoanThu);
        return new ResponseDto(true, "Xóa khoản thu thành công");
    }
}
