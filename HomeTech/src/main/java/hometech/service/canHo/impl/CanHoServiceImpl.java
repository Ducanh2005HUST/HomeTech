package hometech.service.canHo.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import hometech.model.dto.ResponseDto;
import hometech.model.dto.canHo.CanHoChiTietDto;
import hometech.model.dto.canHo.CanHoDto;
import hometech.model.entity.CanHo;
import hometech.model.mapper.CanHoMapper;
import hometech.repository.CanHoRepository;
import hometech.service.canHo.CanHoService;
import hometech.session.Session;

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
    public ResponseDto updateCanHo(CanHoDto canHoDto) {
        if (Session.getCurrentUser() == null || !"Tổ phó".equals(Session.getCurrentUser().getVaiTro())) {
            return new ResponseDto(false, "Bạn không có quyền cập nhật căn hộ. Chỉ Tổ phó mới được phép.");
        }
        if (canHoRepository.existsById(canHoDto.getMaCanHo())) {
            return new ResponseDto(false, "Căn hộ đã tồn tại");
        }
        CanHo canHo = canHoMapper.fromCanHoDto(canHoDto);
        canHoRepository.save(canHo);
        return new ResponseDto(true, "Căn hộ đã được cập nhật thành công");
    }

    @Override
    public ResponseDto deleteCanHo(CanHoDto canHoDto) {
        if (Session.getCurrentUser() == null || !"Tổ phó".equals(Session.getCurrentUser().getVaiTro())) {
            return new ResponseDto(false, "Bạn không có quyền xóa căn hộ. Chỉ Tổ phó mới được phép.");
        }
        canHoRepository.deleteById(canHoDto.getMaCanHo());
        return new ResponseDto(true, "Căn hộ đã được xóa thành công");
    }

    
}
