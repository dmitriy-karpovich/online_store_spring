package com.company.onlinestorespring.dao.impl;

import com.company.onlinestorespring.dao.UserOrderDao;
import com.company.onlinestorespring.entity.UserOrder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserOrderDaoImpl implements UserOrderDao  {

    private static final String FIND_USER_ORDER_BY_ID = "select uo from UserOrder uo where uo.id=:id";

    private static final String FIND_USER_ORDER_BY_USER_ID =
            "select uo from UserOrder uo where uo.user.id=:userId order by uo.id desc";

    private static final String UPDATE_ORDER_INFORMATION =
            "update UserOrder uo set uo.orderInformation.id=:orderInformationId where uo.id=:userOrderId";

    private static final String FIND_USER_ORDER_BY_ORDER_INFORMATION_STATUS =
            "select uo from UserOrder uo where uo.orderInformation.orderStatus=:status";

    private static final String FIND_ALL_USER_ORDERS = "select uo from UserOrder uo";

    private static final String FIND_USER_ORDER_BY_USER_ID_WITHOUT_ORDER_INFORMATION =
            "select uo from UserOrder uo where uo.user.id=:userId and uo.orderInformation.id is null order by " +
                    "uo.id desc";

    private static final String FIND_ORDERS_BY_USER_ID_AND_PRODUCT_ID_WITHOUT_ORDER_INFORMATION =
            "select uo from UserOrder uo where uo.user.id=:userId and uo.product.id=:productId and " +
                    "uo.orderInformation.id is null";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UserOrder findUserOrderById(Long id) {
        TypedQuery<UserOrder> query = entityManager.createQuery(FIND_USER_ORDER_BY_ID, UserOrder.class);
        query.setParameter("id", id);
        List<UserOrder> userOrderList = query.getResultList();
        return userOrderList.isEmpty() ? null : userOrderList.get(0);
    }

    @Override
    public UserOrder saveUserOrder(UserOrder userOrder) {
        entityManager.persist(userOrder);
        return userOrder;
    }

    @Override
    public List<UserOrder> findUserOrderByUserId(Long userId) {
        TypedQuery<UserOrder> query = entityManager.createQuery(FIND_USER_ORDER_BY_USER_ID, UserOrder.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Override
    public List<UserOrder> findUserOrderByOrderInformationStatus(String status) {
        TypedQuery<UserOrder> query =
                entityManager.createQuery(FIND_USER_ORDER_BY_ORDER_INFORMATION_STATUS, UserOrder.class);
        query.setParameter("status", status);
        return query.getResultList();
    }

    @Override
    public List<UserOrder> findAllUserOrders() {
        TypedQuery<UserOrder> query =
                entityManager.createQuery(FIND_ALL_USER_ORDERS, UserOrder.class);
        return query.getResultList();
    }

    @Override
    public List<UserOrder> findByUserWithoutOrderInformation(Long userId) {
        TypedQuery<UserOrder> query =
                entityManager.createQuery(FIND_USER_ORDER_BY_USER_ID_WITHOUT_ORDER_INFORMATION, UserOrder.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Override
    public List<UserOrder> findByUserAndProductWithoutOrderInformation(Long userId, Long productId) {
        TypedQuery<UserOrder> query =
                entityManager.createQuery(FIND_ORDERS_BY_USER_ID_AND_PRODUCT_ID_WITHOUT_ORDER_INFORMATION, UserOrder.class);
        query.setParameter("userId", userId);
        query.setParameter("productId", productId);
        return query.getResultList();
    }

    @Override
    public void updateOrderInformation(Long id, Long orderInformationId) {
        Query query = entityManager.createQuery(UPDATE_ORDER_INFORMATION);
        query.setParameter("orderInformationId", orderInformationId);
        query.setParameter("userOrderId", id);
        query.executeUpdate();
    }

    @Override
    public void removeUserOrderById(UserOrder userOrder) {
        entityManager.remove(userOrder);
    }
}