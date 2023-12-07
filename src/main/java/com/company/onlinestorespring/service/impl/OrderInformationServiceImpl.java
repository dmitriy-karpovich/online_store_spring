package com.company.onlinestorespring.service.impl;

import com.company.onlinestorespring.dao.OrderInformationDao;
import com.company.onlinestorespring.dao.UserOrderDao;
import com.company.onlinestorespring.entity.BankCard;
import com.company.onlinestorespring.entity.OrderInformation;
import com.company.onlinestorespring.entity.UserOrder;
import com.company.onlinestorespring.exception.BankCardVerificationException;
import com.company.onlinestorespring.exception.EntityNotFoundException;
import com.company.onlinestorespring.service.BankCardService;
import com.company.onlinestorespring.service.OrderInformationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.*;

@Service
public class OrderInformationServiceImpl implements OrderInformationService {

    private final OrderInformationDao orderInformationDao;
    private final BankCardService bankCardService;
    private final UserOrderDao userOrderDao;

    private static final String WAIT_STATUS = "ожидается";
    private static final String COMPLETED_STATUS = "доставлен";
    private static final String CANCELLED_STATUS = "отменен";
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderInformationServiceImpl.class);

    @Autowired
    public OrderInformationServiceImpl(OrderInformationDao orderInformationDao,
                                       BankCardService bankCardService,
                                       UserOrderDao userOrderDao) {
        this.orderInformationDao = orderInformationDao;
        this.bankCardService = bankCardService;
        this.userOrderDao = userOrderDao;
    }

    @Override
    public OrderInformation retrieveOrderInformationById(Long orderInformationId) {
        Optional<OrderInformation> orderInformation =
                Optional.ofNullable(orderInformationDao.findOrderInformationById(orderInformationId));
        if (orderInformation.isEmpty()) {
            throw new EntityNotFoundException("Order information with id " + orderInformationId + " was not found");
        }
        return orderInformation.get();
    }

    @Override
    public List<OrderInformation> retrieveOrderInformationByStatus(String orderStatus) {
        List<OrderInformation> orderInformation = orderInformationDao.findOrderInformationByStatus(orderStatus);
        if (orderInformation.isEmpty()) {
            throw new EntityNotFoundException("Order information with order status " + orderStatus + " was not found");
        }
        return orderInformation;
    }

    @Override
    @Transactional
    public void finalizeOrderByOrderInformationId(Long orderInformationId) {
        LOGGER.info("Order with order information id {} has been finalized.", orderInformationId);
        orderInformationDao.updateStatusById(orderInformationId, COMPLETED_STATUS);
    }

    @Override
    @Transactional
    public void refuseOrderByOrderInformationId(Long orderInformationId) {
        LOGGER.info("Order with order information id {} has been refused.", orderInformationId);
        orderInformationDao.updateStatusById(orderInformationId, CANCELLED_STATUS);

    }

    @Override
    @Transactional
    public void placeOrder(List<UserOrder> userOrders, BankCard bankCard,
                           String deliveryAddress, LocalDate deliveryDate, double totalCost)
            throws BankCardVerificationException {

        bankCardService.pay(bankCard, totalCost);
        OrderInformation orderInformation = buildOrderInformation(deliveryAddress, deliveryDate);
        OrderInformation result = orderInformationDao.saveOrderInformation(orderInformation);
        for (UserOrder userOrder : userOrders) {
            userOrderDao.updateOrderInformation(userOrder.getId(), result.getId());
        }
        LOGGER.info("Order information with the id {} has been successfully added.", result.getId());
    }

    private OrderInformation buildOrderInformation(String address, LocalDate deliveryDate) {
        OrderInformation orderInformation = new OrderInformation();
        orderInformation.setDeliveryAddress(address);
        orderInformation.setOrderDate(LocalDate.now());
        orderInformation.setDeliveryDate(deliveryDate);
        orderInformation.setOrderStatus(WAIT_STATUS);
        return orderInformation;
    }
}