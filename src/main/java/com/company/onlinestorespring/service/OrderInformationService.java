package com.company.onlinestorespring.service;

import com.company.onlinestorespring.entity.BankCard;
import com.company.onlinestorespring.entity.OrderInformation;
import com.company.onlinestorespring.entity.UserOrder;
import com.company.onlinestorespring.exception.BankCardVerificationException;
import java.time.LocalDate;
import java.util.List;

public interface OrderInformationService {

    /**
     * Retrieve an order information by id
     *
     * @param orderInformationId - the order information id
     * @return order information
     */
    OrderInformation retrieveOrderInformationById(Long orderInformationId);

    /**
     * Retrieve information about an order by status
     *
     * @param orderStatus - the order status
     * @return the list of order information
     */
    List<OrderInformation> retrieveOrderInformationByStatus(String orderStatus);

    /**
     * Update the status in order information by id
     *
     * @param orderInformationId - the order information id
     */
    void finalizeOrderByOrderInformationId(Long orderInformationId);

    /**
     * Add new order information
     *
     * @param userOrders      - the user orders
     * @param bankCard        - the bank card
     * @param deliveryAddress - the delivery address
     * @param deliveryDate    - the delivery date
     * @param totalCost       - the total cost
     */

    void placeOrder(List<UserOrder> userOrders, BankCard bankCard, String deliveryAddress,
                    LocalDate deliveryDate, double totalCost) throws BankCardVerificationException;

    /**
     * Refuse an order by order information id
     *
     * @param orderInformationId - the order information id
     */
    void refuseOrderByOrderInformationId(Long orderInformationId);
}