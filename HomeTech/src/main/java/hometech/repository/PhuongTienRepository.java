package hometech.repository;

import hometech.model.entity.PhuongTien;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PhuongTienRepository extends JpaRepository<PhuongTien, Integer> {
    boolean existsByBienSo(String bienSo);
    List<PhuongTien> findByCanHo_MaCanHo(String maCanHo);
}
