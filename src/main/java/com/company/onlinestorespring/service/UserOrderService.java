package com.company.onlinestorespring.service;

import com.company.onlinestorespring.entity.Product;
import com.company.onlinestorespring.entity.User;
import com.company.onlinestorespring.entity.UserOrder;
import java.util.List;

public interface UserOrderService {

    /**
     * Retrieve orders by user id
     *
     * @param userId - the user id
     * @return the list of orders
     */
    List<UserOrder> retrieveUserOrdersByUserId(Long userId);

    /**
     * Retrieve orders by user id where order information is null and status is true
     *
     * @param userId - the user id
     * @return the list of orders
     */
    List<UserOrder> retrieveUserOrdersByUserWhereProductStatusTrue(Long userId);

    /**
     * Retrieve orders by status
     *
     * @param status - the status
     * @return the list of orders
     */
    List<UserOrder> retrieveUserOrderByOrderInformationStatus(String status);

    /**
     * Retrieve all user orders
     *
     * @return the list of orders
     */
    List<UserOrder> retrieveAllUserOrders();

    /**
     * Remove a user order by id
     *
     * @param orderId - the order id
     */
    void removeUserOrdersById(Long orderId);

    /**
     * Add a new order
     *
     * @param user             - the user
     * @param product          - the product
     * @param purchaseQuantity - the purchase quantity
     * @return the result of product addition
     */
    boolean addNewOrder(User user, Product product, Integer purchaseQuantity);

    /**
     * Calculate the total cost
     *
     * @param userOrders - the list of orders
     * @return the total cost
     */
    double calculateTotalCost(List<UserOrder> userOrders);
}