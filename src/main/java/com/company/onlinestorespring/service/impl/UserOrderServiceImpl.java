package com.company.onlinestorespring.service.impl;

import com.company.onlinestorespring.dao.UserOrderDao;
import com.company.onlinestorespring.entity.Product;
import com.company.onlinestorespring.entity.User;
import com.company.onlinestorespring.entity.UserOrder;
import com.company.onlinestorespring.exception.EntityNotFoundException;
import com.company.onlinestorespring.service.UserOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserOrderServiceImpl implements UserOrderService {

    private final UserOrderDao userOrderDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserOrderServiceImpl.class);

    @Autowired
    public UserOrderServiceImpl(UserOrderDao userOrderDao) {
        this.userOrderDao = userOrderDao;
    }

    @Override
    public List<UserOrder> retrieveUserOrdersByUserId(Long userId) {
        List<UserOrder> userOrders = userOrderDao.findUserOrderByUserId(userId);
        if (userOrders.isEmpty()) {
            throw new EntityNotFoundException("User orders were not found");
        }
        return userOrders;
    }

    @Override
    public List<UserOrder> retrieveUserOrdersByUserWhereProductStatusTrue(Long userId) {
        List<UserOrder> userOrders = userOrderDao.findByUserWithoutOrderInformation(userId);
        userOrders.removeIf(userOrder -> !userOrder.getProduct().getStatus());
        return userOrders;
    }

    @Override
    public List<UserOrder> retrieveUserOrderByOrderInformationStatus(String status) {
        return userOrderDao.findUserOrderByOrderInformationStatus(status);
    }

    @Override
    public List<UserOrder> retrieveAllUserOrders() {
        return userOrderDao.findAllUserOrders();
    }

    @Override
    @Transactional
    public void removeUserOrdersById(Long orderId) {
        LOGGER.info("Remove user order by id {}", orderId);
        Optional<UserOrder> userOrder = Optional.ofNullable(userOrderDao.findUserOrderById(orderId));
        if (userOrder.isEmpty()) {
            throw new EntityNotFoundException("User order was not found");
        }
        userOrderDao.removeUserOrderById(userOrder.get());
    }

    @Override
    @Transactional
    public boolean addNewOrder(User user, Product product, Integer purchasedQuantity) {
        List<UserOrder> userOrders =
                userOrderDao.findByUserAndProductWithoutOrderInformation(user.getId(), product.getId());
        if (userOrders.size() != 0) {
            return false;
        }
        UserOrder userOrder = new UserOrder();
        userOrder.setUser(user);
        userOrder.setProduct(product);
        userOrder.setPurchasedQuantity(purchasedQuantity);
        UserOrder result = userOrderDao.saveUserOrder(userOrder);
        LOGGER.info("The user order with id {} was successfully added.", result.getId());
        return true;
    }

    @Override
    public double calculateTotalCost(List<UserOrder> userOrders) {
        return userOrders.stream()
                .mapToDouble(userOrder ->
                        userOrder.getProduct().getPrice() * userOrder.getPurchasedQuantity())
                .sum();
    }
}