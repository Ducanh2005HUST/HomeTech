package hometech.repository.custom.impl;

import hometech.model.entity.CanHo;
import hometech.repository.custom.CanHoRepositoryCustom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.NoResultException;
public class CanHoRepositoryCustomImpl implements CanHoRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public CanHo findCanHoWithCuDanCuTru(String maCanHo) {
        Query query = entityManager.createQuery(
                "SELECT c FROM CanHo c LEFT JOIN FETCH c.cuDanList cd WHERE c.maCanHo = :maCanHo AND (cd.trangThaiCuTru = 'Cư trú' OR cd IS NULL)"
        );
        query.setParameter("maCanHo", maCanHo);
        try {
            return (CanHo) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
