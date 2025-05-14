package hometech.repository.custom.impl;

import hometech.repository.custom.KhoanThuRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

/**
 * Implementation class cho KhoanThuRepositoryCustom
 */
public class KhoanThuRepositoryCustomImpl implements KhoanThuRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public long countByBatBuocAndMonthAndYear(boolean batBuoc, int month, int year) {
        Query query = entityManager.createQuery(
                "SELECT COUNT(k) FROM KhoanThu k WHERE k.batBuoc = :batBuoc AND FUNCTION('MONTH', k.ngayTao) = :month AND FUNCTION('YEAR', k.ngayTao) = :year");
        query.setParameter("batBuoc", batBuoc);
        query.setParameter("month", month);
        query.setParameter("year", year);
        
        return (long) query.getSingleResult();
    }
} 