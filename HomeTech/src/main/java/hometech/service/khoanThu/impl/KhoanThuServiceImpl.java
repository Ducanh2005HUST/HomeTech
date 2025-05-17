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
import hometech.util.XlsxExportUtil;
import hometech.util.XlxsFileUtil;

import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.ss.usermodel.Row;
import java.io.File;
import java.io.FileOutputStream;
import java.util.function.Function;

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

    @Override
    public ResponseDto importFromExcel(MultipartFile file) {
        if (Session.getCurrentUser() == null || !"Kế toán".equals(Session.getCurrentUser().getVaiTro())) {
            return new ResponseDto(false, "Bạn không có quyền thêm khoản thu. Chỉ Kế toán mới được phép.");
        }
        try {
            File tempFile = File.createTempFile("khoanthu_temp", ".xlsx");
            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                fos.write(file.getBytes());
            }
            Function<Row, KhoanThuDto> rowMapper = row -> {
                    KhoanThuDto dto = new KhoanThuDto();
                    dto.setMaKhoanThu(row.getCell(0).getStringCellValue());
                    dto.setTenKhoanThu(row.getCell(1).getStringCellValue());
                    dto.setBatBuoc(row.getCell(2).getBooleanCellValue());
                    dto.setDonViTinh(row.getCell(3).getStringCellValue());
                    dto.setSoTien(Integer.parseInt(row.getCell(4).getStringCellValue()));
                    dto.setPhamVi(row.getCell(5).getStringCellValue());
                    dto.setNgayTao(row.getCell(6).getDateCellValue().toInstant()
                        .atZone(java.time.ZoneId.systemDefault()).toLocalDate());
                    dto.setThoiHan(row.getCell(7).getDateCellValue().toInstant()
                        .atZone(java.time.ZoneId.systemDefault()).toLocalDate());
                    dto.setGhiChu(row.getCell(8).getStringCellValue());
                    dto.setPhiGuiXeList(null);
                    return dto;
            };
            List<KhoanThuDto> khoanThuDtoList = XlxsFileUtil.importFromExcel(tempFile.getAbsolutePath(), rowMapper);
            List<KhoanThu> khoanThuList = khoanThuDtoList.stream()
                    .map(khoanThuMapper::fromKhoanThuDto)
                    .collect(Collectors.toList());
            khoanThuRepository.saveAll(khoanThuList);
            tempFile.delete();
            return new ResponseDto(true, "Thêm khoản thu thành công " + khoanThuList.size() + " khoản thu");
        } catch (Exception e) {
            return new ResponseDto(false, "Thêm khoản thu thất bại: " + e.getMessage());
        }
    }
    @Override
    public ResponseDto exportToExcel(String filePath) {
        if (Session.getCurrentUser() == null || !"Kế toán".equals(Session.getCurrentUser().getVaiTro())) {
            return new ResponseDto(false, "Bạn không có quyền xuất khoản thu. Chỉ Kế toán mới được phép.");
        }
        List<KhoanThu> khoanThuList = khoanThuRepository.findAll();
        String[] headers = {"Mã khoản thu", "Tên khoản thu", "Bắt buộc", "Đơn vị tính", "Số tiền", "Phạm vi", "Ngày tạo", "Thời hạn", "Ghi chú"};
        try {
            XlsxExportUtil.exportToExcel(filePath, headers, khoanThuList, (row, khoanThu) -> {
                row.createCell(0).setCellValue(khoanThu.getMaKhoanThu());
                row.createCell(1).setCellValue(khoanThu.getTenKhoanThu());
                row.createCell(2).setCellValue(khoanThu.isBatBuoc());
                row.createCell(3).setCellValue(khoanThu.getDonViTinh());
                row.createCell(4).setCellValue(khoanThu.getSoTien());
                row.createCell(5).setCellValue(khoanThu.getPhamVi());
                row.createCell(6).setCellValue(java.sql.Date.valueOf(khoanThu.getNgayTao()));
                row.createCell(7).setCellValue(java.sql.Date.valueOf(khoanThu.getThoiHan()));
                row.createCell(8).setCellValue(khoanThu.getGhiChu());
            });
            return new ResponseDto(true, "Xuất file thành công");
        } catch (Exception e) {
            return new ResponseDto(false, "Xuất khoản thu thất bại: " + e.getMessage());
        }
    }
}
