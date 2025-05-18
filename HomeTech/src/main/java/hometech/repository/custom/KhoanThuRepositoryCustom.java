package hometech.repository.custom;

import org.springframework.data.repository.query.Param;

/**
 * Custom repository interface cho KhoanThu với các truy vấn phức tạp
 */
public interface KhoanThuRepositoryCustom {
    
    /**
     * Đếm số lượng khoản thu theo loại (bắt buộc/tự nguyện) và tháng/năm tạo
     * 
     * @param batBuoc Loại khoản thu (true: bắt buộc, false: tự nguyện)
     * @param month Tháng tạo
     * @param year Năm tạo
     * @return Số lượng khoản thu thỏa mãn điều kiện
     */

    long countByBatBuocAndMonthAndYear(
            @Param("batBuoc") boolean batBuoc,
            @Param("month") int month,
            @Param("year") int year);
} 