package hometech.repository;

import hometech.model.entity.CanHo;
import hometech.repository.custom.CanHoRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CanHoRepository extends JpaRepository<CanHo, String>, CanHoRepositoryCustom {
    
}
