package com.company.onlinestorespring.dao.impl;

import com.company.onlinestorespring.dao.OrderInformationDao;
import com.company.onlinestorespring.entity.OrderInformation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class OrderInformationDaoImpl implements OrderInformationDao {

    private static final String FIND_ORDER_INFORMATION_BY_STATUS = "select oi from OrderInformation oi where " +
            "oi.orderStatus=:orderStatus";

    private static final String UPDATE_ORDER_INFORMATION_STATUS_BY_ID =
            "update OrderInformation oi set oi.orderStatus=:status where oi.id=:id";

    private static final String FIND_ORDER_INFORMATION_BY_ID = "select oi from OrderInformation oi where oi.id=:id";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public OrderInformation findOrderInformationById(Long id) {
        TypedQuery<OrderInformation> query =
                entityManager.createQuery(FIND_ORDER_INFORMATION_BY_ID, OrderInformation.class);
        query.setParameter("id", id);
        List<OrderInformation> informationList = query.getResultList();
        return informationList.isEmpty() ? null : informationList.get(0);
    }

    @Override
    public OrderInformation saveOrderInformation(OrderInformation orderInformation) {
        entityManager.persist(orderInformation);
        return orderInformation;
    }

    @Override
    public List<OrderInformation> findOrderInformationByStatus(String status) {
        TypedQuery<OrderInformation> query =
                entityManager.createQuery(FIND_ORDER_INFORMATION_BY_STATUS, OrderInformation.class);
        query.setParameter("orderStatus", status);
        return query.getResultList();
    }

    @Override
    public void updateStatusById(Long id, String status) {
        Query query = entityManager.createQuery(UPDATE_ORDER_INFORMATION_STATUS_BY_ID);
        query.setParameter("status", status);
        query.setParameter("id", id);
        query.executeUpdate();
    }
}