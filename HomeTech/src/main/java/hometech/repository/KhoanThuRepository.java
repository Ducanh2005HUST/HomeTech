package hometech.repository;

import hometech.model.entity.KhoanThu;
import hometech.repository.custom.KhoanThuRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KhoanThuRepository extends JpaRepository<KhoanThu, String>, KhoanThuRepositoryCustom {

}
