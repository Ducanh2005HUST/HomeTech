package hometech.service.canHo.impl;

import java.io.FileOutputStream;
import java.io.File;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import hometech.model.dto.ResponseDto;
import hometech.model.dto.canHo.CanHoChiTietDto;
import hometech.model.dto.canHo.CanHoDto;
import hometech.model.entity.CanHo;
import hometech.model.mapper.CanHoMapper;
import hometech.repository.CanHoRepository;
import hometech.service.canHo.CanHoService;
import hometech.session.Session;
import hometech.util.XlsxExportUtil;
import hometech.util.XlxsFileUtil;

@Service
public class CanHoServiceImpl implements CanHoService {

    private final CanHoRepository canHoRepository;
    private final CanHoMapper canHoMapper;

    public CanHoServiceImpl(CanHoRepository canHoRepository, CanHoMapper canHoMapper) {
        this.canHoRepository = canHoRepository;
        this.canHoMapper = canHoMapper;
    }

    @Override
    public List<CanHoDto> getAllCanHo() {
        List<CanHo> canHoList = canHoRepository.findAll();
        return canHoList.stream()
                .map(canHoMapper::toCanHoDto)
                .collect(Collectors.toList());
    }

    @Override
    public CanHoChiTietDto getCanHoChiTiet(CanHoDto canHoDto) {
        CanHo canHo = canHoRepository.findById(canHoDto.getMaCanHo()).orElse(null);
        return canHoMapper.toCanHoChiTietDto(canHo);
    }

    @Override
    public ResponseDto addCanHo(CanHoDto canHoDto) {
        // Kiểm tra quyền: chỉ 'Tổ phó' mới được thêm căn hộ
        if (Session.getCurrentUser() == null || !"Tổ phó".equals(Session.getCurrentUser().getVaiTro())) {
            return new ResponseDto(false, "Bạn không có quyền thêm căn hộ. Chỉ Tổ phó mới được phép.");
        }
        // Check if an apartment with this code already exists
        if (canHoRepository.existsById(canHoDto.getMaCanHo())) {
            return new ResponseDto(false, "Căn hộ đã tồn tại");
        }
        // Convert DTO to entity using the mapper
        CanHo canHo = canHoMapper.fromCanHoDto(canHoDto);
        canHoRepository.save(canHo);
        return new ResponseDto(true, "Căn hộ đã được thêm thành công");
    }
    @Override
    public ResponseDto importFromExcel(MultipartFile file) {
        if (Session.getCurrentUser() == null || !"Kế toán".equals(Session.getCurrentUser().getVaiTro())) {
            return new ResponseDto(false, "Bạn không có quyền thêm hóa đơn tự nguyện. Chỉ Kế toán mới được phép.");
        }
        try {
            File tempFile = File.createTempFile("canho_temp", ".xlsx");
            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                fos.write(file.getBytes());
            }
            Function<Row, CanHoDto> rowMapper = row -> {
                CanHoDto canHoDto = new CanHoDto();
                canHoDto.setMaCanHo(row.getCell(0).getStringCellValue());
                canHoDto.setToaNha(row.getCell(1).getStringCellValue());
                canHoDto.setTang(Integer.parseInt(row.getCell(2).getStringCellValue()));
                canHoDto.setSoNha(row.getCell(3).getStringCellValue());
                canHoDto.setDienTich(row.getCell(4).getNumericCellValue());
                canHoDto.setChuHo(null);
                canHoDto.setDaBanChua(row.getCell(6).getBooleanCellValue());
                canHoDto.setTrangThaiKiThuat(row.getCell(7).getStringCellValue());
                canHoDto.setTrangThaiSuDung(row.getCell(8).getStringCellValue());
                return canHoDto;
            };
            List<CanHoDto> canHoDtoList = XlxsFileUtil.importFromExcel(tempFile.getAbsolutePath(), rowMapper);
            List<CanHo> canHoList = canHoDtoList.stream()
                    .map(canHoMapper::fromCanHoDto)
                    .collect(Collectors.toList());
            canHoRepository.saveAll(canHoList);
            tempFile.delete();
            return new ResponseDto(true, "Thêm căn hộ thành công" + canHoDtoList.size() + " căn hộ");
        } catch (Exception e) {
            return new ResponseDto(false, "Thêm căn hộ thất bại: " + e.getMessage());
        }
    }
    
    @Override
    public ResponseDto exportToExcel(String filePath) {
        if (Session.getCurrentUser() == null || !"Kế toán".equals(Session.getCurrentUser().getVaiTro())) {
            return new ResponseDto(false, "Bạn không có quyền xuất khoản thu. Chỉ Kế toán mới được phép.");
        }
        String[] headers = {"Mã căn hộ", "Tên tòa nhà", "Số tầng", "Số nhà", "Diện tích", "Chủ hộ", "Đã bán chưa", "Trạng thái kỹ thuật", "Trạng thái sử dụng"};
        List<CanHoDto> canHoList = getAllCanHo();
        try {
            XlsxExportUtil.exportToExcel(filePath, headers, canHoList, (row, canHo) -> {
                row.createCell(0).setCellValue(canHo.getMaCanHo());
                row.createCell(1).setCellValue(canHo.getToaNha());
                row.createCell(2).setCellValue(canHo.getTang());
                row.createCell(3).setCellValue(canHo.getSoNha());
                row.createCell(4).setCellValue(canHo.getDienTich());
                row.createCell(5).setCellValue(canHo.getChuHo() != null ? canHo.getChuHo().getHoVaTen() : "");
                row.createCell(6).setCellValue(canHo.isDaBanChua() ? "Có" : "Không");
                row.createCell(7).setCellValue(canHo.getTrangThaiKiThuat());
                row.createCell(8).setCellValue(canHo.getTrangThaiSuDung());
            });
            return new ResponseDto(true, "Xuất file thành công");
        } catch (Exception e) {
            return new ResponseDto(false, "Xuất file thất bại: " + e.getMessage());
        }
    }
}
